/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.postgres.entity.ClienteEntity;
import com.pichincha.postgres.entity.CuentaClienteEntity;
import com.pichincha.postgres.entity.CuentaEntity;
import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.postgres.entity.MovimientoEntity;
import com.pichincha.postgres.repository.IClienteRepository;
import com.pichincha.postgres.repository.ICuentaClienteRepository;
import com.pichincha.postgres.repository.ICuentaRepository;
import com.pichincha.postgres.repository.IEntidadRepository;
import com.pichincha.postgres.repository.IMovimientoRepository;
import com.pichincha.util.Constantes;
import com.pichincha.vo.MovimientoFechasVo;
import com.pichincha.vo.MovimientoRegistarVo;
import com.pichincha.vo.MovimientoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Service
public class MovimientoService implements IMovimientoService {

    private final transient IMovimientoRepository movimientoRepository;
    @Autowired
    private transient IEntidadRepository entidadRepository;
    @Autowired
    private transient ICuentaRepository cuentaRepository;
    @Autowired
    private transient IClienteRepository clienteRepository;
    @Autowired
    private transient ICuentaClienteRepository cuentaClienteRepository;

    private static final DateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH);

    /**
     * Constructor
     *
     * @param movimientoRepository
     */
    public MovimientoService(IMovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    /**
     * Find todos los movimientos
     *
     * @param idEntidad
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public List<MovimientoVo> obtenerMovimientoPorEntidad(Integer idEntidad) {
        List<MovimientoVo> retorno = new ArrayList<>();
        Optional<EntidadEntity> entidad = entidadRepository.findById(idEntidad);
        if (entidad.isPresent()) {
            List<MovimientoEntity> listMovimiento = movimientoRepository.obtenerPorEntidad(entidad.get().getId(), Boolean.TRUE);
            listMovimiento.stream().forEach(mov -> {
                MovimientoVo movData = new MovimientoVo();
                movData.setIdEntidad(idEntidad);
                movData.setEntidad(mov.getCuentaCliente().getCuenta().getEntidad().getNombre());
                movData.setId(mov.getId());
                movData.setFecMovimiento(mov.getFecMovimiento());
                movData.setIdCliente(mov.getCuentaCliente().getCliente().getId());
                movData.setCliente(mov.getCuentaCliente().getCliente().getPersona().getNombre());
                movData.setIdCuenta(mov.getCuentaCliente().getCuenta().getId());
                movData.setNumCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta());
                movData.setTipoCuenta(mov.getCuentaCliente().getCuenta().getTipo());
                movData.setTipoMovimiento(mov.getDescripcion());
                movData.setSaldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial());
                movData.setValorMovimiento(mov.getValor());
                movData.setSaldoDisponibleCuenta(mov.getCuentaCliente().getCuenta().getSaldoDisponible());
                movData.setSaldoDisponibleFechaCuenta(mov.getSaldoCuentaFecha());
                movData.setEstadoCuenta(mov.getCuentaCliente().getCuenta().getEstado());
                retorno.add(movData);
            });
        }
        return retorno;
    }

    /**
     * Find all movimientos por entidad
     *
     * @param movimiento
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public String generarMovimientoPorEntidad(MovimientoRegistarVo movimiento) throws PichinchaException {
        Optional<CuentaEntity> cuentaOp = cuentaRepository.findById(movimiento.getIdCuenta());
        Optional<ClienteEntity> clienteOp = clienteRepository.findById(movimiento.getIdCliente());
        if (!cuentaOp.isPresent()) {
            return "No se encontró la cuenta";
        }
        if (!clienteOp.isPresent()) {
            return "No se encontró al cliente";
        }
        CuentaEntity cuenta = cuentaOp.get();
        CuentaClienteEntity cuentaCliente = cuentaClienteRepository.findByCuentaAndCliente(cuenta, clienteOp.get());
        if (cuentaCliente == null) {
            return "No se encontró la cuenta asociada al cliente";
        }
        MovimientoEntity newMovimiento = new MovimientoEntity();
        BigDecimal saldoDisponible = cuentaOp.get().getSaldoDisponible();
        newMovimiento.setCuentaCliente(cuentaCliente);
        if (movimiento.getValorMovimiento().compareTo(BigDecimal.ZERO) >= 0) {
            newMovimiento.setTipo(Constantes.DEPOSITO);
            newMovimiento.setDescripcion(Constantes.DEPOSITO.concat(" ").concat(movimiento.getValorMovimiento().toString()));
        } else {
            newMovimiento.setTipo(Constantes.RETIRO);
            newMovimiento.setDescripcion(Constantes.RETIRO.concat(" ").concat(movimiento.getValorMovimiento().toString()));
        }
        saldoDisponible = saldoDisponible.add(movimiento.getValorMovimiento());
        newMovimiento.setSaldoCuentaFecha(saldoDisponible);
        newMovimiento.setValor(movimiento.getValorMovimiento());
        newMovimiento.setFecMovimiento(new Date());
        cuenta.setSaldoDisponible(saldoDisponible);
        movimientoRepository.save(newMovimiento);
        cuentaRepository.save(cuenta);
        return null;
    }

    /**
     * Find all movimientos por entidad
     *
     * @param fecDesde
     * @param fecHasta
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public List<MovimientoFechasVo> obtenerMovimientoPorFecha(String fecDesde, String fecHasta) throws PichinchaException {
        List<MovimientoFechasVo> retorno = new ArrayList<>();
        Date fechaDesde = new Date();
        Date fechaHasta = new Date();
        if (fecDesde == null || fecHasta == null) {
            throw new PichinchaException(HttpStatus.BAD_REQUEST, MensajeConstantes.OBLIGATORIO_FECHAS);
        } else {
            fechaDesde = convertirStringToDate(fecDesde.concat(" 00:00:00"));
            fechaHasta = convertirStringToDate(fecHasta.concat(" 23:59:59"));
        }
        List<MovimientoEntity> listMovimiento = movimientoRepository.obtenerPorFechas(fechaDesde, fechaHasta);
        listMovimiento.stream().forEach(mov -> {
            MovimientoFechasVo movData = new MovimientoFechasVo();
            movData.setFecha(mov.getFecMovimiento());
            movData.setCliente(mov.getCuentaCliente().getCliente().getPersona().getNombre());
            movData.setNumeroCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta());
            movData.setTipo(mov.getCuentaCliente().getCuenta().getTipo());
            movData.setSaldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial());
            movData.setMovimiento(mov.getValor());
            movData.setSaldoDisponible(mov.getSaldoCuentaFecha());
            movData.setEstado(mov.getCuentaCliente().getCuenta().getEstado());
            retorno.add(movData);
        });
        return retorno;
    }

    /**
     * Permite dar formato a la fecha
     *
     * @return fecha con formato
     */
    public Date convertirStringToDate(String fechaString) {
        try {
            synchronized (FORMATO) {
                return FORMATO.parse(fechaString);
            }
        } catch (ParseException ex) {
            throw new PichinchaException(HttpStatus.BAD_REQUEST, MensajeConstantes.FORMATO_FECHAS);
        }
    }
}