package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events;

import lombok.Builder;

@Builder
public record AppointmentCreatedEvent(
        String appointmentId,
        String clientId,
        String vehicleId,
        String mechanicId,
        String workshopId
) implements AppointmentEvent {
}
