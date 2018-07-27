package com.telnet.jukebox.spring.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.service.GenreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreServiceTest {
	
	@Autowired
	GenreService genreService;
	
	@Test
	public void entityToDTO() {
		Genre mockGenre = new Genre();
		mockGenre.setId((long) 1);
		mockGenre.setName("noviZanr1");
		
		GenreDTO mockGenreDTO= genreService.entityToDTO(mockGenre);
		
		assertEquals(mockGenre.getId(), mockGenreDTO.getId());
		assertEquals(mockGenre.getName(), mockGenreDTO.getName());

	}

}
