/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pichincha.vo;

import lombok.Data;

/**
 * @author cfreire
 */
@Data
public class CatalogoVO {
    private Long codCatalogo;
    private Long codCatalogoPadre;
    private Integer codGrupoCatalogo;
    private String nomNemonico;
    private String nomCatalogo;
    private Integer numOrden;
    private String nomCodigoExterno;
    private String estRegistro;
    private String nomAplicacion;
    private String visibleSasof;
}
