package com.workshopngine.platform.schedulingmanagement.scheduling.domain.services;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;

public interface AppointmentCommandService {
    String handle(CreateAppointmentCommand command);
}
