package com.telnet.jukebox.spring.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.service.TrafficService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrafficServiceTest {

	@Autowired
	TrafficService trafficService;

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

		Traffic mockTraffic = new Traffic();
		mockTraffic.setId((long) 1);
		mockTraffic.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic.setSong(mockSong);
		mockTraffic.setUser("auth0|123456789");

		TrafficDTO mockTrafficDTO = trafficService.entityToDTO(mockTraffic);

		assertEquals(mockTraffic.getId(), mockTrafficDTO.getId());
		assertEquals(mockTraffic.getDate(), mockTrafficDTO.getDate());
		assertEquals(mockTraffic.getSong(), mockTrafficDTO.getSong());
		assertEquals(mockTraffic.getUser(), mockTrafficDTO.getUser());

	}

}
