package com.pichincha.postgres.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Catálogo entity.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "cliente", schema = "pichincha")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

    @Column(name = "clienteid", nullable = false, length = 20)
    private String clienteid;

    @Column(name = "contrasena", nullable = false, length = 50)
    private String contrasena;

    @Column(name = "estado")
    private Boolean estado;
}