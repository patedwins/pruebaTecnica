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
import com.pichincha.vo.CuentaClienteVo;
import com.pichincha.vo.CuentaVo;

import java.util.List;

/**
 * Cuenta service interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface ICuentaService {

    /**
     * Find all cuenta.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<CuentaEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewCuenta(CuentaVo data) throws PichinchaException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateCuenta(CuentaVo data) throws PichinchaException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteCuenta(CuentaVo data) throws PichinchaException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String crearCuentaCliente(CuentaClienteVo data) throws PichinchaException;
}