package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.*;

public record CreateAppointmentCommand(
        ClientId clientId,
        VehicleId vehicleId,
        MechanicId mechanicId,
        WorkshopId workshopId,
        EServiceType serviceType,
        DateTimeRange dateTimeRange
) {
}
