package com.telnet.jukebox.spring.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.service.SongService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongServiceTest {
	
	@Autowired
	SongService songService;
	
	@Test
	public void entityToDTO() {
		Genre mockGenre = new Genre();
		mockGenre.setId((long) 1);
		mockGenre.setName("noviZanr");
		
		Artist mockArtist = new Artist();
		mockArtist.setId((long) 1);
		mockArtist.setName("noviIzvodjac");
		mockArtist.setGenre(mockGenre);
		
		Price mockPrice = new Price();
		mockPrice.setId((long) 1);
		mockPrice.setPrice((long) 50);
		
		Song mockSong = new Song();
		mockSong.setId((long) 1);
		mockSong.setName("novaPesma");
		mockSong.setArtist(mockArtist);
		mockSong.setPrice(mockPrice);
		
		SongDTO mockSongDTO= songService.entityToDTO(mockSong);
		
		assertEquals(mockSong.getId(), mockSongDTO.getId());
		assertEquals(mockSong.getName(), mockSongDTO.getName());
		assertEquals(mockSong.getArtist(), mockSongDTO.getArtist());
		assertEquals(mockSong.getPrice(), mockSongDTO.getPrice());

	}

}
