package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.transform;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
            entity.getId(),
            entity.getClientId().clientId(),
            entity.getVehicleId().vehicleId(),
            entity.getMechanicId().mechanicId(),
            entity.getWorkshopId().workshopId(),
            entity.getServiceType().toString(),
            entity.getTimeRange().getStartedAt(),
            entity.getTimeRange().getEndedAt(),
            entity.getStatus().toString(),
            entity.getDuration().getEstimatedDuration()
        );
    }
}
