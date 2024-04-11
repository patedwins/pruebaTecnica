/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.EntidadEntity;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IEntidadService {

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<EntidadEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewEntidad(EntidadEntity data) throws PichinchaException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateEntidad(EntidadEntity data) throws PichinchaException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteEntidad(EntidadEntity data) throws PichinchaException;
}