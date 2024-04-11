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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Catálogo entity.
 *
 * @author cfreire on 2022/08/25.
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "base_catalogo", schema = "base")
public class CatalogoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_catalogo", nullable = false)
    private Integer codCatalogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_catalogo_padre")
    private CatalogoEntity codCatalogoPadre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_grupo_catalogo", nullable = false)
    private GrupoCatalogoEntity codGrupoCatalogo;

    @Column(name = "nom_nemonico", nullable = false, length = 100)
    private String nomNemonico;

    @Column(name = "nom_catalogo", nullable = false, length = 1000)
    private String nomCatalogo;

    @Column(name = "num_orden", nullable = false)
    private Integer numOrden;

    @Column(name = "num_nivel", nullable = false)
    private Integer numNivel;

    @Column(name = "fec_inicio", nullable = false)
    private Instant fecInicio;

    @Column(name = "fec_fin")
    private Instant fecFin;

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

    @Column(name = "txt_descripcion", length = 2000)
    private String txtDescripcion;

    @OneToMany(mappedBy = "codCatalogoPadre")
    private Set<CatalogoEntity> baseCatalogos = new LinkedHashSet<>();

}