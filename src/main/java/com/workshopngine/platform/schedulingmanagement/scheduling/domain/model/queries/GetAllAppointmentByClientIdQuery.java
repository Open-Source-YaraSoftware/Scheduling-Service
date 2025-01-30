package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAllAppointmentByClientIdQuery(Long clientId) {
    public GetAllAppointmentByClientIdQuery {
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("clientId cannot be null or less than or equal to 0");
        }
    }
}
