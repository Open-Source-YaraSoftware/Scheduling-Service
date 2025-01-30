package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto;

import java.time.LocalDateTime;

public record CreateAppointmentResource(
        Long clientId,
        Long vehicleId,
        Long mechanicId,
        Long workshopId,
        String serviceType,
        LocalDateTime startedAt
) {
}
