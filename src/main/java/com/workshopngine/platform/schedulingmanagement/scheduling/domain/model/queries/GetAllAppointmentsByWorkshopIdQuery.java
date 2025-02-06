package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAllAppointmentsByWorkshopIdQuery(String workshopId) {
    public GetAllAppointmentsByWorkshopIdQuery {
        if (workshopId == null || workshopId.isBlank()) {
            throw new IllegalArgumentException("workshopId cannot be null or empty");
        }
    }
}
