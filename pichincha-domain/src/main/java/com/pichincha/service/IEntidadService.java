/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.service;

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
}