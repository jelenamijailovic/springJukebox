package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.dto.UserDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.BadLoginException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.Login;
import com.telnet.jukebox.spring.service.TrafficService;
import com.telnet.jukebox.spring.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	// final static Logger logger = Logger.getLogger(KorisnikResource.class);

	@Autowired
	UserService userService = new UserService();

	@Autowired
	TrafficService trafficService = new TrafficService();

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserDTO> getAllUsers() {
		// logger.info("Prikaz svih izvodjaca");

		List<UserDTO> listOfUsers = new ArrayList<UserDTO>();

		try {
			listOfUsers = userService.getAllUsers();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfUsers;
	}

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDTO getUser(@PathVariable Long userId) {
		// logger.info("Prikaz korisnika sa id-om " + userId);

		UserDTO user = new UserDTO();
		try {
			user = userService.getUser(userId);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return user;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(UserDTO user) {
		// logger.info("Unosenje korisnika");

		try {
			userService.addUser(user);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

	}

	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDTO updateUser(@PathVariable Long userId, UserDTO user) {
		user.setId(userId);

		// logger.info("Modifikovanje korisnika sa id-om " + userId);

		UserDTO newUser = new UserDTO();

		try {
			newUser = userService.updateUser(user);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return newUser;
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long userId) {
		// logger.info("Brisanje korisnika sa id-om " + userId);

		try {
			userService.deleteUser(userId);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping("/{userId}/traffic")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TrafficDTO> getSviPrometiPoKorisniku(@PathVariable Long userId) {
		// logger.info("Prikaz pesama za izvodjaca sa id-om " + korisnikId);

		List<TrafficDTO> prometi = new ArrayList<TrafficDTO>();

		try {
			prometi = trafficService.getTrafficByUser(userId);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return prometi;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(Login login) {
		// logger.info("Login korisnika");

		try {
			userService.login(login);
		} catch (BadLoginException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @GET
	 * 
	 * @Path("/getuser") public Response getInfoFromtoken(ContainerRequestContext
	 * requestContex) { // ErrorMessages messages = new
	 * ErrorMessages("Token is not valid",401); Response responses =
	 * Response.status(Status.UNAUTHORIZED).entity("Token is not valid").build();
	 * try { return Response.status(Status.ACCEPTED)
	 * .entity(KorisnikService.getDataFromToken(requestContex.getHeaderString(
	 * "Authorization"))).build();
	 * 
	 * } catch (Exception ex) {
	 * 
	 * throw new WebApplicationException(responses); } }
	 */

}
