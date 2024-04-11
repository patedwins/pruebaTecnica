/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.CuentaEntity;
import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.postgres.repository.ICuentaRepository;
import com.pichincha.postgres.repository.IEntidadRepository;
import com.pichincha.vo.CuentaVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Service
public class CuentaService implements ICuentaService {

    private final transient ICuentaRepository cuentaRepository;
    private final transient IEntidadRepository entidadRepository;

    /**
     * Constructor
     *
     * @param cuentaRepository
     */
    public CuentaService(ICuentaRepository cuentaRepository, IEntidadRepository entidadRepository) {
        this.cuentaRepository = cuentaRepository;
        this.entidadRepository = entidadRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link CuentaEntity} list.
     */
    @Override
    public List<CuentaEntity> findAll() {
        return cuentaRepository.findAll();
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public String saveNewCuenta(CuentaVo data) throws PichinchaException {
        CuentaEntity newData = new CuentaEntity();
        Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
        if (entidadOp.isPresent()) {
            newData.setEntidad(entidadOp.get());
            newData.setNumCuenta(data.getNumCuenta());
            newData.setTipo(data.getTipo());
            newData.setSaldoInicial(data.getSaldoInicial());
            newData.setEstado(Boolean.TRUE);
            cuentaRepository.save(newData);
            return null;
        } else {
            return "No se encontró la entidad para la cuenta";
        }
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public String updateCuenta(CuentaVo data) throws PichinchaException {
        Optional<CuentaEntity> opCuenta = cuentaRepository.findById(data.getId());
        if (opCuenta.isPresent()) {
            Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
            if (entidadOp.isPresent()) {
                CuentaEntity newData = opCuenta.get();
                newData.setEntidad(entidadOp.get());
                newData.setNumCuenta(data.getNumCuenta());
                newData.setTipo(data.getTipo());
                newData.setSaldoInicial(data.getSaldoInicial());
                newData.setEstado(Boolean.TRUE);
                cuentaRepository.save(newData);
                return null;
            } else {
                return "No se encontró la entidad para la cuenta";
            }
        } else {
            return "No se encontró la entidad para su actualización";
        }
    }

    /**
     * Delete a persona
     *
     * @param data
     * @return a @{@link String} list.
     */
    @Override
    public String deleteCuenta(CuentaVo data) throws PichinchaException {
        Optional<CuentaEntity> opCuenta = cuentaRepository.findById(data.getId());
        if (opCuenta.isPresent()) {
            CuentaEntity actEntidad = opCuenta.get();
            cuentaRepository.delete(actEntidad);
            return null;
        } else {
            return "No se encontró la persona para su eliminación";
        }
    }
}