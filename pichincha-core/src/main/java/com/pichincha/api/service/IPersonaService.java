/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.postgres.entity.PersonaEntity;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IPersonaService {

    /**
     * Find all person.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<PersonaEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewPerson(PersonaEntity person) throws PichinchaException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updatePerson(PersonaEntity person) throws PichinchaException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deletePerson(PersonaEntity person) throws PichinchaException;
}