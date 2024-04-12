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
public class MovimientoVo implements Serializable {
    private Integer id;
    private Integer idEntidad;
    private Integer idCuenta;
    private Integer idCliente;
    private String cliente;
    private String numCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private String tipoMovimiento;
    private BigDecimal valorMovimiento;
    private BigDecimal saldoDisponibleCuenta;
    private BigDecimal saldoDisponibleFechaCuenta;
    private Date fecMovimiento;
    private Boolean estadoCuenta;
}
