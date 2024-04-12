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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Catálogo entity.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "movimiento", schema = "pichincha")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cuenta_cliente", nullable = false)
    private CuentaClienteEntity cuentaCliente;

    @Column(name = "tipo_movimiento", nullable = false, length = 100)
    private String tipo;

    @Column(name = "desc_movimiento", nullable = true, length = 150)
    private String descripcion;

    @Column(name = "fec_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMovimiento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo_fecha_cuenta")
    private BigDecimal saldoCuentaFecha;
}