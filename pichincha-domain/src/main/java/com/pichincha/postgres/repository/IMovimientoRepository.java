/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.postgres.repository;

import com.pichincha.postgres.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IMovimientoRepository extends JpaRepository<MovimientoEntity, Integer> {

    /**
     * Obtener movimientos
     *
     * @param idEntidad
     * @param estado
     * @return
     */
    @Query("select mov from MovimientoEntity mov " +
            " join fetch mov.cuentaCliente ccl join fetch ccl.cuenta cc join fetch cc.entidad ent " +
            " join fetch ccl.cliente cl join fetch cl.persona p " +
            "where ent.id = :idEntidad and cc.estado=:estado")
    List<MovimientoEntity> obtenerPorEntidad(@Param("idEntidad") Integer idEntidad, @Param("estado") Boolean estado);

    /**
     * Obtener movimientos
     *
     * @param fecDesde
     * @param fecHasta
     * @return
     */
    @Query("select mov from MovimientoEntity mov " +
            " join fetch mov.cuentaCliente ccl join fetch ccl.cuenta cc join fetch cc.entidad ent " +
            " join fetch ccl.cliente cl join fetch cl.persona p " +
            "where mov.fecMovimiento between :fecDesde and :fecHasta order by mov.fecMovimiento")
    List<MovimientoEntity> obtenerPorFechas(@Param("fecDesde") Date fecDesde, @Param("fecHasta") Date fecHasta);
}
