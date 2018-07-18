package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.exceptions.TrafficNotFoundException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.User;
import com.telnet.jukebox.spring.service.TrafficService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/traffic")
public class TrafficResource {

	// final static Logger logger = Logger.getLogger(PrometResource.class);

	@Autowired
	public TrafficService trafficService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
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

	@GetMapping("/{trafficId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TrafficDTO getPromet(@PathVariable Long trafficId) {
		// logger.info("Prikaz prometa sa id-om " + trafficId);

		TrafficDTO traffic = new TrafficDTO();
		try {
			traffic = trafficService.getTraffic(trafficId);
		} catch (TrafficNotFoundException e) {
			e.printStackTrace();
		}

		return traffic;
	}

	/*@GetMapping("/top5songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TrafficDTO> getTop5Songs() {
		// logger.info("Prikaz top 5 pesama");

		List<TrafficDTO> listOfTraffic = new ArrayList<TrafficDTO>();
		try {
			listOfTraffic = trafficService.getTop5Songs();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}

	@GetMapping("/top5artists")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TrafficDTO> getTop5Artists() {
		// logger.info("Prikaz top 5 izvodjaca");

		List<TrafficDTO> listOfTraffic = new ArrayList<TrafficDTO>();
		try {
			listOfTraffic = trafficService.getTop5Artists();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}*/

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addTraffic(@RequestHeader("Authorization") String authorization, @RequestBody TrafficDTO traffic) {
		// logger.info("Unosenje prometa");

		// String authenticationheader =
		// requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println(authorization);
		// logger.info(authorization);

		Jws<Claims> claims = Jwts.parser().setSigningKey("sifra".getBytes()).parseClaimsJws(authorization);

		Long id = (Long) claims.getBody().get("id");
		traffic.setUser(new User(id));

		System.out.println("User id from token is  :" + id);

		try {
			trafficService.addTraffic(traffic);
		} catch (ExpTokenException e) {
			e.printStackTrace();
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

	}

	@DeleteMapping("/{trafficId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTraffic(@PathVariable Long trafficId) {
		// logger.info("Brisanje prometa sa id-om " + prometId);

		try {
			trafficService.deleteTraffic(trafficId);
		} catch (TrafficNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@GetMapping("/recomended")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TrafficDTO> recomended(@RequestHeader("Authorization") String authorization) {
		// logger.info("Preporucujemo");

		Jws<Claims> claims = Jwts.parser().setSigningKey("password".getBytes()).parseClaimsJws(authorization);

		Long id = (Long) claims.getBody().get("id");

		List<TrafficDTO> listOfTraffic = new ArrayList<TrafficDTO>();
		try {
			listOfTraffic = trafficService.recomended(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return listOfTraffic;

	}

}
