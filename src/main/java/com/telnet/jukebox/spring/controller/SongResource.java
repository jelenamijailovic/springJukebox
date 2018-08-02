package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/songs")
@Api(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Songs")
public class SongResource {

	public static final Logger logger = LogManager.getLogger(SongResource.class);

	@Autowired
	SongService songService = new SongService();

	@Autowired
	TrafficService trafficService = new TrafficService();

	@CrossOrigin(origins = "*")
	@GetMapping("/pagination/{page}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get all songs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SongDTO.class) })
	@ResponseBody
	public Page<SongDTO> getAllSongsPagination(@PathVariable("page") int page) {
		logger.info("All songs!");

		page = page - 1;
		Page<SongDTO> listOfSongs = null;
		try {
			listOfSongs = songService.getAllSongsPagination(PageRequest.of(page, 5, new Sort(Direction.ASC, "id")));
		} catch (EmptyListException e) {
			logger.error("There are no songs in DB!");
			e.printStackTrace();
		}

		return listOfSongs;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/top5songs")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get top 5 songs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SongDTO.class) })
	@ResponseBody
	public List<SongDTO> getTop5Songs() {
		logger.info("Top 5 songs!");

		List<SongDTO> listOfTraffic = new ArrayList<SongDTO>();
		try {
			listOfTraffic = songService.getTop5Songs();
		} catch (EmptyListException e) {
			logger.error("Top 5 songs don't exist!");
			e.printStackTrace();
		}

		return listOfTraffic;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/recomended")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Show recomended songs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SongDTO.class) })
	@ResponseBody
	public List<SongDTO> recomended(@RequestHeader("Authorization") String authorization) {
		logger.info("Recomended songs!");

		DecodedJWT decJwt = JWT.decode(authorization.substring(7));
		String user = decJwt.getSubject();

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.recomended(user);
		} catch (UserNotFoundException e) {
			logger.info("Recomended songs don't exist!");
			e.printStackTrace();
		}

		return listOfSongs;

	}

}
