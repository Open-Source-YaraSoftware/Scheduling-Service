package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@Embeddable
public class ServiceDuration {
    @NotNull
    private Duration estimatedDuration;

    public ServiceDuration() {
        this.estimatedDuration = Duration.ZERO;
    }
}
