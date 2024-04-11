package com.pichincha.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Catálogo response VO.
 *
 * @author cfreire on 2022/08/25.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoResponseVO {
    private Long codCatalogo;
    private Long codCatalogoPadre;
    private Long codGrupoCatalogo;
    private String nomNemonico;
    private String nomCatalogo;
    private Long numOrden;
    private Long numNivel;
    private Date fecInicio;
    private Date fecFin;
    private String estRegistro;
    private Date fecCreacion;
    private String nomUsuarioCreacion;
    private Date fecActualizacion;
    private String nomUsuarioActualizacion;
    private String nomAplicacion;
    private String nomAplicacionProceso;
    private String txtDescripcion;
}
