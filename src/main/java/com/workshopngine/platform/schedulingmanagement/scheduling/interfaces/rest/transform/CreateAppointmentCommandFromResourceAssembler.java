package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.transform;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.*;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                new ClientId(resource.clientId()),
                new VehicleId(resource.vehicleId()),
                new MechanicId(resource.mechanicId()),
                new WorkshopId(resource.workshopId()),
                EServiceType.fromString(resource.serviceType()),
                new DateTimeRange(resource.startedAt())
        );
    }
}
