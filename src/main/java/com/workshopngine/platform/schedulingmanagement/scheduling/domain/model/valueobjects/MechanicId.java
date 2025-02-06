package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record MechanicId(String mechanicId) {
    public MechanicId {
        if (mechanicId == null || mechanicId.isBlank()) {
            throw new IllegalArgumentException("mechanicId cannot be null or empty");
        }
    }
}
