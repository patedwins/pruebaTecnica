/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.IEntidadService;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.postgres.entity.EntidadEntity;
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
@RequestMapping("/entidad")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class EntidadController {

    private final transient IEntidadService entidadService;

    /**
     * Controller
     *
     * @param entidadService
     */

    public EntidadController(IEntidadService entidadService) {
        this.entidadService = entidadService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @GetMapping(value = "obtenerListaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<EntidadEntity> findAll() {
        return entidadService.findAll();
    }

    /**
     * New Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @PostMapping(value = "nuevaEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> newEntity(@RequestBody EntidadEntity data, HttpServletRequest request) {
        try {
            String respons = entidadService.saveNewEntidad(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_NEW, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva dataa: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @PutMapping(value = "actualizarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> updateEntidad(@RequestBody EntidadEntity data, HttpServletRequest request) {
        try {
            String respons = entidadService.updateEntidad(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_UPDATE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva dataa: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @DeleteMapping(value = "eliminarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> deleteEntidad(@RequestBody EntidadEntity data, HttpServletRequest request) {
        try {
            String respons = entidadService.deleteEntidad(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.DELETE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva dataa: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
