package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public record AppointmentResource(
        String id,
        String clientId,
        String vehicleId,
        String mechanicId,
        String workshopId,
        String serviceType,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String status,
        Duration estimatedDuration
) {
}
