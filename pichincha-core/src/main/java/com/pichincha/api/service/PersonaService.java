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
import com.pichincha.postgres.repository.IPersonaRepository;
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
public class PersonaService implements IPersonaService {

    private final transient IPersonaRepository personaRepository;

    /**
     * Constructor
     *
     * @param personaRepository
     */
    public PersonaService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public List<PersonaEntity> findAll() {
        return personaRepository.findAll();
    }

    /**
     * Save new persona
     *
     * @param person
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public String saveNewPerson(PersonaEntity person) throws PichinchaException {
        PersonaEntity newPerson = new PersonaEntity();
        newPerson.setNombre(person.getNombre());
        newPerson.setEdad(person.getEdad());
        newPerson.setDireccion(person.getDireccion());
        newPerson.setGenero(person.getGenero());
        newPerson.setIdentificacion(person.getIdentificacion());
        newPerson.setTelefono(person.getTelefono());
        newPerson.setEstado(Boolean.TRUE);
        personaRepository.save(newPerson);
        return null;
    }

    /**
     * Update a persona
     *
     * @param person
     * @return a @{@link String} list.
     */
    @Override
    public String updatePerson(PersonaEntity person) throws PichinchaException {
        Optional<PersonaEntity> opPerson = personaRepository.findById(person.getId());
        if (opPerson.isPresent()) {
            PersonaEntity actPerson = opPerson.get();
            actPerson.setNombre(person.getNombre());
            actPerson.setEdad(person.getEdad());
            actPerson.setDireccion(person.getDireccion());
            actPerson.setGenero(person.getGenero());
            actPerson.setIdentificacion(person.getIdentificacion());
            actPerson.setTelefono(person.getTelefono());
            personaRepository.save(actPerson);
            return null;
        } else {
            return "No se encontró la persona para su actualización";
        }
    }

    /**
     * Delete a persona
     *
     * @param person
     * @return a @{@link String} list.
     */
    @Override
    public String deletePerson(PersonaEntity person) throws PichinchaException {
        Optional<PersonaEntity> opPerson = personaRepository.findById(person.getId());
        if (opPerson.isPresent()) {
            PersonaEntity actPerson = opPerson.get();
            personaRepository.delete(actPerson);
            return null;
        } else {
            return "No se encontró la persona para su eliminación";
        }
    }
}