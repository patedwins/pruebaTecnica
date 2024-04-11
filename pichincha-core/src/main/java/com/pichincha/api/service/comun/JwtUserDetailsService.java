package com.pichincha.api.service.comun;

import com.pichincha.api.service.security.AuditDataUser;
import com.pichincha.api.service.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * JwtUserDetailsService.
 *
 * @author patedwins on 2022/09/08.
 * @version 1.0.0
 */
@Service
public class JwtUserDetailsService {

    @Autowired
    private transient JwtTokenUtil jwtTokenUtil;

    /**
     * loadUserByClaims.
     *
     * @param claimsUser
     * @param aplicacion
     * @return
     */
    public UserDetails loadUserByClaims(Claims claimsUser, String aplicacion) {

        String roles = claimsUser.get("roles", String.class);
        String nombre = claimsUser.get("nombre", String.class);
        Integer idUsuario = claimsUser.get("idusuario", Integer.class);
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (StringUtils.isNotEmpty(roles)) {
            StringTokenizer tokenizer = new StringTokenizer(roles, ",");
            while (tokenizer.hasMoreTokens()) {
                String rol = tokenizer.nextToken();
                grantedAuthorities.add(new SimpleGrantedAuthority(rol));
            }
        }
        String password = "";
        return new AuditDataUser(jwtTokenUtil.getUsernameFromToken(claimsUser), password, nombre, aplicacion, idUsuario.toString(), grantedAuthorities);

    }
}
