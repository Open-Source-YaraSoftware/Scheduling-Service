package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events.AppointmentCreatedEvent;
import com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.brokers.kafka.AppointmentEventSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class AppointmentEventPublisherService {
    private final AppointmentEventSource appointmentEventSource;

    @TransactionalEventListener
    public void handleAppointmentCreatedEvent(AppointmentCreatedEvent event) {
        appointmentEventSource.publishEvent(event);
    }
}