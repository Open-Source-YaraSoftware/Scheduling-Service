package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.queryservices;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentByClientIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentsByWorkshopIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAppointmentByIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.ClientId;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.WorkshopId;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.services.AppointmentQueryService;
import com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return this.appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public Collection<Appointment> handle(GetAllAppointmentByClientIdQuery query) {
        var clientId = new ClientId(query.clientId());
        return this.appointmentRepository.findAllByClientId(clientId);
    }

    @Override
    public Collection<Appointment> handle(GetAllAppointmentsByWorkshopIdQuery query) {
        var workshopId = new WorkshopId(query.workshopId());
        return this.appointmentRepository.findAllByWorkshopId(workshopId);
    }
}
