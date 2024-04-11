package com.pichincha;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer to run spring boot app.
 *
 * @author cfreire on 2022/08/23.
 * @version 1.0.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * configure
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppWebApplication.class);
    }

}
