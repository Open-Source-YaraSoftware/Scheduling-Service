package com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources;

public record WorkshopAvailabilityResource(
        String workshopId,
        Boolean available
) {
}