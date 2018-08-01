package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.SongService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/artists")
@Api(value = "ArtistControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags= "Artists")
public class ArtistResource {

	// final static Logger logger = LogManager.getLogger(ArtistResource.class);

	@Autowired
	ArtistService artistService;

	@Autowired
	SongService songService;

	@CrossOrigin(origins = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get all artists")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = List.class) })
	@ResponseBody
	public List<ArtistDTO> getAllArtists() {

		List<ArtistDTO> listOfArtists = new ArrayList<ArtistDTO>();
		try {
			listOfArtists = artistService.getAllArtists();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return listOfArtists;

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{artistId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get songs by artist")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = List.class) })
	@ResponseBody
	public List<SongDTO> getSongsByArtist(@PathVariable("artistId") Long artistId) {
		// logger.info("Prikaz pesama za izvodjaca sa id-om " + artistId);

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();

		try {
			listOfSongs = songService.getSongsByArtist(artistId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/top5artists")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get top 5 artists")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = List.class) })
	@ResponseBody
	public List<ArtistDTO> getTop5Artists() {
		// logger.info("Prikaz top 5 izvodjaca");

		List<ArtistDTO> listOfTraffic = new ArrayList<ArtistDTO>();
		try {
			listOfTraffic = artistService.getTop5Artists();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}

}
