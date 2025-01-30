package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public record AppointmentResource(
        Long id,
        Long clientId,
        Long vehicleId,
        Long mechanicId,
        Long workshopId,
        String serviceType,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String status,
        Duration estimatedDuration,
        Duration actualDuration
) {
}
