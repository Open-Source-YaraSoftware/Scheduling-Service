package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentByClientIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAllAppointmentsByWorkshopIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.queries.GetAppointmentByIdQuery;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.services.AppointmentCommandService;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.services.AppointmentQueryService;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.AppointmentResource;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.CreateAppointmentResource;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Endpoints for managing appointments")
public class AppointmentController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Get appointment by ID", description = "Get appointment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment found"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var query = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(query);
        if (appointment.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all appointments by workshop ID or client ID", description = "Get all appointments by workshop ID or client ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<Collection<AppointmentResource>> getAllAppointmentsByWorkshopIdOrClientId
            (@RequestParam(required = false) Long workshopId, @RequestParam(required = false) Long clientId) {
        if (workshopId == null && clientId == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (workshopId != null && clientId != null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var appointments = (workshopId != null)
                ? appointmentQueryService.handle(new GetAllAppointmentsByWorkshopIdQuery(workshopId))
                : appointmentQueryService.handle(new GetAllAppointmentByClientIdQuery(clientId));
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(appointmentResources, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create appointment", description = "Create appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var command = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointmentId = appointmentCommandService.handle(command);
        if (appointmentId == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(getAppointmentById(appointmentId).getBody(), HttpStatus.CREATED);
    }

}
