package com.pichincha.api.service.security;

import com.pichincha.service.security.IAuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * AuthenticationFacade.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    /**
     * getAuthenticatedUser.
     *
     * @return
     */
    @Override
    public String getAuthenticatedUser() {
        return ((AuditDataUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    /**
     * getCurrentApplication.
     *
     * @return
     */
    @Override
    public String getCurrentApplication() {
        return ((AuditDataUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getApplication();
    }

    /**
     * getAuthenticatedNombreCompleto.
     *
     * @return
     */
    @Override
    public String getAuthenticatedNombreCompleto() {
        return ((AuditDataUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNombreCompleto();
    }

    /**
     * getCurrentSessionToken.
     *
     * @return
     */
    @Override
    public String getCurrentSessionToken() {
        return ((AuditDataUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSessionToken();
    }

}
