package com.pichincha.api.service.enums;

/**
 * Messages enumerator to error manage.
 *
 * @author patedwins on 2022/09/16.
 * @version 1.0.0
 */
public enum MessagesEnum {

    ERROR_404("ec.gob.seps.error.message.404");

    private String value;

    MessagesEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
