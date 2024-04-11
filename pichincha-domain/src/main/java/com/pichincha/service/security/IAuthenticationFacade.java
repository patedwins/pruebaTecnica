package com.pichincha.service.security;

/**
 * IAuthenticationFacade.
 *
 * @author cfreire on 2022/09/08.
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
