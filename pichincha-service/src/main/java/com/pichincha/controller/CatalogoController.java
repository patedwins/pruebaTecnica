/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.exception.util.MessageProcessorUtil;
import com.pichincha.service.IBaseCatalogoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CatalogoController.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@RestController
@RequestMapping("/catalogo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class CatalogoController {

    private final transient IBaseCatalogoService baseCatalogoService;
    private final transient MessageProcessorUtil messageProcessor;

    /**
     * Controller
     *
     * @param baseCatalogoService
     * @param messageProcessor
     */

    public CatalogoController(IBaseCatalogoService baseCatalogoService, MessageProcessorUtil messageProcessor) {
        this.baseCatalogoService = baseCatalogoService;
        this.messageProcessor = messageProcessor;
    }

}
