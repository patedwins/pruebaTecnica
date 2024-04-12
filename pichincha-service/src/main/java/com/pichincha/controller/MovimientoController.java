/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import com.pichincha.api.service.IMovimientoService;
import com.pichincha.api.service.exception.util.MensajeConstantes;
import com.pichincha.vo.MovimientoFechasVo;
import com.pichincha.vo.MovimientoRegistarVo;
import com.pichincha.vo.MovimientoVo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CatalogoController.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@RestController
@RequestMapping("/movimiento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class MovimientoController {

    private final transient IMovimientoService movimientoService;

    /**
     * Controller
     *
     * @param movimientoService
     */

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @GetMapping(value = "obtenerMovimientosPorEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> findAllMovimiento(@NotNull @RequestParam("idEntidad") Integer idEntidad
            , HttpServletRequest request) {
        try {
            List<MovimientoVo> responsList = movimientoService.obtenerMovimientoPorEntidad(idEntidad);
            return new ResponseEntity<>(responsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en una nueva persona: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @PostMapping(value = "crearMovimientosPorCuentaYCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> asiganarMovimiento(@RequestBody MovimientoRegistarVo movimiento
            , HttpServletRequest request) {
        try {
            String respons = movimientoService.generarMovimientoPorEntidad(movimiento);
            if (respons == null) {
                return new ResponseEntity<>(MensajeConstantes.SAVE_UPDATE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(respons, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en generar movimiento: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @GetMapping(value = "obtenerMovimientosPorFecha", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> findAllMovimientoPorFecha(@NotNull @RequestParam("fecDesde") String fecDesde
            , @NotNull @RequestParam("fecHasta") String fecHasta, HttpServletRequest request) {
        try {
            List<MovimientoFechasVo> responsList = movimientoService.obtenerMovimientoPorFecha(fecDesde, fecHasta);
            return new ResponseEntity<>(responsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en obtener Movimientos Por Fecha: "
                    + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
