package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record MechanicId(Long mechanicId) {
    public MechanicId {
        if (mechanicId == null || mechanicId < 0) {
            throw new IllegalArgumentException("Invalid mechanicId");
        }
    }
}
