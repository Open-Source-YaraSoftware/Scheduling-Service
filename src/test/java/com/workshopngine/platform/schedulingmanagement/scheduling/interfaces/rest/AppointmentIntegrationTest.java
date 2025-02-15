package com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest;

import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.AppointmentResource;
import com.workshopngine.platform.schedulingmanagement.scheduling.interfaces.rest.dto.CreateAppointmentResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentIntegrationTest {
    @Container
    @ServiceConnection
    static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.6");

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void TestCreateAppointment_ValidResource_ShouldPass() {
        // Given
        CreateAppointmentResource createAppointmentResource = new CreateAppointmentResource(
                "1",
                "1",
                "9bc16276-ad51-4216-a0f7-2aef0668c5c5",
                "d4138fe8-cbdf-4652-8d8a-87bfefb1aa6a",
                "REPAIR",
                LocalDateTime.of(2025, 2, 27, 7, 0)
        );
        // When
        ResponseEntity<AppointmentResource> createAppointmentResponse = testRestTemplate.exchange(
                "/appointments",
                HttpMethod.POST,
                new HttpEntity<>(createAppointmentResource),
                AppointmentResource.class
        );
        // Then
        Assertions.assertThat(createAppointmentResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}