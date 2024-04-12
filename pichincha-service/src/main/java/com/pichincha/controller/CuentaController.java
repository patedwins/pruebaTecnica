/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.ICuentaService;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.postgres.entity.CuentaEntity;
import com.pichincha.vo.CuentaClienteVo;
import com.pichincha.vo.CuentaVo;
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
@RequestMapping("/cuenta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class CuentaController {

    private final transient ICuentaService cuentaService;

    /**
     * Controller
     *
     * @param cuentaService
     */

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    /**
     * Find all entidad.
     *
     * @return a @{@link CuentaEntity} list.
     */
    @GetMapping(value = "obtenerListaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CuentaEntity> findAll() {
        return cuentaService.findAll();
    }

    /**
     * New Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @PostMapping(value = "nuevaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> newCuenta(@RequestBody CuentaVo data, HttpServletRequest request) {
        try {
            String respons = cuentaService.saveNewCuenta(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_NEW, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva Cuenta: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @PutMapping(value = "actualizarCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> updateCuenta(@RequestBody CuentaVo data, HttpServletRequest request) {
        try {
            String respons = cuentaService.updateCuenta(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_UPDATE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en actualizar cuenta: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @DeleteMapping(value = "eliminarCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> deleteCuenta(@RequestBody CuentaVo data, HttpServletRequest request) {
        try {
            String respons = cuentaService.deleteCuenta(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.DELETE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en un eliminar cuenta: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crear cuenta y asignar cuenta cliente.
     *
     * @return a @{@link CuentaClienteVo} string.
     */
    @PostMapping(value = "nuevaCuentaCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> crearCuentaCliente(@RequestBody CuentaClienteVo data, HttpServletRequest request) {
        try {
            String respons = cuentaService.crearCuentaCliente(data);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_NEW, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva cuenta cliente: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
