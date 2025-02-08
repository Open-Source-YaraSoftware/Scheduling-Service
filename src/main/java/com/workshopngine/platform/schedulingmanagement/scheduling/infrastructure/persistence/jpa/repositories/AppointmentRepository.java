package com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.persistence.jpa.repositories;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.ClientId;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.WorkshopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    Collection<Appointment> findAllByWorkshopId(WorkshopId workshopId);
    Collection<Appointment> findAllByClientId(ClientId clientId);
    Boolean existsByTimeRange_StartedAtLessThanEqualAndTimeRange_EndedAtGreaterThanEqual(LocalDateTime requestedTimeStart, LocalDateTime requestedTimeEnd);
}
