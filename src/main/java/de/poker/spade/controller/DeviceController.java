package de.poker.spade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.poker.spade.entity.Device;
import de.poker.spade.service.AdminService;

@RestController
@RequestMapping("/api")
public class DeviceController {

	@Autowired
	private AdminService adminService;

	// Queries

	@GetMapping("/devices/querydsl")
	public ResponseEntity<?> findDevicesByStudentUsername(@RequestParam(value="username") String username) {
		return new ResponseEntity<Iterable<Device>>(adminService.findDevicesByStudentUsernameWithQuerydsl(username), HttpStatus.OK);
	}

}
