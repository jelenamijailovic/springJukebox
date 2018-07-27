package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configurers.ChannelSecurityConfigurer.RequiresChannelUrl;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.exceptions.TrafficNotFoundException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.User;
import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.JWT;

/*import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;*/

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Header;

@RestController
@RequestMapping("/traffic")
public class TrafficResource {

	// final static Logger logger = Logger.getLogger(PrometResource.class);

	@Autowired
	public TrafficService trafficService;

	@Autowired
	public SongService songService;

	  @CrossOrigin(origins= "*")
	  @GetMapping
	  @ResponseStatus(HttpStatus.OK)
	  @ResponseBody public List<TrafficDTO> getSviPrometi() { //
	 //logger.info("Prikaz svih prometa");
	  
	  List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>(); try {
	  listOfTrafficDTO = trafficService.getAllTraffic(); 
	  } catch(EmptyListException e) {
		  e.printStackTrace();
	  }
	 
	  return listOfTrafficDTO; }
	  
	 /*@GetMapping("/{trafficId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public TrafficDTO getPromet(@PathVariable Long trafficId) { //
	 * logger.info("Prikaz prometa sa id-om " + trafficId);
	 * 
	 * TrafficDTO traffic = new TrafficDTO(); try { traffic =
	 * trafficService.getTraffic(trafficId); } catch (TrafficNotFoundException e) {
	 * e.printStackTrace(); }
	 * 
	 * return traffic; }
	 */

	  
	@CrossOrigin(origins= "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrafficDTO addTraffic(@RequestHeader("Authorization") String authorization, @RequestBody TrafficDTO traffic) {
		// logger.info("Unosenje prometa");
		
		System.out.println(authorization); 
		
		// logger.info(authorization);
	//	JWTVerifier.init(Algorithm.HMAC256(secret));
		//Jws<Claims> claims = Jwts.parser().setSigningKey("password".getBytes()).parseClaimsJws(authorization);
		
		JWTParser jwtParser= new  JWTParser();
		Long id =Long.parseLong(jwtParser.parsePayload(authorization).getIssuer()); 
		//Long id = claims.getHeaderClaim("id").asLong();
		traffic.setUser(new User(id));

		System.out.println("User id from token is  :" + id);

		try {
			return trafficService.addTraffic(traffic);
		} catch (ExpTokenException e) {
			e.printStackTrace();
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*@DeleteMapping("/{trafficId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTraffic(@PathVariable Long trafficId) {
		// logger.info("Brisanje prometa sa id-om " + prometId);

		try {
			trafficService.deleteTraffic(trafficId);
		} catch (TrafficNotFoundException e) {
			e.printStackTrace();
		}

	}*/

	/*@CrossOrigin(origins= "*")
	@GetMapping("/recomended")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> recomended(@RequestHeader("Authorization") String authorization) {
		// logger.info("Preporucujemo");

		Jws<Claims> claims = Jwts.parser().setSigningKey("password".getBytes()).parseClaimsJws(authorization);

		Long id = (Long) claims.getBody().get("id");

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.recomended(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return listOfSongs;

	}*/

}
