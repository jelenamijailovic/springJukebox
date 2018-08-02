package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.GenreService;
import com.telnet.jukebox.spring.service.SongService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/genres")
@Api(value = "/genres", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Genres")
public class GenreResource {

	public static final Logger logger = LogManager.getLogger(GenreResource.class);

	@Autowired
	GenreService genreService;

	@Autowired
	ArtistService artistService;

	@Autowired
	SongService songService;

	@CrossOrigin(origins = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get all genres")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GenreDTO.class) })
	@ResponseBody
	public List<GenreDTO> getAllGenres() {
		logger.info("All genres!");

		List<GenreDTO> genres = new ArrayList<GenreDTO>();
		try {
			genres = genreService.getAllGenres();
		} catch (EmptyListException e) {
			logger.error("There are no genres in DB!");
			e.printStackTrace();
		}

		return genres;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{genreId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Get songs by genre")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SongDTO.class) })
	@ResponseBody
	public List<SongDTO> getSongsByGenre(@PathVariable("genreId") Long genreId) {
		logger.info("Songs by genre: " + genreId + "!");

		List<SongDTO> songs = new ArrayList<SongDTO>();

		try {
			songs = songService.getSongsByGenre(genreId);
		} catch (EmptyListException e) {
			logger.error("Songs by genre: " + genreId + " don't exist!");
			e.printStackTrace();
		}

		return songs;
	}

}
