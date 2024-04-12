package com.pichincha.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * JwtResponse.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Data
public class CuentaClienteVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idEntidad;
    private Integer idCliente;
    private String numCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
}
