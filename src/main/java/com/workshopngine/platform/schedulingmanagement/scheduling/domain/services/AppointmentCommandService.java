package com.workshopngine.platform.schedulingmanagement.scheduling.domain.services;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
}
