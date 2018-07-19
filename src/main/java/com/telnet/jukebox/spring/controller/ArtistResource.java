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
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.ArtistNotFoundException;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.SongService;

@RestController
@RequestMapping("/artists")
public class ArtistResource {

	// final static Logger logger = Logger.getLogger(ZanrResource.class);

	@Autowired
	ArtistService artistService;

	@Autowired
	SongService songService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
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

	@GetMapping("/{artistId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArtistDTO getArtist(@PathVariable Long artistId) {

		ArtistDTO artist = new ArtistDTO();
		try {
			artist = artistService.getArtist(artistId);
		} catch (ArtistNotFoundException e) {
			e.printStackTrace();
		}
		return artist;

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addArtist(@RequestBody ArtistDTO artist) {
		// logger.info("Unosenje izvodjaca");

		try {
			artistService.addArtist(artist);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}
		// logger.info("Izvodjac je uspesno unet");

	}

	@PutMapping("/{artistId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArtistDTO updateArtist(@PathVariable Long artistId, @RequestBody ArtistDTO artist) {
		artist.setId(artistId);

		// logger.info("Modifikovanje izvodjaca sa id-om " + artistId);

		ArtistDTO newArtist = new ArtistDTO();
		try {
			newArtist = artistService.updateArtist(artist);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return newArtist;
	}

	@DeleteMapping("/{artistId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteIzvodjac(@PathVariable Long artistId) {
		// logger.info("Brisanje izvodjaca sa id-om " + artistId);

		try {
			artistService.deleteArtist(artistId);
		} catch (ArtistNotFoundException e) {
			e.printStackTrace();
		}
		// logger.info("Izvodjac sa id-om " + artistId + " je uspesno obrisan");

	}

	@GetMapping("/{artistId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getSongsByArtist(@PathVariable Long artistId) {
		// logger.info("Prikaz pesama za izvodjaca sa id-om " + artistId);

		List<SongDTO> listOfSongs= new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.getSongsByArtist(artistId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

}
