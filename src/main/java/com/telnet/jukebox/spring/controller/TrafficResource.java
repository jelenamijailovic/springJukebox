package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.service.TrafficService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;

@RestController
@RequestMapping("/traffic")
@Api(value = "/traffic", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Traffic")
public class TrafficResource {

	public static final Logger logger = LogManager.getLogger(TrafficResource.class);

	@Autowired
	public TrafficService trafficService;

	@CrossOrigin(origins = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get all traffic")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SongDTO.class) })
	@ResponseBody
	public List<TrafficDTO> getSviPrometi() {
		logger.info("All traffic!");

		List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>();
		try {
			listOfTrafficDTO = trafficService.getAllTraffic();
		} catch (EmptyListException e) {
			logger.error("There are no traffic in DB!");
			e.printStackTrace();
		}

		return listOfTrafficDTO;
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Add new traffic")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@ResponseBody
	public void addTraffic(@RequestHeader("Authorization") String authorization, @RequestBody TrafficDTO traffic) {
		logger.info("Add traffic!");

		logger.info("Authorization header is: " + authorization);

		DecodedJWT decJwt = JWT.decode(authorization.substring(7));
		String id = decJwt.getSubject();
		Long today = new Date().getTime();
		Long expiration = decJwt.getExpiresAt().getTime();
		if (today >= expiration) {
			throw new ExpTokenException("Token expiration");
		} else {
			traffic.setUser(id);
			logger.info("User id from token is  :" + id);
			trafficService.addTraffic(traffic);
			logger.info("Traffic is added!");
		}

	}
}
