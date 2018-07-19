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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.GenreNotFoundException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.GenreService;
import com.telnet.jukebox.spring.service.SongService;

@RestController
@RequestMapping("/genres")
public class GenreResource {

	// final static Logger logger = Logger.getLogger(GenreResource.class);

	@Autowired
	GenreService genreService;

	@Autowired
	ArtistService artistService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<GenreDTO> getAllGenres() {
		// logger.info("Prikaz svih zanrova");

		List<GenreDTO> genres = new ArrayList<GenreDTO>();
		try {
			genres = genreService.getAllGenres();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return genres;
	}

	@GetMapping("/{genreId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public GenreDTO getGenre(@PathVariable Long genreId) {
		// logger.info("Prikaz zanra sa id-om " + genreId);

		GenreDTO genre = new GenreDTO();
		try {
			genre = genreService.getGenre(genreId);
		} catch (GenreNotFoundException e) {
			e.printStackTrace();
		}

		return genre;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addGenre(@RequestBody GenreDTO genre) {
		// logger.info("Unosenje zanra");

		try {
			genreService.addGenre(genre);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

	}

	@PutMapping("/{genreId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public GenreDTO updateZanr(@PathVariable Long genreId, @RequestBody GenreDTO genre) {
		genre.setId(genreId);

		// logger.info("Modifikovanje zanra sa id-om " + genreId);

		GenreDTO newGenre = new GenreDTO();

		try {
			newGenre = genreService.updateGenre(genre);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return newGenre;
	}

	@DeleteMapping("/{genreId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteZanr(@PathVariable Long genreId) {
		// logger.info("Brisanje zanra sa id-om " + genreId);

		try {
			genreService.deleteGenre(genreId);
		} catch (GenreNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/{genreId}/artists")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ArtistDTO> getArtistsByGenre(@PathVariable Long genreId) {
		// logger.info("Prikaz pesama za izvodjaca sa id-om " + artistId);

		List<ArtistDTO> listOfArtists= new ArrayList<ArtistDTO>();
		try {
			listOfArtists = artistService.getArtistsByGenre(genreId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfArtists;
	}

	/*@GetMapping("/{genreId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getSvePesmePoZanru(@PathVariable Long genreId) {
		// logger.info("Prikaz pesama za zanr sa id-om " + genreId);

		List<SongDTO> songs = new ArrayList<SongDTO>();
		try {
			songs = songService.getSongsByGenre(genreId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return songs;
	}*/
}
