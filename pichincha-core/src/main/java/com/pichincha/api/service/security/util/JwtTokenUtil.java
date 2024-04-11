package com.pichincha.api.service.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Componente que nos permite encriptar y desencriptar un token JWT.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Getter
@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Devuelve el nombre de usuario de un token JWT
     *
     * @return
     */
    public String getUsernameFromToken(Claims claims) {
        return getClaimFromToken(claims, Claims::getSubject);
    }

    /**
     * Devuelve la fecha de expiracion de un token JWT
     *
     * @return
     */
    public Date getExpirationDateFromToken(Claims claims) {
        return getClaimFromToken(claims, Claims::getExpiration);
    }

    /**
     * getClaimFromToken
     *
     * @param claims
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    /**
     * Desencripta un token JWT y devuelve todas las propiedades del token
     *
     * @param token
     * @return
     */
    public Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Verifica si un token ya ha expirado
     *
     * @return
     */
    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = getExpirationDateFromToken(claims);
        return expiration.before(new Date());
    }

    /**
     * Genera un token para un usuario de sesi&oaocute;n actual
     *
     * @param userDetails usuario de sesi&oacute;n de Spring Security
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new ConcurrentHashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Genera un token JWT en el que se establece el propietario, tiempo de expiración, motivo y su ID.
     * Se firma el token JWT usando el algoritmo HS512 y una clave secreta.
     * Se compacta el token JWT a una cadena segura para URL de acuerdo a JWS (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1).
     *
     * @param claims  par&aacute;metros para reclamar el token
     * @param subject motivo para generar el token JWT
     * @return Token JWT compactado.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Verifica que un token JWT sea v&aacute;lido, tanto que pertenezca al mismo usuario de sesi&oacute;n
     * como el tiempo de expiraci&oacute;n del token
     *
     * @param userDetails Datos del usuario de sesi&oacute;n actual de Spring Security
     * @return
     */
    public boolean validateToken(Claims claims, UserDetails userDetails) {
        final String username = getUsernameFromToken(claims);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(claims);
    }
}
