/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.postgres.repository;

import com.pichincha.postgres.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
