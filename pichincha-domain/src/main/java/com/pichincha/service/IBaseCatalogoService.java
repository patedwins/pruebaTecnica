/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.service;

import com.pichincha.oracle.entity.CatalogoOracleEntity;
import com.pichincha.postgres.entity.CatalogoEntity;
import com.pichincha.postgres.entity.GrupoCatalogoEntity;
import com.pichincha.vo.CatalogoVO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Base catálogo service interfaz.
 *
 * @author cfreire on 2022/08/23.
 * @version 1.0.0
 */
public interface IBaseCatalogoService {

    /**
     * obtenerListaCalogoGrupo
     *
     * @return
     */
    CatalogoEntity obtenerCatalogoPorNemonicoYGrupo(String nemonicoCatalogo, String nemonicoGrupo);

    /**
     * Consulta una lista de catálogos desde una lista de codigos.
     *
     * @param codCatalogos
     * @return
     */
    List<CatalogoEntity> findAllByCodCatalogoInAndEstRegistro(List<Integer> codCatalogos);

    /**
     * Consulta una lista de catálogos desde una lista de codigos.
     *
     * @param nomGrupoCatalogo
     * @param nomNemonico
     * @return
     */
    Collection<CatalogoVO> obtenerCatalogoPorNemonicoYGrupoOracle(String nomGrupoCatalogo, String nomNemonico);

    /**
     * Consulta catálogos por id.
     *
     * @param id
     * @return
     */
    Optional<CatalogoOracleEntity> findById(Long id);

    /**
     * Find all group catalogs.
     *
     * @return a @{@link GrupoCatalogoEntity} list.
     */
    List<GrupoCatalogoEntity> findAll();

    /**
     * Find all caltalogs by group catalog code.
     *
     * @param id
     * @return
     */
    List<CatalogoEntity> findAllByCodigoGrupoCatalogo(Long id);
}