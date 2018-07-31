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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.repository.ArtistRepository;
import com.telnet.jukebox.spring.repository.GenreRepository;
import com.telnet.jukebox.spring.repository.SongRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SongRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ArtistRepository artistRepository;

	@Test
	public void getSongs() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre = new Genre();
		mockGenre.setName("noviZanr1");

		Artist mockArtist = new Artist();
		mockArtist.setName("noviZanr1");
		mockArtist.setGenre(mockGenre);

		Song mockSong1 = new Song();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist);

		Song mockSong2 = new Song();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist);

		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre);

		entityManager.persist(mockArtist);

		entityManager.persist(mockSong1);
		entityManager.persist(mockSong2);

		Iterable<Song> allSongs = songRepository.findAll(PageRequest.of(0, 5, new Sort(Direction.ASC, "id")));
		List<Song> songList = new ArrayList<>();

		for (Song artist : allSongs) {
			songList.add(artist);
		}
		
		Page<Song> pageOfSongs= new PageImpl<Song>(songList, PageRequest.of(0, 5, new Sort(Direction.ASC, "id")), 2);
		
		assertEquals(pageOfSongs, allSongs);
	}

	@Test
	public void getSongsByGenreAndByArtist() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre1 = new Genre();
		mockGenre1.setName("noviZanr1");

		Artist mockArtist1 = new Artist();
		Artist mockArtist2 = new Artist();

		mockArtist1.setName("noviIzvodjac1");
		mockArtist1.setGenre(mockGenre1);

		mockArtist2.setName("noviIzvodjac2");
		mockArtist2.setGenre(mockGenre1);

		List<Artist> mockArtistList = new ArrayList<>();
		mockArtistList.add(mockArtist1);
		mockArtistList.add(mockArtist2);

		mockGenre1.setArtists(mockArtistList);

		Song mockSong1 = new Song();
		Song mockSong2 = new Song();

		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist1);

		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist2);

		List<Song> mockSongList = new ArrayList<>();
		mockSongList.add(mockSong1);
		mockSongList.add(mockSong2);

		mockArtist1.setSongs(mockSongList);

		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre1);

		entityManager.persist(mockArtist1);
		entityManager.persist(mockArtist2);

		entityManager.persist(mockSong1);
		entityManager.persist(mockSong2);

		Iterable<Song> allSongsByGenre = genreRepository.findById((long) 2).get().getArtists().get(0).getSongs();
		Iterable<Song> allSongsByArtist = artistRepository.findById((long) 3).get().getSongs();
		
		List<Song> songListGenre = new ArrayList<>();
		List<Song> songListArtist = new ArrayList<>();

		for (Song song : allSongsByGenre) {
			songListGenre.add(song);
		}
		
		for (Song song : allSongsByArtist) {
			songListArtist.add(song);
		}
		
		assertEquals(songListGenre, allSongsByGenre);
		assertEquals(songListArtist, allSongsByArtist);
	}
	
	@Test
	public void getTop5Songs() {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre = new Genre();
		mockGenre.setName("noviZanr1");

		Artist mockArtist = new Artist();
		mockArtist.setName("noviZanr1");
		mockArtist.setGenre(mockGenre);

		Song mockSong1 = new Song();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist);

		Song mockSong2 = new Song();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist);
		
		Song mockSong3 = new Song();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist);

		Song mockSong4 = new Song();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist);
		
		Song mockSong5 = new Song();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist);

		Song mockSong6 = new Song();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist);
		
		Traffic mockTraffic1= new Traffic();
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(mockSong1);
		mockTraffic1.setUser("auth0|123456789");
		
		Traffic mockTraffic2= new Traffic();
		mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic2.setSong(mockSong1);
		mockTraffic2.setUser("auth0|123456789");
		
		Traffic mockTraffic3= new Traffic();
		mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic3.setSong(mockSong1);
		mockTraffic3.setUser("auth0|123456789");
		
		Traffic mockTraffic4= new Traffic();
		mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic4.setSong(mockSong2);
		mockTraffic4.setUser("auth0|123456789");
		
		Traffic mockTraffic5= new Traffic();
		mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic5.setSong(mockSong2);
		mockTraffic5.setUser("auth0|123456789");
		
		Traffic mockTraffic6= new Traffic();
		mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic6.setSong(mockSong2);
		mockTraffic6.setUser("auth0|123456789");

		Traffic mockTraffic7= new Traffic();
		mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic7.setSong(mockSong2);
		mockTraffic7.setUser("auth0|123456789");
		
		Traffic mockTraffic8= new Traffic();
		mockTraffic8.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic8.setSong(mockSong3);
		mockTraffic8.setUser("auth0|123456789");
		
		Traffic mockTraffic9= new Traffic();
		mockTraffic9.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic9.setSong(mockSong3);
		mockTraffic9.setUser("auth0|123456789");
		
		Traffic mockTraffic10= new Traffic();
		mockTraffic10.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic10.setSong(mockSong3);
		mockTraffic10.setUser("auth0|123456789");
		
		Traffic mockTraffic11= new Traffic();
		mockTraffic11.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic11.setSong(mockSong4);
		mockTraffic11.setUser("auth0|123456789");
		
		Traffic mockTraffic12= new Traffic();
		mockTraffic12.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic12.setSong(mockSong4);
		mockTraffic12.setUser("auth0|123456789");
		
		Traffic mockTraffic13= new Traffic();
		mockTraffic13.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic13.setSong(mockSong5);
		mockTraffic13.setUser("auth0|123456789");
		
		Traffic mockTraffic14= new Traffic();
		mockTraffic14.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic14.setSong(mockSong6);
		mockTraffic14.setUser("auth0|123456789");
		
		Traffic mockTraffic15= new Traffic();
		mockTraffic15.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic15.setSong(mockSong6);
		mockTraffic15.setUser("auth0|123456789");
		
		Traffic mockTraffic16= new Traffic();
		mockTraffic16.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic16.setSong(mockSong6);
		mockTraffic16.setUser("auth0|123456789");
		
		entityManager.persist(mockPrice);

		entityManager.persist(mockGenre);

		entityManager.persist(mockArtist);

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
		
		Iterable<Song> allSongs = songRepository.findTop5Songs(PageRequest.of(0, 5));
		List<Song> songList = new ArrayList<>();

		for (Song song : allSongs) {
			songList.add(song);
		}
		
		Assert.assertEquals(songList.get(0), mockSong2);
		Assert.assertEquals(songList.size(), 5);
		Assert.assertEquals(songList.get(4), mockSong4);
	}

}
