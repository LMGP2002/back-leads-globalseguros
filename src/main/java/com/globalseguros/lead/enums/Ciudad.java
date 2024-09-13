package com.globalseguros.lead.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Ciudad {
    CALI("Cali"),
    MEDELLIN("Medellín"),
    BOGOTA("Bogotá");

    private final String value;

    Ciudad(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Ciudad fromValue(String value) {
        for (Ciudad ciudad : Ciudad.values()) {
            if (ciudad.value.equalsIgnoreCase(value)) {
                return ciudad;
            }
        }
        throw new IllegalArgumentException("Valor desconocido: " + value);
    }
}
