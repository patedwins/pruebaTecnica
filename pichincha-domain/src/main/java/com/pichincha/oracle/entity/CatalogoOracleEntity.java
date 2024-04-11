/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pichincha.oracle.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cfreire
 */
@Entity
@Table(name = "RSFPS_CATALOGO", catalog = "", schema = "RSFPS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NOM_NEMONICO"})})
@NamedQueries({
        @NamedQuery(name = "RsfpsCatalogo.findAll", query = "SELECT r FROM CatalogoOracleEntity r")})
@Data
public class CatalogoOracleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_CATALOGO", nullable = false)
    private Long codCatalogo;
    @Basic(optional = false)
    @Column(name = "NOM_NEMONICO", nullable = false, length = 5)
    private String nomNemonico;
    @Basic(optional = false)
    @Column(name = "NOM_CATALOGO", nullable = false, length = 200)
    private String nomCatalogo;
    @Column(name = "NUM_ORDEN")
    private Integer numOrden;
    @Column(name = "NOM_CODIGO_EXTERNO", length = 50)
    private String nomCodigoExterno;
    @Basic(optional = false)
    @Column(name = "EST_REGISTRO", nullable = false, length = 3)
    private String estRegistro;
    @Basic(optional = false)
    @Column(name = "FEC_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Basic(optional = false)
    @Column(name = "NOM_USUARIO_CREACION", nullable = false, length = 50)
    private String nomUsuarioCreacion;
    @Column(name = "FEC_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Column(name = "NOM_USUARIO_ACTUALIZACION", length = 50)
    private String nomUsuarioActualizacion;
    @Basic(optional = false)
    @Column(name = "NOM_APLICACION", nullable = false, length = 50)
    private String nomAplicacion;
    @Column(name = "EST_ORIGEN")
    private Character estOrigen;
    @Transient
    @Column(name = "EST_VISIBLE_MS", length = 1)
    private String estVisibleMs;
    @Column(name = "VISIBLESASOF", length = 1)
    private String visibleSasof;
    @Transient
    @OneToMany(mappedBy = "rsfpsCatalogo", fetch = FetchType.LAZY)
    private List<CatalogoOracleEntity> rsfpsCatalogoList;
    @JoinColumn(name = "COD_CATALOGO_PADRE", referencedColumnName = "COD_CATALOGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CatalogoOracleEntity rsfpsCatalogo;
    @JoinColumn(name = "COD_GRUPO_CATALOGO", referencedColumnName = "COD_GRUPO_CATALOGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoCatalogoOracleEntity rsfpsGrupoCatalogo;
}
