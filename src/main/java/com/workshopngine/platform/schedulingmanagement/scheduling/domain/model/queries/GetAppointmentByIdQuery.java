package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries;

public record GetAppointmentByIdQuery(Long appointmentId) {
    public GetAppointmentByIdQuery {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Appointment ID must be a positive integer");
        }
    }
}
