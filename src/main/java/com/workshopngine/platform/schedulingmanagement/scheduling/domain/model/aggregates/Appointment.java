package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events.AppointmentCreatedEvent;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.DateTimeRange;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.*;
import com.workshopngine.platform.schedulingmanagement.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    @Embedded
    private ClientId clientId;

    @Embedded
    private VehicleId vehicleId;

    @Embedded
    private MechanicId mechanicId;

    @Embedded
    private WorkshopId workshopId;

    @Enumerated(EnumType.STRING)
    private EServiceType serviceType;

    @Embedded
    private DateTimeRange timeRange;

    @Enumerated(EnumType.STRING)
    private EAppointmentStatus status;

    @Embedded
    private ServiceDuration duration;

    public Appointment() {
        super();
        this.status = EAppointmentStatus.SCHEDULED;
        this.duration = new ServiceDuration();
    }

    public Appointment(CreateAppointmentCommand command){
        this();
        this.clientId = command.clientId();
        this.vehicleId = command.vehicleId();
        this.mechanicId = command.mechanicId();
        this.workshopId = command.workshopId();
        this.serviceType = command.serviceType();
        this.timeRange = command.dateTimeRange();
        verifyServiceDuration();
    }

    private void verifyServiceDuration(){
        if (this.serviceType.equals(EServiceType.DIAGNOSTIC)){
            this.duration.setEstimatedDuration(Duration.ofHours(1));
        }
        if (this.serviceType.equals(EServiceType.MAINTENANCE)){
            this.duration.setEstimatedDuration(Duration.ofHours(1).plus(Duration.ofMinutes(30)));
        }
        if (this.serviceType.equals(EServiceType.REPAIR)){
            this.duration.setEstimatedDuration(Duration.ofHours(2));
        }
            this.timeRange.setEndedAt(this.timeRange.getStartedAt().plus(this.duration.getEstimatedDuration()));
    }

    public void sendAppointmentCreatedEvent(){
        registerEvent(AppointmentCreatedEvent
                .builder()
                .appointmentId(this.getId())
                .clientId(this.getClientId().clientId())
                .vehicleId(this.getVehicleId().vehicleId())
                .mechanicId(this.getMechanicId().mechanicId())
                .workshopId(this.getWorkshopId().workshopId())
                .build());
    }
}
