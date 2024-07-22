package org.kernel360.busseat.schedule.controller;

import java.util.Map;

import org.kernel360.busseat.schedule.ApiProperties;
import org.kernel360.busseat.schedule.ResultCode;
import org.kernel360.busseat.schedule.service.ScheduledTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class BusLocationController {

	private final ScheduledTask scheduledTask;
	private final ApiProperties apiProperties;

	public BusLocationController(ScheduledTask scheduledTask, ApiProperties apiProperties) {
		this.scheduledTask = scheduledTask;
		this.apiProperties = apiProperties;
	}

	@GetMapping("/busLocations")
	public ResponseEntity<Map<String, Object>> getBusLocations(@RequestParam("routeId") String routeId) {
		// Prepare parameters for the API call
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", routeId);

		// Make the API call using the scheduledTask's request method
		String path = "/6410000/buslocationservice/getBusLocationList";
		String encodedKey = apiProperties.getServiceKey(); // Assuming serviceKey is configured correctly

		// Use scheduledTask to make the request
		Map<String, Object> response = scheduledTask.request(path, encodedKey, params, Map.class);

		// Interpret the result code
		Integer resultCodeValue = (Integer) response.get("resultCode");
		if (resultCodeValue == null) {
			// Handle missing resultCode appropriately
			response.put("resultMessage", "Result code is missing in the response.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		ResultCode resultCode = ResultCode.fromCode(resultCodeValue);
		response.put("resultMessage", resultCode.getMessage());

		// Return ResponseEntity with the response from the API
		return ResponseEntity.ok(response);
	}
}
