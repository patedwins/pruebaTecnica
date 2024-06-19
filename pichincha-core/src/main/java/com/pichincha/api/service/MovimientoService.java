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
                MovimientoVo movData = MovimientoVo.builder()
                        .idEntidad(idEntidad)
                        .idCuenta(mov.getCuentaCliente().getCuenta().getId())
                        .entidad(mov.getCuentaCliente().getCuenta().getEntidad().getNombre())
                        .id(mov.getId())
                        .fecMovimiento(mov.getFecMovimiento())
                        .idCliente(mov.getCuentaCliente().getCliente().getId())
                        .cliente(mov.getCuentaCliente().getCliente().getPersona().getNombre())
                        .idCuenta(mov.getCuentaCliente().getCuenta().getId())
                        .numCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta())
                        .tipoCuenta(mov.getCuentaCliente().getCuenta().getTipo())
                        .tipoMovimiento(mov.getDescripcion())
                        .saldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial())
                        .valorMovimiento(mov.getValor())
                        .saldoDisponibleCuenta(mov.getCuentaCliente().getCuenta().getSaldoDisponible())
                        .saldoDisponibleFechaCuenta(mov.getSaldoCuentaFecha())
                        .estadoCuenta(mov.getCuentaCliente().getCuenta().getEstado())
                        .build();
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
        if (!cuentaOp.isPresent()) {
            return "No se encontró la cuenta";
        }
        Optional<ClienteEntity> clienteOp = clienteRepository.findById(movimiento.getIdCliente());
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
        if (fecDesde == null || fecHasta == null) {
            throw new PichinchaException(HttpStatus.BAD_REQUEST, MensajeConstantes.OBLIGATORIO_FECHAS);
        }
        List<MovimientoEntity> listMovimiento = movimientoRepository.obtenerPorFechas(
                convertirStringToDate(fecDesde.concat(" 00:00:00")), convertirStringToDate(fecHasta.concat(" 23:59:59")));
        List<MovimientoFechasVo> retorno = new ArrayList<>();
        listMovimiento.stream().forEach(mov -> {
            MovimientoFechasVo movData = new MovimientoFechasVo().toBuilder()
                    .fecha(mov.getFecMovimiento())
                    .cliente(mov.getCuentaCliente().getCliente().getPersona().getNombre())
                    .numeroCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta())
                    .tipo(mov.getCuentaCliente().getCuenta().getTipo())
                    .saldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial())
                    .movimiento(mov.getValor())
                    .saldoDisponible(mov.getSaldoCuentaFecha())
                    .estado(mov.getCuentaCliente().getCuenta().getEstado())
                    .build();
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