/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.postgres.repository;

import com.pichincha.postgres.entity.ClienteEntity;
import com.pichincha.postgres.entity.CuentaClienteEntity;
import com.pichincha.postgres.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
public interface ICuentaClienteRepository extends JpaRepository<CuentaClienteEntity, Integer> {

    /**
     * Obtener cuenta cliente por cuenta y cliente
     *
     * @param cuenta
     * @param cliente
     * @return
     */
    CuentaClienteEntity findByCuentaAndCliente(CuentaEntity cuenta, ClienteEntity cliente);

}
