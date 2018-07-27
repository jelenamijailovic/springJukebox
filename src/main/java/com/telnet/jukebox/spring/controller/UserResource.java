package com.telnet.jukebox.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.UserDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.BadLoginException;
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

	/*
	 * @GetMapping
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public List<UserDTO> getAllUsers() { //
	 * logger.info("Prikaz svih izvodjaca");
	 * 
	 * List<UserDTO> listOfUsers = new ArrayList<UserDTO>();
	 * 
	 * try { listOfUsers = userService.getAllUsers(); } catch (EmptyListException e)
	 * { e.printStackTrace(); }
	 * 
	 * return listOfUsers; }
	 * 
	 * @GetMapping("/{userId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public UserDTO getUser(@PathVariable Long userId) { //
	 * logger.info("Prikaz korisnika sa id-om " + userId);
	 * 
	 * UserDTO user = new UserDTO(); try { user = userService.getUser(userId); }
	 * catch (UserNotFoundException e) { e.printStackTrace(); }
	 * 
	 * return user; }
	 */

	@CrossOrigin(origins= "*")
	@PostMapping
	@PreAuthorize("hasAuthority('add:users')")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO addUser(@RequestBody UserDTO user) {
		// logger.info("Unosenje korisnika");

		try {
			return userService.addUser(user);
		} catch (BadEntryException e) {
			e.printStackTrace();
			return null;
		}
		 

	}
/*
	@CrossOrigin(origins= "*")
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public String login(@RequestBody Login login) {
		// logger.info("Login korisnika");
		String jwt= "";
		try {
			jwt= userService.login(login);
		} catch (BadLoginException e) {
			e.printStackTrace();
		}
		return jwt;
	}*/

	/*
	 * @GetMapping("/{userId}/traffic")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public List<TrafficDTO> getSviPrometiPoKorisniku(@PathVariable
	 * Long userId) { // logger.info("Prikaz pesama za izvodjaca sa id-om " +
	 * korisnikId);
	 * 
	 * List<TrafficDTO> prometi = new ArrayList<TrafficDTO>();
	 * 
	 * try { prometi = trafficService.getTrafficByUser(userId); } catch
	 * (UserNotFoundException e) { e.printStackTrace(); }
	 * 
	 * return prometi; }
	 */

	/*
	 * @PutMapping("/{userId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public UserDTO updateUser(@PathVariable Long userId, UserDTO
	 * user) { user.setId(userId);
	 * 
	 * // logger.info("Modifikovanje korisnika sa id-om " + userId);
	 * 
	 * UserDTO newUser = new UserDTO();
	 * 
	 * try { newUser = userService.updateUser(user); } catch (BadEntryException e) {
	 * e.printStackTrace(); }
	 * 
	 * return newUser; }
	 * 
	 * @DeleteMapping("/{userId}")
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteUser(@PathVariable
	 * Long userId) { // logger.info("Brisanje korisnika sa id-om " + userId);
	 * 
	 * try { userService.deleteUser(userId); } catch (UserNotFoundException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}
