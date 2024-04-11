package com.pichincha.config.security;

import com.pichincha.api.service.comun.JwtUserDetailsService;
import com.pichincha.api.service.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro para interceptar cada peticion del cliente, y asegurarse que todas las
 * llamadas tengan un token v&aacute;lido
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private transient JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private transient JwtTokenUtil jwtTokenUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/actuator/metrics") || path.startsWith("/actuator/prometheus") || path.startsWith("/transaccion");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);

        // Obtener el token JWT eliminando parte de la cadena que es innecesaria de
        // Bearer
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                Claims claimsUsuario = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
                // Cargamos los datos a sesion.
                if (claimsUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.jwtUserDetailsService.loadUserByClaims(claimsUsuario,
                            request.getContextPath());
                    // Si el token es valido, lo cargamos en la sesion de Spring Security
                    if (jwtTokenUtil.validateToken(claimsUsuario, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (IllegalArgumentException e) {
                logger.error("No se puede obtener el token JWT", e);
            } catch (ExpiredJwtException e) {
                logger.error("Token expirado", e);
            }
        } else {
            logger.debug("El token no empieza con la palabra Bearer");
        }

        filterChain.doFilter(request, response);
    }
}