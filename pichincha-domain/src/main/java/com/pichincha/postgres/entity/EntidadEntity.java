package com.pichincha.postgres.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catálogo entity.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "entidad", schema = "pichincha")
public class EntidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;
}