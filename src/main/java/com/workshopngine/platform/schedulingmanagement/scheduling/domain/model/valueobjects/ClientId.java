package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ClientId(Long clientId) {
    public ClientId {
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("ClientId must be a positive number");
        }
    }
}
