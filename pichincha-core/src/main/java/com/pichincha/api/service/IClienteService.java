/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.ClienteEntity;
import com.pichincha.vo.ClienteVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IClienteService {

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    List<ClienteEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewCliente(ClienteVo data) throws PichinchaException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateCliente(ClienteVo data) throws PichinchaException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteCliente(ClienteVo data) throws PichinchaException;
}