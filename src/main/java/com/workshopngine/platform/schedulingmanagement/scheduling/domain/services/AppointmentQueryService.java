package com.workshopngine.platform.schedulingmanagement.scheduling.domain.services;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentByClientIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentsByWorkshopIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAppointmentByIdQuery;

import java.util.Collection;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    Collection<Appointment> handle(GetAllAppointmentByClientIdQuery query);
    Collection<Appointment> handle(GetAllAppointmentsByWorkshopIdQuery query);
}
