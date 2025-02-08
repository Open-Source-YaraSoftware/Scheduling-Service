package com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources;

public record MechanicAvailabilityResource(
        String mechanicId,
        Boolean available
) {
}