package com.telnet.jukebox.spring.repository.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.model.User;
import com.telnet.jukebox.spring.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void postUser() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre = new Genre();
		mockGenre.setName("noviZanr1");

		Artist mockArtist = new Artist();
		mockArtist.setName("noviIzvodjac1");
		mockArtist.setGenre(mockGenre);

		Song mockSong = new Song();
		mockSong.setName("novaPesma1");
		mockSong.setPrice(mockPrice);
		mockSong.setArtist(mockArtist);
		
		User mockUser= new User();
		mockUser.setEmail("mrjmijailovicpz@gmail.com");
		mockUser.setPassword("marija");
		
		Traffic mockTraffic= new Traffic();
		mockTraffic.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic.setSong(mockSong);
		mockTraffic.setUser(mockUser);
		
		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre);

		entityManager.persist(mockArtist);

		entityManager.persist(mockSong);
		
		entityManager.persist(mockUser);
		
		entityManager.persist(mockTraffic);
		
		User newUser= userRepository.save(mockUser);
		
		assertEquals(mockUser, newUser);
	}

}
