/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.oracle.repository;

import com.pichincha.oracle.entity.CatalogoOracleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

/**
 * Base catálogo repository interfaz.
 *
 * @author cfreire on 2022/08/31.
 * @version 1.0.0
 */
public interface ICatalogoOracleRepository extends JpaRepository<CatalogoOracleEntity, Integer> {

    /**
     * Consulta el catálogo por codigo grupo catalogo y codigo catálogo padre.
     *
     * @param codGrupoCatalogo
     * @param codCatalogoPadre
     * @param estRegistro
     * @param visible
     * @return
     */
    @Query(value = "SELECT COD_CATALOGO, COD_CATALOGO_PADRE, COD_GRUPO_CATALOGO, NOM_NEMONICO, NOM_CATALOGO, \n" +
            "NUM_ORDEN, NOM_CODIGO_EXTERNO, EST_ORIGEN, EST_REGISTRO, FEC_CREACION, NOM_USUARIO_CREACION, \n" +
            "FEC_ACTUALIZACION, NOM_USUARIO_ACTUALIZACION, NOM_APLICACION, EST_VISIBLE_MS, VISIBLESASOF \n" +
            "FROM RSFPS.RSFPS_CATALOGO WHERE COD_GRUPO_CATALOGO = :codGrupoCatalogo AND \n" +
            "COD_CATALOGO_PADRE = :codCatalogoPadre AND EST_REGISTRO = :estRegistro AND VISIBLESASOF = :visible ORDER BY NUM_ORDEN ASC"
            , nativeQuery = true)
    Collection<CatalogoOracleEntity> findByRsfpsGrupoCatalogoAndRsfpsCatalogoAndEstRegistro(
            @Param("codGrupoCatalogo") Integer codGrupoCatalogo,
            @Param("codCatalogoPadre") Long codCatalogoPadre,
            @Param("estRegistro") String estRegistro,
            @Param("visible") String visible
    );

    /**
     * Consulta el catálogo por nombre nemónico.
     *
     * @param nomNemonico
     * @return
     */
    Optional<CatalogoOracleEntity> findAllByNomNemonico(String nomNemonico);

    /**
     * Consulta el catálogo por codigo catálogo.
     *
     * @param codCatalogo
     * @return
     */
    Optional<CatalogoOracleEntity> findAllByCodCatalogo(Long codCatalogo);

}