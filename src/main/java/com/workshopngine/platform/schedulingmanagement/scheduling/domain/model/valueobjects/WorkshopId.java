package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

public record WorkshopId(Long workshopId) {
    public WorkshopId {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("WorkshopId must be a positive number");
        }
    }
}
