package com.pichincha;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Objects;

/**
 * AppWebApplication to run spring boot app.
 *
 * @author patedwins on 2024/12/04.
 * @version 1.0.0
 */
@SpringBootApplication
@Slf4j
public class AppWebApplication {

    /**
     * Main to run app.
     *
     * @param args args to pass app
     */
    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AppWebApplication.class);
            app.run(args);
        } catch (Exception throwable) {
            if (!Objects.equals(throwable.getClass().getName(),
                    "org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")
                    && log.isErrorEnabled()) {
                log.error(
                        "********************************Ha ocurrido una exception****************************");
                log.error("Exception: " + throwable.toString());
                log.error("Root Cause: " + ExceptionUtils.getRootCause(throwable).toString());
            }
        }
    }

    /**
     * commandLineRunner.
     *
     * @param ctx the ctx
     * @return CommandLineRunner instance
     */
    @Bean
    public CommandLineRunner commandLineRunner(ListableBeanFactory ctx) {
        if (log.isDebugEnabled()) {
            log.debug("Beans Loaded by Spring Boot:{}", ctx.getBeanDefinitionCount());
        }
        return args -> {
            if (log.isDebugEnabled()) {
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    log.debug("Bean:{}", beanName);
                }
            }
        };
    }

}
