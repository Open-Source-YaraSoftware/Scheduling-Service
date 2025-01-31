package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events;

import com.workshopngine.platform.schedulingmanagement.shared.domain.model.events.DomainEvent;

public sealed interface AppointmentEvent extends DomainEvent permits AppointmentCreatedEvent {
}
