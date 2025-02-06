package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAllAppointmentByClientIdQuery(String clientId) {
    public GetAllAppointmentByClientIdQuery {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("clientId cannot be null or empty");
        }
    }
}
