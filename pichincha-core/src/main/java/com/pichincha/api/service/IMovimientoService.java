/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.api.service.exception.PichinchaException;
import com.pichincha.postgres.entity.EntidadEntity;
import com.pichincha.vo.MovimientoVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IMovimientoService {

    /**
     * Find all movimientos por entidad
     *
     * @return a @{@link MovimientoVo} list.
     */
    List<MovimientoVo> obtenerMovimientoPorEntidad(Integer idEntidad);

    /**
     * Find all movimientos por entidad
     *
     * @return a @{@link MovimientoVo} list.
     */
    String generarMovimientoPorEntidad(MovimientoVo movimiento) throws PichinchaException;


}