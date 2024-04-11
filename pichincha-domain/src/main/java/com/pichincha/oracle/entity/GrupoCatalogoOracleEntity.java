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
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cfreire
 */
@Entity
@Table(name = "RSFPS_GRUPO_CATALOGO", catalog = "", schema = "RSFPS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NOM_NEMONICO"})})
@NamedQueries({
        @NamedQuery(name = "RsfpsGrupoCatalogo.findAll", query = "SELECT r FROM GrupoCatalogoOracleEntity r")})
@Data
public class GrupoCatalogoOracleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_GRUPO_CATALOGO", nullable = false)
    private Integer codGrupoCatalogo;
    @Basic(optional = false)
    @Column(name = "NOM_NEMONICO", nullable = false, length = 5)
    private String nomNemonico;
    @Basic(optional = false)
    @Column(name = "NOM_GRUPO_CATALOGO", nullable = false, length = 20)
    private String nomGrupoCatalogo;
    @Column(name = "TXT_DESCRIPCION", length = 100)
    private String txtDescripcion;
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
    @OneToMany(mappedBy = "rsfpsGrupoCatalogo", fetch = FetchType.LAZY)
    private List<CatalogoOracleEntity> rsfpsCatalogoList;
    @OneToMany(mappedBy = "rsfpsGrupoCatalogo", fetch = FetchType.LAZY)
    private List<GrupoCatalogoOracleEntity> rsfpsGrupoCatalogoList;
    @JoinColumn(name = "COD_GRUPO_CATALOGO_PADRE", referencedColumnName = "COD_GRUPO_CATALOGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoCatalogoOracleEntity rsfpsGrupoCatalogo;
}
