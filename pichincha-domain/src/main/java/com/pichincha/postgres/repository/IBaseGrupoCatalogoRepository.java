/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.postgres.repository;

import com.pichincha.postgres.entity.GrupoCatalogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Base grupo catálogo repository interfaz.
 *
 * @author cfreire on 2022/10/05.
 * @version 1.0.0
 */
public interface IBaseGrupoCatalogoRepository extends JpaRepository<GrupoCatalogoEntity, Integer> {

}
