package com.pichincha.service.security;

/**
 * IAuthenticationFacade.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
public interface IAuthenticationFacade {

    /**
     * getAuthenticatedUser.
     *
     * @return
     */
    String getAuthenticatedUser();

    /**
     * getCurrentApplication.
     *
     * @return
     */
    String getCurrentApplication();

    /**
     * getAuthenticatedNombreCompleto.
     *
     * @return
     */
    String getAuthenticatedNombreCompleto();

    /**
     * getCurrentSessionToken.
     *
     * @return
     */
    String getCurrentSessionToken();
}
