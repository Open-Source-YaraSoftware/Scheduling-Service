package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events;

import lombok.Builder;

@Builder
public record AppointmentCreatedEvent(
        Long appointmentId,
        Long clientId,
        Long vehicleId,
        Long mechanicId,
        Long workshopId
) implements AppointmentEvent {
}
