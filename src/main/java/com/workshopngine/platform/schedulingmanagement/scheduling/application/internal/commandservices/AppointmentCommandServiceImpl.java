package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.commandservices;

import com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl.WorkshopManagementContextFacade;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.services.AppointmentCommandService;
import com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;
    private final WorkshopManagementContextFacade workshopManagementContextFacade;

    @Override
    public String handle(CreateAppointmentCommand command) {
        var newAppointment = new Appointment(command);
        try {
            this.appointmentRepository.save(newAppointment);
            newAppointment.sendAppointmentCreatedEvent();
            this.appointmentRepository.save(newAppointment);
        } catch (Exception e) {
            throw new RuntimeException("Error saving appointment", e);
        }
        return newAppointment.getId();
    }
}
