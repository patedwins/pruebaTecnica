/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.IPersonaService;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.postgres.entity.PersonaEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CatalogoController.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class PersonaController {

    private final transient IPersonaService personaService;

    /**
     * Controller
     *
     * @param personaService
     */

    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    /**
     * Find all person.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @GetMapping(value = "obtenerListaPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PersonaEntity> findAll() {
        return personaService.findAll();
    }

    /**
     * New person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @PostMapping(value = "nuevaPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> newPerson(@RequestBody PersonaEntity person, HttpServletRequest request) {
        try {
            String respons = personaService.saveNewPerson(person);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_NEW, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva persona: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @PutMapping(value = "actualizarPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> updatePerson(@RequestBody PersonaEntity person, HttpServletRequest request) {
        try {
            String respons = personaService.updatePerson(person);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_UPDATE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva persona: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @DeleteMapping(value = "eliminarPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> deletePerson(@RequestBody PersonaEntity person, HttpServletRequest request) {
        try {
            String respons = personaService.deletePerson(person);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.DELETE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva persona: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
