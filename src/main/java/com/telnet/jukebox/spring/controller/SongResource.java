package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.telnet.jukebox.spring.exceptions.SongNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/songs")
public class SongResource {

	// final static Logger logger = Logger.getLogger(PesmaResource.class);

	@Autowired
	SongService songService = new SongService();

	@Autowired
	TrafficService trafficService = new TrafficService();

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getAllSongs() {
		// logger.info("Prikaz svih pesama");

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.getAllSongs();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

/*	@GetMapping("/pagination/{page}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getAllSongsPagination(@PathVariable int page) {
		// logger.info("Prikaz svih pesama");

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.getAllSongsPagination(page);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}
*/
	
	/*@GetMapping("/list")
	public String userList(Model model, Pageable pageable) {
			Page<SongDTO> pages;
			try {
				pages = songService.songList(pageable);
				model.addAttribute("number", pages.getNumber());
				model.addAttribute("totalPages", pages.getTotalPages());
				model.addAttribute("totalElements",       
		                                      pages.getTotalElements());
				model.addAttribute("size", pages.getSize());
				model.addAttribute("users", pages.getContent());
				return "/song/list";
			} catch (EmptyListException e) {
				e.printStackTrace();
				return null;
			}
			
	}*/
	
	@GetMapping("/{songId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SongDTO getSong(@PathVariable Long songId) {
		// logger.info("Prikaz pesme sa id-om " + pesmaId);

		SongDTO song = new SongDTO();
		try {
			song = songService.getSong(songId);
		} catch (SongNotFoundException e) {
			e.printStackTrace();
		}

		return song;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addSong(@RequestBody SongDTO song) {
		// logger.info("Unosenje pesme");

		try {
			songService.addSong(song);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

	}

	@PutMapping("/{songId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SongDTO updatePesma(@PathVariable Long songId, @RequestBody SongDTO song) {
		song.setId(songId);

		// logger.info("Modifikovanje pesme sa id-om " + pesmaId);

		SongDTO newSong = new SongDTO();
		try {
			newSong = songService.updateSong(song);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return newSong;
	}

	@DeleteMapping("/{songId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePesma(@PathVariable Long songId) {
		// logger.info("Brisanje pesme sa id-om " + pesmaId);

		try {
			songService.deleteSong(songId);
		} catch (SongNotFoundException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/{songId}/traffic")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TrafficDTO> getTrafficBySong(@PathVariable Long songId) {
		// logger.info("Prikaz prometa za pesmu sa id-om " + pesmaId);

		List<TrafficDTO> listOfTraffic = new ArrayList<TrafficDTO>();
		try {
			listOfTraffic = trafficService.getTrafficBySong(songId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}

}
