package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ClientId(String clientId) {
    public ClientId {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("clientId cannot be null or empty");
        }
    }
}
