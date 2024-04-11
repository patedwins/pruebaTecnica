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
@Table(name = "persona", schema = "pichincha")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "genero", nullable = true, length = 1)
    private String genero;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "identificacion", nullable = false, length = 18)
    private String identificacion;

    @Column(name = "direccion", nullable = true, length = 200)
    private String direccion;

    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;

    @Column(name = "estado")
    private Boolean estado;
}