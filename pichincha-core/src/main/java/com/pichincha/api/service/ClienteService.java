/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.ClienteEntity;
import com.pichincha.postgres.entity.PersonaEntity;
import com.pichincha.postgres.repository.IClienteRepository;
import com.pichincha.postgres.repository.IPersonaRepository;
import com.pichincha.vo.ClienteVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClienteService implements IClienteService {

    private final transient IClienteRepository clienteRepository;
    @Autowired
    private transient IPersonaRepository personaRepository;

    /**
     * Constructor
     *
     * @param clienteRepository
     */
    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    @Override
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public String saveNewCliente(ClienteVo data) throws PichinchaException {
        Optional<PersonaEntity> personaOp = personaRepository.findById(data.getIdPersona());
        if (!personaOp.isPresent()) {
            return "No se encontró la persona para su creación";
        }
        PersonaEntity persona = personaOp.get();
        ClienteEntity newData = new ClienteEntity();
        newData.setClienteid(data.getClienteId());
        newData.setPersona(persona);
        newData.setContrasena(data.getContrasena());
        newData.setEstado(Boolean.TRUE);
        clienteRepository.save(newData);
        return null;
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public String updateCliente(ClienteVo data) throws PichinchaException {
        Optional<ClienteEntity> clienteOp = clienteRepository.findById(data.getId());
        if (!clienteOp.isPresent()) {
            return "No se encontró al cliente para su actualización";
        }
        Optional<PersonaEntity> personaOp = personaRepository.findById(data.getIdPersona());
        if (personaOp.isPresent()) {
            ClienteEntity newData = clienteOp.get();
            newData.setClienteid(data.getClienteId());
            newData.setPersona(personaOp.get());
            newData.setContrasena(data.getContrasena());
            newData.setEstado(Boolean.TRUE);
            clienteRepository.save(newData);
            return null;
        } else {
            return "No se encontró la persona para su creación";
        }
    }

    /**
     * Delete a persona
     *
     * @param data
     * @return a @{@link String} list.
     */
    @Override
    public String deleteCliente(ClienteVo data) throws PichinchaException {
        Optional<ClienteEntity> opEntidad = clienteRepository.findById(data.getId());
        if (opEntidad.isPresent()) {
            ClienteEntity actEntidad = opEntidad.get();
            clienteRepository.delete(actEntidad);
            return null;
        } else {
            return "No se encontró la persona para su eliminación";
        }
    }
}