package com.telnet.jukebox.spring.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.service.ArtistService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistServiceTest {

	@Autowired
	ArtistService artistService;

	@Test
	public void entityToDTO() {
		Genre mockGenre = new Genre();
		mockGenre.setId((long) 1);
		mockGenre.setName("noviZanr");

		Artist mockArtist = new Artist();
		mockArtist.setId((long) 1);
		mockArtist.setName("noviIzvodjac");
		mockArtist.setGenre(mockGenre);

		ArtistDTO mockArtistDTO = artistService.entityToDTO(mockArtist);

		assertEquals(mockArtist.getId(), mockArtistDTO.getId());
		assertEquals(mockArtist.getName(), mockArtistDTO.getName());
		assertEquals(mockArtist.getGenre().getId(), mockArtistDTO.getGenre().getId());

	}

}
