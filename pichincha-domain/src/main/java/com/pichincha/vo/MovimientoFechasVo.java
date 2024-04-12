package com.pichincha.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MovimientoVo.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Data
public class MovimientoFechasVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
