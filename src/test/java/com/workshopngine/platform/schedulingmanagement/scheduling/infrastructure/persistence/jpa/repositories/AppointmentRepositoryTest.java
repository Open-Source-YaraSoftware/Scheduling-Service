package com.workshopngine.platform.schedulingmanagement.scheduling.infrastructure.persistence.jpa.repositories;

import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.aggregates.Appointment;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.ClientId;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.DateTimeRange;
import com.workshopngine.platform.schedulingmanagement.scheduling.domain.model.valueobjects.WorkshopId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppointmentRepositoryTest {
    @Container
    @ServiceConnection
    static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.6");

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void TestEstablishConnection_ShouldPass() {
        Assertions.assertThat(mariaDBContainer.isCreated()).isTrue();
        Assertions.assertThat(mariaDBContainer.isRunning()).isTrue();
    }

    @Test
    void TestFindAllAppointments_ValidWorkshopId_ShouldPass() {
        // Given
        Appointment appointment1 = new Appointment();
        appointment1.setWorkshopId(new WorkshopId("1"));
        Appointment appointment2 = new Appointment();
        appointment2.setWorkshopId(new WorkshopId("1"));
        Appointment appointment3 = new Appointment();
        appointment3.setWorkshopId(new WorkshopId("2"));
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        // When
        var appointments = appointmentRepository.findAllByWorkshopId(new WorkshopId("1"));
        // Then
        Assertions.assertThat(appointments).hasSize(2);
    }

    @Test
    void TestFindAllAppointments_ValidClientId_ShouldPass() {
        // Given
        Appointment appointment1 = new Appointment();
        appointment1.setClientId(new ClientId("1"));
        Appointment appointment2 = new Appointment();
        appointment2.setClientId(new ClientId("1"));
        Appointment appointment3 = new Appointment();
        appointment3.setClientId(new ClientId("2"));
        Appointment appointment4 = new Appointment();
        appointment4.setClientId(new ClientId("1"));
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        appointmentRepository.save(appointment4);
        // When
        var appointments = appointmentRepository.findAllByClientId(new ClientId("1"));
        // Then
        Assertions.assertThat(appointments).hasSize(3);
    }

    @Test
    void TestExistsAppointment_ValidTimeRangeStartedAt_ValidTimeRangeEndedAt_ShouldPass() {
        // Given
        Appointment appointment1 = new Appointment();
        appointment1.setTimeRange(new DateTimeRange());
        appointment1.getTimeRange().setStartedAt(LocalDateTime.of(2025, 1, 1, 1, 0));
        appointment1.getTimeRange().setEndedAt(LocalDateTime.of(2025, 1, 1, 2, 0));
        Appointment appointment2 = new Appointment();
        appointment2.setTimeRange(new DateTimeRange());
        appointment2.getTimeRange().setStartedAt(LocalDateTime.of(2025, 1, 1, 4, 0));
        appointment2.getTimeRange().setEndedAt(LocalDateTime.of(2025, 1, 1, 5, 0));
        Appointment appointment3 = new Appointment();
        appointment3.setTimeRange(new DateTimeRange());
        appointment3.getTimeRange().setStartedAt(LocalDateTime.of(2025, 1, 1, 3, 0));
        appointment3.getTimeRange().setEndedAt(LocalDateTime.of(2025, 1, 1, 4, 0));
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        // When
        var exists1 = appointmentRepository
                .existsByTimeRange_StartedAtLessThanEqualAndTimeRange_EndedAtGreaterThanEqual(
                        LocalDateTime.of(2025, 1, 1, 2, 0),
                        LocalDateTime.of(2025, 1, 1, 3, 0));
        var exists2 = appointmentRepository
                .existsByTimeRange_StartedAtLessThanEqualAndTimeRange_EndedAtGreaterThanEqual(
                        LocalDateTime.of(2025, 1, 1, 5, 0),
                        LocalDateTime.of(2025, 1, 1, 6, 0));
        var exists3 = appointmentRepository
                .existsByTimeRange_StartedAtLessThanEqualAndTimeRange_EndedAtGreaterThanEqual(
                        LocalDateTime.of(2025, 1, 1, 3, 0),
                        LocalDateTime.of(2025, 1, 1, 4, 0));
        // Then
        Assertions.assertThat(exists1).isFalse();
        Assertions.assertThat(exists2).isFalse();
        Assertions.assertThat(exists3).isTrue();

    }
}