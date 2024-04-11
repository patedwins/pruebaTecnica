package com.pichincha.postgres.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Grupo catalogo entity.
 *
 * @author cfreire on 2022/08/25.
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "base_grupo_catalogo", schema = "base")
public class GrupoCatalogoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_grupo_catalogo", nullable = false)
    private Integer id;

    @Column(name = "nom_nemonico", nullable = false, length = 100)
    private String nomNemonico;

    @Column(name = "nom_nombre", nullable = false, length = 50)
    private String nomNombre;

    @Column(name = "txt_descripcion", nullable = false, length = 100)
    private String txtDescripcion;

    @Column(name = "est_registro", nullable = false, length = 3)
    private String estRegistro;

    @Column(name = "fec_creacion", nullable = false)
    private Instant fecCreacion;

    @Column(name = "nom_usuario_creacion", nullable = false, length = 50)
    private String nomUsuarioCreacion;

    @Column(name = "fec_actualizacion")
    private Instant fecActualizacion;

    @Column(name = "nom_usuario_actualizacion", length = 50)
    private String nomUsuarioActualizacion;

    @Column(name = "nom_aplicacion", nullable = false, length = 100)
    private String nomAplicacion;

    @Column(name = "nom_aplicacion_proceso", nullable = false, length = 50)
    private String nomAplicacionProceso;

    @OneToMany(mappedBy = "codGrupoCatalogo")
    private Set<CatalogoEntity> baseCatalogos = new LinkedHashSet<>();

}