package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;

@RestController
@RequestMapping("/traffic")
@Api(value = "TrafficControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags= "Traffic")
public class TrafficResource {

	// final static Logger logger = Logger.getLogger(PrometResource.class);

	@Autowired
	public TrafficService trafficService;

	@Autowired
	public SongService songService;

	@CrossOrigin(origins = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get all traffic")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = List.class) })
	@ResponseBody
	public List<TrafficDTO> getSviPrometi() {
		// logger.info("Prikaz svih prometa");

		List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>();
		try {
			listOfTrafficDTO = trafficService.getAllTraffic();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTrafficDTO;
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Add new traffic")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED", response = TrafficDTO.class) })
	@ResponseBody
	public TrafficDTO addTraffic(@RequestHeader("Authorization") String authorization,
			@RequestBody TrafficDTO traffic) {
		// logger.info("Unosenje prometa");

		System.out.println(authorization);

		// logger.info(authorization);

		DecodedJWT decJwr = JWT.decode(authorization.substring(7));
		String id = decJwr.getSubject();
		Long today = new Date().getTime();
		Long expiration = decJwr.getExpiresAt().getTime();
		if (today >= expiration) {
			throw new ExpTokenException("Token expiration");
		} else {
			traffic.setUser(id);
			System.out.println("User id from token is  :" + id);
			try {
				return trafficService.addTraffic(traffic);
			} catch (ExpTokenException e) {
				e.printStackTrace();
			} catch (BadEntryException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/recomended")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> recomended(@RequestHeader("Authorization") String authorization) {
		// logger.info("Preporucujemo");

		DecodedJWT decJwt = JWT.decode(authorization.substring(7));
		String user = decJwt.getSubject();

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.recomended(user);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return listOfSongs;

	}

}
