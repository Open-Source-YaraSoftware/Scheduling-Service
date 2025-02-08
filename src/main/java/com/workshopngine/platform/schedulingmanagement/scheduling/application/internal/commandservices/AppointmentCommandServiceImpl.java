package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.commandservices;

import com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl.StaffManagementContextFacade;
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
    private final StaffManagementContextFacade staffManagementContextFacade;

    @Override
    public String handle(CreateAppointmentCommand command) {
        var newAppointment = new Appointment(command);
        var isWorkshopAvailable = this.workshopManagementContextFacade.checkWorkshopAvailability(command.workshopId().workshopId(), command.dateTimeRange().getStartedAt());
        if (!isWorkshopAvailable.available()) throw new RuntimeException("Workshop is not available");
        var isStaffAvailable = this.staffManagementContextFacade.checkMechanicAvailability(command.mechanicId().mechanicId(), command.dateTimeRange().getStartedAt());
        if (!isStaffAvailable.available()) throw new RuntimeException("Mechanic is not available");
        if (this.appointmentRepository
                .existsByTimeRange_StartedAtLessThanEqualAndTimeRange_EndedAtGreaterThanEqual(newAppointment.getTimeRange().getStartedAt(), newAppointment.getTimeRange().getEndedAt())
        ) throw new RuntimeException("Requested time is not available");

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
