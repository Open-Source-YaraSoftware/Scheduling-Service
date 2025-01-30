package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VehicleId(Long vehicleId) {
    public VehicleId {
        if (vehicleId == null || vehicleId < 0) {
            throw new IllegalArgumentException("VehicleId must be a positive number");
        }
    }
}
