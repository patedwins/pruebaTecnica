/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.postgres.repository.IEntidadRepository;
import com.pichincha.service.IEntidadService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins on 2022/08/22.
 * @version 1.0.0
 */
@Service
public class EntidadService implements IEntidadService {

    private final transient IEntidadRepository entidadRepository;

    /**
     * Constructor
     *
     * @param entidadRepository
     */
    public EntidadService(IEntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public List<EntidadEntity> findAll() {
        return entidadRepository.findAll();
    }
}