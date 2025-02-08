package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl;

import com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources.WorkshopAvailabilityResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "workshop-management")
public interface WorkshopManagementContextFacade {
    @GetMapping("/workshops/{workshopId}/availability")
    WorkshopAvailabilityResource checkWorkshopAvailability(@PathVariable String workshopId, @RequestParam LocalDateTime requestedTime);
}
