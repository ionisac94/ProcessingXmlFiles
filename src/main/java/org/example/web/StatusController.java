package org.example.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatusController for the application.
 */
@RequiredArgsConstructor
@RestController
public class StatusController {

	private final HealthEndpoint healthEndpoint;

	@GetMapping(path = "/status")
	public Status getStatus() {
		HealthComponent health = healthEndpoint.health();
		return health.getStatus();
	}
}
