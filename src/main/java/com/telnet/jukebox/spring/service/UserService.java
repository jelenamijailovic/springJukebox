package com.telnet.jukebox.spring.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.UserDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.BadLoginException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.Login;
import com.telnet.jukebox.spring.model.User;
import com.telnet.jukebox.spring.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<UserDTO> getAllUsers() throws EmptyListException {
		List<UserDTO> listOfUsersDTO = new ArrayList<UserDTO>();

		List<User> listOfUsers = new ArrayList<User>();

		listOfUsers = userRepository.findAll();

		if (listOfUsers.isEmpty()) {
			throw new EmptyListException();
		} else {
			for (int i = 0; i < listOfUsers.size(); i++) {
				listOfUsersDTO.add(entityToDTO(listOfUsers.get(i)));
			}
			return listOfUsersDTO;
		}

	}

	public UserDTO getUser(Long userId) throws UserNotFoundException {
		User user = new User();

		user = userRepository.findById(userId).get();

		if (user.equals(null)) {
			throw new UserNotFoundException(userId);
		} else {
			UserDTO userDTO = entityToDTO(user);
			userDTO.setPassword(encryptPassword(userDTO.getPassword()));

			return userDTO;
		}
	}

	public UserDTO addUser(UserDTO user) throws BadEntryException {
		return entityToDTO(userRepository.save(DTOToEntity(user)));
	}

	public UserDTO updateUser(UserDTO user) throws BadEntryException {
		UserDTO newUser = entityToDTO(userRepository.save(DTOToEntity(user)));
		newUser.setPassword(encryptPassword(newUser.getPassword()));
		return newUser;
	}

	public void deleteUser(Long userId) throws UserNotFoundException {
		userRepository.deleteById(userId);
	}

	public User loginUser(Login login) throws BadLoginException {
		User user = new User();
		String newPassword = encryptPassword(login.getPassword());

		user = userRepository.findUserByEmail(login.getEmail());

		if (user.equals(null)) {
			throw new BadLoginException();
		} else {

			String existingPassword = user.getPassword();

			if (existingPassword.equals(newPassword)) {
				user.setPassword(login.getPassword());
			} else {
				// System.out.println("Pogresna sifra");
				user.setPassword(null);
				user.setEmail(null);
			}
		}

		return user;
	}

	private static String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public String login(Login login) throws BadLoginException {
		UserDTO korisnik = entityToDTO(loginUser(login));

		String jwt = "";

		if (korisnik.getEmail() != null) {
			Long time = System.currentTimeMillis();

			jwt = Jwts.builder().claim("email", korisnik.getEmail()).claim("id", korisnik.getId())
					.setExpiration(new Date(time + 600000000)).signWith(SignatureAlgorithm.HS256, "sifra".getBytes())
					.compact();

			return jwt;
		}

		return jwt;
	}

	public User DTOToEntity(UserDTO user) {
		User entity = new User();
		entity.setId(user.getId());
		entity.setPassword(user.getPassword());
		entity.setEmail(user.getEmail());
		return entity;
	}

	public UserDTO entityToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setPassword(user.getPassword());
		dto.setEmail(user.getEmail());
		return dto;
	}

}
