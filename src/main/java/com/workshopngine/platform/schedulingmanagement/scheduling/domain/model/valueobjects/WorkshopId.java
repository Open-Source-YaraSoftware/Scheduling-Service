package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

public record WorkshopId(String workshopId) {
    public WorkshopId {
        if (workshopId == null || workshopId.isBlank()) {
            throw new IllegalArgumentException("workshopId cannot be null or empty");
        }
    }
}
