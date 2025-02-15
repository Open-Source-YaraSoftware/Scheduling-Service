package com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.commandservices;

import com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl.StaffManagementContextFacade;
import com.workshopngine.platform.schedulingmanagement.scheduling.application.internal.outboundservices.acl.WorkshopManagementContextFacade;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.commands.CreateAppointmentCommand;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.*;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.services.AppointmentCommandService;
import com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources.MechanicAvailabilityResource;
import com.workshopngine.platform.schedulingmanagement.shared.interfaces.rest.resources.WorkshopAvailabilityResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
class AppointmentCommandServiceImplTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private WorkshopManagementContextFacade workshopManagementContextFacade;

    @Mock
    private StaffManagementContextFacade staffManagementContextFacade;

    @Captor
    private ArgumentCaptor<Appointment> appointmentArgumentCaptor;

    private AppointmentCommandService appointmentCommandService;

    @BeforeEach
    void setUp() {
        appointmentCommandService = new AppointmentCommandServiceImpl(appointmentRepository, workshopManagementContextFacade, staffManagementContextFacade);
    }

    @Test
    void TestCreateAppointment_ValidMechanicAvailability_ValidWorkshopAvailability_ShouldPass() {
        // Given
        CreateAppointmentCommand command = new CreateAppointmentCommand(
                new ClientId("c1b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b"),
                new VehicleId("v1b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b"),
                new MechanicId("9bc16276-ad51-4216-a0f7-2aef0668c5c5"),
                new WorkshopId("d4138fe8-cbdf-4652-8d8a-87bfefb1aa6a"),
                EServiceType.DIAGNOSTIC,
                new DateTimeRange(LocalDateTime.of(2024, 2, 6, 5, 0))
        );
        WorkshopAvailabilityResource workshopAvailabilityResource = new WorkshopAvailabilityResource("d4138fe8-cbdf-4652-8d8a-87bfefb1aa6a", true);
        Mockito.when(
                workshopManagementContextFacade
                        .checkWorkshopAvailability(command.workshopId().workshopId(), command.dateTimeRange().getStartedAt()))
                .thenReturn(workshopAvailabilityResource);
        MechanicAvailabilityResource mechanicAvailabilityResource = new MechanicAvailabilityResource("9bc16276-ad51-4216-a0f7-2aef0668c5c5", true);
        Mockito.when(
                staffManagementContextFacade
                        .checkMechanicAvailability(command.mechanicId().mechanicId(), command.dateTimeRange().getStartedAt()))
                .thenReturn(mechanicAvailabilityResource);
        // When
        appointmentCommandService.handle(command);
        // Then
        Assertions.assertThat(workshopAvailabilityResource.available())
                .as("Workshop availability")
                .isTrue();
        Assertions.assertThat(mechanicAvailabilityResource.available())
                .as("Mechanic availability")
                .isTrue();
        Mockito.verify(appointmentRepository, Mockito.times(2)).save(appointmentArgumentCaptor.capture());
        Appointment appointment = appointmentArgumentCaptor.getValue();
        Mockito.verify(staffManagementContextFacade).checkMechanicAvailability(command.mechanicId().mechanicId(), command.dateTimeRange().getStartedAt());
        Mockito.verify(workshopManagementContextFacade).checkWorkshopAvailability(command.workshopId().workshopId(), command.dateTimeRange().getStartedAt());
        Assertions.assertThat(appointment.getClientId())
                .as("Appointment client id")
                .isNotNull()
                .isEqualTo(command.clientId());
        Assertions.assertThat(appointment.getVehicleId())
                .as("Appointment vehicle id")
                .isNotNull()
                .isEqualTo(command.vehicleId());
        Assertions.assertThat(appointment.getMechanicId())
                .as("Appointment mechanic id")
                .isNotNull()
                .isEqualTo(command.mechanicId());
        Assertions.assertThat(appointment.getWorkshopId())
                .as("Appointment workshop id")
                .isNotNull()
                .isEqualTo(command.workshopId());
    }
}