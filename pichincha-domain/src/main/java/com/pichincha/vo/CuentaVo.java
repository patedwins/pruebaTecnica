package com.pichincha.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * JwtResponse.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Data
public class CuentaVo implements Serializable {
	private Integer id;
	private Integer idEntidad;
	private String numCuenta;
	private String tipo;
	private BigDecimal saldoInicial;
	private Boolean estado;
}
