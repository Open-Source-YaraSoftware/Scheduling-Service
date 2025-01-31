package com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.brokers.kafka;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.events.AppointmentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

@Configuration
public class AppointmentEventSource {
    private final Queue<Message<?>> eventQueue = new LinkedList<>();

    @Bean
    public Supplier<Message<?>> appointmentSupplier() {
        return this.eventQueue::poll;
    }

    public void publishEvent(AppointmentEvent event){
        this.eventQueue.add(MessageBuilder.withPayload(event).build());
    }
}
