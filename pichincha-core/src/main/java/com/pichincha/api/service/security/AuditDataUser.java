package com.pichincha.api.service.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * AuditDataUser.
 *
 * @author patedwins on 2022/09/08.
 * @version 1.0.0
 */
@Getter
public class AuditDataUser extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String application;
    private String nombreCompleto;
    private String sessionToken;

    /**
     * Constructor.
     *
     * @param username
     * @param password
     * @param nombreCompleto
     * @param application
     * @param sessionToken
     * @param authorities
     */
    public AuditDataUser(String username, String password, String nombreCompleto, String application, String sessionToken, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.application = application.replace("/", "");
        this.nombreCompleto = nombreCompleto;
        this.sessionToken = sessionToken;
    }
}
