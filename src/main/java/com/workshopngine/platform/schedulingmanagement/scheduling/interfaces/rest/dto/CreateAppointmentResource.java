package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto;

import java.time.LocalDateTime;

public record CreateAppointmentResource(
        String clientId,
        String vehicleId,
        String mechanicId,
        String workshopId,
        String serviceType,
        LocalDateTime startedAt
) {
}
