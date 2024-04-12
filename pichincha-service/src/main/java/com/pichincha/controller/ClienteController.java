/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.IClienteService;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.postgres.entity.ClienteEntity;
import com.pichincha.vo.ClienteVo;
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
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class ClienteController {

    private final transient IClienteService clienteService;

    /**
     * Controller
     *
     * @param clienteService
     */

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    @GetMapping(value = "obtenerListaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ClienteEntity> findAll() {
        return clienteService.findAll();
    }

    /**
     * New Entidad.
     *
     * @return a @{@link ClienteEntity} string.
     */
    @PostMapping(value = "nuevaEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> newEntity(@RequestBody ClienteVo data, HttpServletRequest request) {
        try {
            String respons = clienteService.saveNewCliente(data);
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
     * @return a @{@link ClienteEntity} string.
     */
    @PutMapping(value = "actualizarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> updateEntidad(@RequestBody ClienteVo data, HttpServletRequest request) {
        try {
            String respons = clienteService.updateCliente(data);
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
     * @return a @{@link ClienteEntity} string.
     */
    @DeleteMapping(value = "eliminarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> deleteEntidad(@RequestBody ClienteVo data, HttpServletRequest request) {
        try {
            String respons = clienteService.deleteCliente(data);
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
