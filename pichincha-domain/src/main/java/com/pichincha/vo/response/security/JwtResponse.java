package com.pichincha.vo.response.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * JwtResponse.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String token;
}
