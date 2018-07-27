package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
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

	public GenreDTO entityToDTO(Genre genre) {
		GenreDTO dto = new GenreDTO();
		dto.setId(genre.getId());
		dto.setName(genre.getName());
		return dto;
	}
}
