package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.exceptions.ArtistNotFoundException;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	ArtistRepository artistRepository;

	public List<ArtistDTO> getAllArtists() throws EmptyListException {
		List<ArtistDTO> listOfArtistsDTO = new ArrayList<ArtistDTO>();

		List<Artist> listOfArtists = new ArrayList<Artist>();

		listOfArtists = artistRepository.findAll();

		if (listOfArtists.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfArtists.size(); i++) {
				listOfArtistsDTO.add(entityToDTO(listOfArtists.get(i)));
			}

			return listOfArtistsDTO;
		}
	}

	public List<ArtistDTO> getArtistsByGenre(Long genreId) throws EmptyListException {
		List<ArtistDTO> listOfArtistsDTO = new ArrayList<ArtistDTO>();

		List<Artist> listOfArtists = new ArrayList<Artist>();

		listOfArtists = artistRepository.findArtistsByGenre(genreId);

		if (listOfArtists.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfArtists.size(); i++) {
				listOfArtistsDTO.add(entityToDTO(listOfArtists.get(i)));
			}

			return listOfArtistsDTO;
		}
	}

	public ArtistDTO getArtist(Long artistId) throws ArtistNotFoundException {
		Artist artist = new Artist();
		artist = artistRepository.findById(artistId).get();

		if (artist.equals(null)) {
			throw new ArtistNotFoundException(artistId);
		} else {
			return entityToDTO(artist);
		}

	}

	public ArtistDTO addArtist(ArtistDTO artist) throws BadEntryException {
		return entityToDTO(artistRepository.save(DTOToEntity(artist)));
	}

	public ArtistDTO updateArtist(ArtistDTO artist) throws BadEntryException {
		return entityToDTO(artistRepository.save(DTOToEntity(artist)));
	}

	public void deleteArtist(Long id) throws ArtistNotFoundException {
		artistRepository.deleteById(id);
	}

	public Artist DTOToEntity(ArtistDTO artist) {
		Artist entity = new Artist();
		entity.setId(artist.getId());
		entity.setName(artist.getName());
		return entity;
	}

	public ArtistDTO entityToDTO(Artist artist) {
		ArtistDTO dto = new ArtistDTO();
		dto.setId(artist.getId());
		dto.setName(artist.getName());
		return dto;
	}
}
