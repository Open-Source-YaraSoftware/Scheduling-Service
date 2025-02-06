package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAppointmentByIdQuery(String appointmentId) {
    public GetAppointmentByIdQuery {
        if (appointmentId == null || appointmentId.isBlank()) {
            throw new IllegalArgumentException("appointmentId cannot be null or empty");
        }
    }
}
