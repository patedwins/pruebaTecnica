/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.service.IEntidadService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
