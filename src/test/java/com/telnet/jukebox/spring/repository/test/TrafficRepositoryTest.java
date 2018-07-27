package com.telnet.jukebox.spring.repository.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
import com.telnet.jukebox.spring.repository.TrafficRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrafficRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TrafficRepository trafficRepository;

	@Test
	public void postTraffic() {
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

		User mockUser = new User();
		mockUser.setEmail("mrjmijailovicpz@gmail.com");
		mockUser.setPassword("marija");

		Traffic mockTraffic = new Traffic();
		mockTraffic.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic.setSong(mockSong);
		mockTraffic.setUser(mockUser);

		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre);

		entityManager.persist(mockArtist);

		entityManager.persist(mockSong);

		entityManager.persist(mockUser);

		entityManager.persist(mockTraffic);

		Traffic newTraffic = trafficRepository.save(mockTraffic);

		assertEquals(mockTraffic, newTraffic);
	}

	@Test
	public void recomended() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre1 = new Genre();
		mockGenre1.setName("noviZanr1");

		Genre mockGenre2 = new Genre();
		mockGenre2.setName("noviZanr2");

		Artist mockArtist1 = new Artist();
		mockArtist1.setName("noviZanr1");
		mockArtist1.setGenre(mockGenre1);

		Artist mockArtist2 = new Artist();
		mockArtist2.setName("noviIzvodjac1");
		mockArtist2.setGenre(mockGenre2);

		Artist mockArtist3 = new Artist();
		mockArtist3.setName("noviIzvodjac2");
		mockArtist3.setGenre(mockGenre2);

		Song mockSong1 = new Song();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist3);

		Song mockSong2 = new Song();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist2);

		Song mockSong3 = new Song();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist1);

		Song mockSong4 = new Song();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist2);

		Song mockSong5 = new Song();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist3);

		Song mockSong6 = new Song();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist2);

		User mockUser1 = new User();
		mockUser1.setEmail("mrjmijailovicpz@gmail.com");
		mockUser1.setPassword("marija");

		User mockUser2 = new User();
		mockUser2.setEmail("jmijailovic95@gmail.com");
		mockUser2.setPassword("jelena");

		Traffic mockTraffic1 = new Traffic();
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(mockSong1);
		mockTraffic1.setUser(mockUser1);

		Traffic mockTraffic2 = new Traffic();
		mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic2.setSong(mockSong1);
		mockTraffic2.setUser(mockUser1);

		Traffic mockTraffic3 = new Traffic();
		mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic3.setSong(mockSong1);
		mockTraffic3.setUser(mockUser1);

		Traffic mockTraffic4 = new Traffic();
		mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic4.setSong(mockSong2);
		mockTraffic4.setUser(mockUser1);

		Traffic mockTraffic5 = new Traffic();
		mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic5.setSong(mockSong2);
		mockTraffic5.setUser(mockUser1);

		Traffic mockTraffic6 = new Traffic();
		mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic6.setSong(mockSong2);
		mockTraffic6.setUser(mockUser1);

		Traffic mockTraffic7 = new Traffic();
		mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic7.setSong(mockSong2);
		mockTraffic7.setUser(mockUser1);

		Traffic mockTraffic8 = new Traffic();
		mockTraffic8.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic8.setSong(mockSong3);
		mockTraffic8.setUser(mockUser2);

		Traffic mockTraffic9 = new Traffic();
		mockTraffic9.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic9.setSong(mockSong3);
		mockTraffic9.setUser(mockUser2);

		Traffic mockTraffic10 = new Traffic();
		mockTraffic10.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic10.setSong(mockSong3);
		mockTraffic10.setUser(mockUser2);

		Traffic mockTraffic11 = new Traffic();
		mockTraffic11.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic11.setSong(mockSong4);
		mockTraffic11.setUser(mockUser1);

		Traffic mockTraffic12 = new Traffic();
		mockTraffic12.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic12.setSong(mockSong4);
		mockTraffic12.setUser(mockUser1);

		Traffic mockTraffic13 = new Traffic();
		mockTraffic13.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic13.setSong(mockSong5);
		mockTraffic13.setUser(mockUser1);

		Traffic mockTraffic14 = new Traffic();
		mockTraffic14.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic14.setSong(mockSong6);
		mockTraffic14.setUser(mockUser2);

		Traffic mockTraffic15 = new Traffic();
		mockTraffic15.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic15.setSong(mockSong6);
		mockTraffic15.setUser(mockUser2);

		Traffic mockTraffic16 = new Traffic();
		mockTraffic16.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic16.setSong(mockSong6);
		mockTraffic16.setUser(mockUser2);

		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre1);
		entityManager.persist(mockGenre2);

		System.out.println(mockGenre1);
		System.out.println(mockGenre2);

		entityManager.persist(mockArtist1);
		entityManager.persist(mockArtist2);
		entityManager.persist(mockArtist3);

		entityManager.persist(mockSong1);
		entityManager.persist(mockSong2);
		entityManager.persist(mockSong3);
		entityManager.persist(mockSong4);
		entityManager.persist(mockSong5);
		entityManager.persist(mockSong6);

		entityManager.persist(mockUser1);
		entityManager.persist(mockUser2);

		System.out.println(mockUser1);
		System.out.println(mockUser2);

		entityManager.persist(mockTraffic1);
		entityManager.persist(mockTraffic2);
		entityManager.persist(mockTraffic3);
		entityManager.persist(mockTraffic4);
		entityManager.persist(mockTraffic5);
		entityManager.persist(mockTraffic6);
		entityManager.persist(mockTraffic7);
		entityManager.persist(mockTraffic8);
		entityManager.persist(mockTraffic9);
		entityManager.persist(mockTraffic10);
		entityManager.persist(mockTraffic11);
		entityManager.persist(mockTraffic12);
		entityManager.persist(mockTraffic13);
		entityManager.persist(mockTraffic14);
		entityManager.persist(mockTraffic15);
		entityManager.persist(mockTraffic16);

		Iterable<Traffic> trafficByUser = trafficRepository.findTrafficByUserId((long) 19);
		List<Traffic> trafficList = new ArrayList<>();

		for (Traffic traffic : trafficByUser) {
			trafficList.add(traffic);
		}

		int max = 0;
		Long genre = (long) 0;

		for (int i = 0; i < trafficList.size(); i++) {
			int countSongId = trafficRepository.countBySongId(trafficList.get(i).getSong().getId());
			if (countSongId > max) {
				max = countSongId;
				genre = trafficList.get(i).getSong().getArtist().getGenre().getId();
			}
		}

		assertEquals((long) 9, genre, 0.000001);
	}

}
