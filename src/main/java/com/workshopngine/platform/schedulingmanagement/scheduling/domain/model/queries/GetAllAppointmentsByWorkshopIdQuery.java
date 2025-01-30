package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAllAppointmentsByWorkshopIdQuery(Long workshopId) {
    public GetAllAppointmentsByWorkshopIdQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("Workshop ID must be a positive integer");
        }
    }
}
