package com.github.lory24.keramix.core.p47.checks;

import lombok.Getter;

public enum ChecksIdentifiers {

    MOVEMENT_FLY_TYPE_A("Fly-A"),
    ;

    /**
     * The identifier string
     */
    @Getter
    private final String identifierText;

    ChecksIdentifiers(String identifierText) {
        this.identifierText = identifierText;
    }
}
