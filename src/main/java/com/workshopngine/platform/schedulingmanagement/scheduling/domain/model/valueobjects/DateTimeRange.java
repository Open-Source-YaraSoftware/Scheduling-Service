package com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class DateTimeRange {
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public DateTimeRange() {
        this.startedAt = null;
        this.endedAt = null;
    }

    public DateTimeRange(LocalDateTime startedAt) {
        this();
        this.startedAt = startedAt;
    }
}
