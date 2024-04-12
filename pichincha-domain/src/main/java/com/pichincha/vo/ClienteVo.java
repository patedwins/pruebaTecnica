package com.pichincha.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * JwtResponse.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Data
public class ClienteVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idPersona;
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
