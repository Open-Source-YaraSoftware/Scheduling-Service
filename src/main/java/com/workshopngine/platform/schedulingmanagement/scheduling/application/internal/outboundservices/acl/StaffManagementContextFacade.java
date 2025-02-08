package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl;

import com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources.MechanicAvailabilityResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "staff-management")
public interface StaffManagementContextFacade {
    @GetMapping("/mechanics/{mechanicId}/availability")
    MechanicAvailabilityResource checkMechanicAvailability(@PathVariable String mechanicId, @RequestParam LocalDateTime requestedTime);
}
