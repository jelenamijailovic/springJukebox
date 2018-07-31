package com.telnet.jukebox.spring.repository.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.repository.ArtistRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArtistRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ArtistRepository artistRepository;

	@Test
	public void getGenres() {
		Genre mockGenre1 = new Genre();
		mockGenre1.setName("noviZanr1");

		Genre mockGenre2 = new Genre();
		mockGenre2.setName("noviZanr2");

		Artist mockArtist1 = new Artist();
		mockArtist1.setName("noviZanr1");
		mockArtist1.setGenre(mockGenre1);

		Artist mockArtist2 = new Artist();
		mockArtist2.setName("noviZanr2");
		mockArtist2.setGenre(mockGenre2);

		entityManager.persist(mockGenre1);
		entityManager.persist(mockGenre2);

		entityManager.persist(mockArtist1);
		entityManager.persist(mockArtist2);

		Iterable<Artist> allArtists = artistRepository.findAll();
		List<Artist> artistList = new ArrayList<>();

		for (Artist artist : allArtists) {
			artistList.add(artist);
		}

		assertEquals(artistList, allArtists);
	}

	@Test
	public void getTop5Songs() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre = new Genre();
		mockGenre.setName("noviZanr1");

		Artist mockArtist1 = new Artist();
		mockArtist1.setName("noviZanr1");
		mockArtist1.setGenre(mockGenre);

		Artist mockArtist2 = new Artist();
		mockArtist2.setName("noviZanr2");
		mockArtist2.setGenre(mockGenre);

		Artist mockArtist3 = new Artist();
		mockArtist3.setName("noviZanr3");
		mockArtist3.setGenre(mockGenre);

		Artist mockArtist4 = new Artist();
		mockArtist4.setName("noviZanr4");
		mockArtist4.setGenre(mockGenre);

		Artist mockArtist5 = new Artist();
		mockArtist5.setName("noviZanr5");
		mockArtist5.setGenre(mockGenre);

		Artist mockArtist6 = new Artist();
		mockArtist6.setName("noviZanr6");
		mockArtist6.setGenre(mockGenre);

		Song mockSong1 = new Song();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist1);

		Song mockSong2 = new Song();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist2);

		Song mockSong3 = new Song();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist3);

		Song mockSong4 = new Song();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist4);

		Song mockSong5 = new Song();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist5);

		Song mockSong6 = new Song();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist6);

		Traffic mockTraffic1 = new Traffic();
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(mockSong1);
		mockTraffic1.setUser("auth0|123456789");

		Traffic mockTraffic2 = new Traffic();
		mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic2.setSong(mockSong1);
		mockTraffic2.setUser("auth0|123456789");

		Traffic mockTraffic3 = new Traffic();
		mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic3.setSong(mockSong1);
		mockTraffic3.setUser("auth0|123456789");

		Traffic mockTraffic4 = new Traffic();
		mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic4.setSong(mockSong2);
		mockTraffic4.setUser("auth0|123456789");

		Traffic mockTraffic5 = new Traffic();
		mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic5.setSong(mockSong2);
		mockTraffic5.setUser("auth0|123456789");

		Traffic mockTraffic6 = new Traffic();
		mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic6.setSong(mockSong2);
		mockTraffic6.setUser("auth0|123456789");

		Traffic mockTraffic7 = new Traffic();
		mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic7.setSong(mockSong2);
		mockTraffic7.setUser("auth0|123456789");

		Traffic mockTraffic8 = new Traffic();
		mockTraffic8.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic8.setSong(mockSong3);
		mockTraffic8.setUser("auth0|123456789");

		Traffic mockTraffic9 = new Traffic();
		mockTraffic9.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic9.setSong(mockSong3);
		mockTraffic9.setUser("auth0|123456789");

		Traffic mockTraffic10 = new Traffic();
		mockTraffic10.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic10.setSong(mockSong3);
		mockTraffic10.setUser("auth0|123456789");

		Traffic mockTraffic11 = new Traffic();
		mockTraffic11.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic11.setSong(mockSong4);
		mockTraffic11.setUser("auth0|123456789");

		Traffic mockTraffic12 = new Traffic();
		mockTraffic12.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic12.setSong(mockSong4);
		mockTraffic12.setUser("auth0|123456789");

		Traffic mockTraffic13 = new Traffic();
		mockTraffic13.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic13.setSong(mockSong5);
		mockTraffic13.setUser("auth0|123456789");

		Traffic mockTraffic14 = new Traffic();
		mockTraffic14.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic14.setSong(mockSong6);
		mockTraffic14.setUser("auth0|123456789");

		Traffic mockTraffic15 = new Traffic();
		mockTraffic15.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic15.setSong(mockSong6);
		mockTraffic15.setUser("auth0|123456789");

		Traffic mockTraffic16 = new Traffic();
		mockTraffic16.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic16.setSong(mockSong6);
		mockTraffic16.setUser("auth0|123456789");

		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre);

		entityManager.persist(mockArtist1);
		entityManager.persist(mockArtist2);
		entityManager.persist(mockArtist3);
		entityManager.persist(mockArtist4);
		entityManager.persist(mockArtist5);
		entityManager.persist(mockArtist6);

		entityManager.persist(mockSong1);
		entityManager.persist(mockSong2);
		entityManager.persist(mockSong3);
		entityManager.persist(mockSong4);
		entityManager.persist(mockSong5);
		entityManager.persist(mockSong6);

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

		Iterable<Artist> allArtists = artistRepository.findTop5Artists(PageRequest.of(0, 5));
		List<Artist> artistList = new ArrayList<>();

		for (Artist song : allArtists) {
			artistList.add(song);
		}

		Assert.assertEquals(artistList.get(0), mockArtist2);
		Assert.assertEquals(artistList.size(), 5);
		Assert.assertEquals(artistList.get(4), mockArtist4);
	}

}
