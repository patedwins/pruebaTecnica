/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.oracle.repository;

import com.pichincha.oracle.entity.GrupoCatalogoOracleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Grupo catálogo repository interfaz.
 *
 * @author cfreire on 2022/08/31.
 * @version 1.0.0
 */
public interface IGrupoCatalogoOracleRepository extends JpaRepository<GrupoCatalogoOracleEntity, Integer> {

    /**
     * Consulta el grupo de catálogo por nombre de grupo catálogo
     *
     * @param nomGrupoCatalogo
     * @return
     */
    Optional<GrupoCatalogoOracleEntity> findByNomGrupoCatalogo(String nomGrupoCatalogo);

}