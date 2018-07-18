package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.GenreNotFoundException;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.repository.GenreRepository;

@Service
public class GenreService {

	@Autowired
	GenreRepository genreRepository;

	public List<GenreDTO> getAllGenres() throws EmptyListException {
		List<GenreDTO> listOfGenresDTO = new ArrayList<GenreDTO>();

		List<Genre> listOfGenres = new ArrayList<Genre>();

		listOfGenres = genreRepository.findAll();

		if (listOfGenres.isEmpty()) {
			throw new EmptyListException();
		} else {
			for (int i = 0; i < listOfGenres.size(); i++) {
				listOfGenresDTO.add(entityToDTO(listOfGenres.get(i)));
			}

			return listOfGenresDTO;
		}

	}

	public GenreDTO getGenre(Long genreId) throws GenreNotFoundException {
		Genre genre = new Genre();
		genre = genreRepository.findById(genreId).get();

		if (genre.equals(null)) {
			throw new GenreNotFoundException(genreId);
		}

		return entityToDTO(genre);
	}

	public GenreDTO addGenre(GenreDTO genre) throws BadEntryException {
		return entityToDTO(genreRepository.save(DTOToEntity(genre)));
	}

	public GenreDTO updateGenre(GenreDTO zanr) throws BadEntryException {
		return entityToDTO(genreRepository.save(DTOToEntity(zanr)));
	}

	public void deleteGenre(Long genreId) throws GenreNotFoundException {
		genreRepository.deleteById(genreId);
	}

	public Genre DTOToEntity(GenreDTO genre) {
		Genre entity = new Genre();
		entity.setId(genre.getId());
		entity.setName(genre.getName());
		return entity;
	}

	public GenreDTO entityToDTO(Genre genre) {
		GenreDTO dto = new GenreDTO();
		dto.setId(genre.getId());
		dto.setName(genre.getName());
		return dto;
	}
}
