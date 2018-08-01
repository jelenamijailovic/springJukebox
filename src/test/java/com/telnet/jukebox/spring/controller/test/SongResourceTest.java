package com.telnet.jukebox.spring.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.controller.SongResource;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SongResource.class, secure = false)
public class SongResourceTest {

	private static final int PAGE = 1;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SongResource songResource;

	@Test
	public void getSongs() throws Exception {
		Genre mockGenre = new Genre();
		mockGenre.setId((long) 1);
		mockGenre.setName("genre");

		Artist mockArtist = new Artist();
		mockArtist.setId((long) 1);
		mockArtist.setName("artist");
		mockArtist.setGenre(mockGenre);

		Price mockPrice = new Price();
		mockPrice.setId((long) 1);
		mockPrice.setPrice((long) 50);

		SongDTO mockSong1 = new SongDTO();
		mockSong1.setId((long) 1);
		mockSong1.setName("song1");
		mockSong1.setArtist(mockArtist);
		mockSong1.setPrice(mockPrice);

		SongDTO mockSong2 = new SongDTO();
		mockSong2.setId((long) 2);
		mockSong2.setName("song2");
		mockSong2.setArtist(mockArtist);
		mockSong2.setPrice(mockPrice);

		SongDTO mockSong3 = new SongDTO();
		mockSong3.setId((long) 3);
		mockSong3.setName("song3");
		mockSong3.setArtist(mockArtist);
		mockSong3.setPrice(mockPrice);

		SongDTO mockSong4 = new SongDTO();
		mockSong4.setId((long) 4);
		mockSong4.setName("song4");
		mockSong4.setArtist(mockArtist);
		mockSong4.setPrice(mockPrice);

		SongDTO mockSong5 = new SongDTO();
		mockSong5.setId((long) 5);
		mockSong5.setName("song5");
		mockSong5.setArtist(mockArtist);
		mockSong5.setPrice(mockPrice);

		SongDTO mockSong6 = new SongDTO();
		mockSong6.setId((long) 6);
		mockSong6.setName("song6");
		mockSong6.setArtist(mockArtist);
		mockSong6.setPrice(mockPrice);

		List<SongDTO> mockList = new ArrayList<SongDTO>();
		mockList.add(mockSong1);
		mockList.add(mockSong2);
		mockList.add(mockSong3);
		mockList.add(mockSong4);
		mockList.add(mockSong5);

		Page<SongDTO> pageOfSongs = new PageImpl<SongDTO>(mockList, PageRequest.of(0, 5, new Sort(Direction.ASC, "id")),
				6);

		Mockito.when(songResource.getAllSongsPagination(PAGE)).thenReturn(pageOfSongs);

		String URI = "/songs/pagination/{page}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI, PAGE).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void getTop5Songs() throws Exception {
		Price mockPrice = new Price();
		mockPrice.setPrice((long) 50);

		Genre mockGenre = new Genre();
		mockGenre.setName("noviZanr1");

		Artist mockArtist = new Artist();
		mockArtist.setName("noviZanr1");
		mockArtist.setGenre(mockGenre);

		SongDTO mockSong1 = new SongDTO();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist);

		SongDTO mockSong2 = new SongDTO();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist);

		SongDTO mockSong3 = new SongDTO();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist);

		SongDTO mockSong4 = new SongDTO();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist);

		SongDTO mockSong5 = new SongDTO();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist);

		SongDTO mockSong6 = new SongDTO();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist);

		Traffic mockTraffic1 = new Traffic();
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(DTOtoEntity(mockSong1));
		mockTraffic1.setUser("auth0|123456789");

		Traffic mockTraffic2 = new Traffic();
		mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic2.setSong(DTOtoEntity(mockSong1));
		mockTraffic2.setUser("auth0|123456789");

		Traffic mockTraffic3 = new Traffic();
		mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic3.setSong(DTOtoEntity(mockSong1));
		mockTraffic3.setUser("auth0|123456789");

		Traffic mockTraffic4 = new Traffic();
		mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic4.setSong(DTOtoEntity(mockSong2));
		mockTraffic4.setUser("auth0|123456789");

		Traffic mockTraffic5 = new Traffic();
		mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic5.setSong(DTOtoEntity(mockSong2));
		mockTraffic5.setUser("auth0|123456789");

		Traffic mockTraffic6 = new Traffic();
		mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic6.setSong(DTOtoEntity(mockSong2));
		mockTraffic6.setUser("auth0|123456789");

		Traffic mockTraffic7 = new Traffic();
		mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic7.setSong(DTOtoEntity(mockSong2));
		mockTraffic7.setUser("auth0|123456789");

		Traffic mockTraffic8 = new Traffic();
		mockTraffic8.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic8.setSong(DTOtoEntity(mockSong3));
		mockTraffic8.setUser("auth0|123456789");

		Traffic mockTraffic9 = new Traffic();
		mockTraffic9.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic9.setSong(DTOtoEntity(mockSong3));
		mockTraffic9.setUser("auth0|123456789");

		Traffic mockTraffic10 = new Traffic();
		mockTraffic10.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic10.setSong(DTOtoEntity(mockSong3));
		mockTraffic10.setUser("auth0|123456789");

		Traffic mockTraffic11 = new Traffic();
		mockTraffic11.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic11.setSong(DTOtoEntity(mockSong4));
		mockTraffic11.setUser("auth0|123456789");

		Traffic mockTraffic12 = new Traffic();
		mockTraffic12.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic12.setSong(DTOtoEntity(mockSong4));
		mockTraffic12.setUser("auth0|123456789");

		Traffic mockTraffic13 = new Traffic();
		mockTraffic13.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic13.setSong(DTOtoEntity(mockSong5));
		mockTraffic13.setUser("auth0|123456789");

		Traffic mockTraffic14 = new Traffic();
		mockTraffic14.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic14.setSong(DTOtoEntity(mockSong6));
		mockTraffic14.setUser("auth0|123456789");

		Traffic mockTraffic15 = new Traffic();
		mockTraffic15.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic15.setSong(DTOtoEntity(mockSong6));
		mockTraffic15.setUser("auth0|123456789");

		Traffic mockTraffic16 = new Traffic();
		mockTraffic16.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic16.setSong(DTOtoEntity(mockSong6));
		mockTraffic16.setUser("auth0|123456789");

		List<SongDTO> mockList = new ArrayList<>();
		mockList.add(mockSong1);
		mockList.add(mockSong2);
		mockList.add(mockSong3);
		mockList.add(mockSong4);
		mockList.add(mockSong6);

		Mockito.when(songResource.getTop5Songs()).thenReturn(mockList);

		String URI = "/songs/top5songs";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URI)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	public Song DTOtoEntity(SongDTO song) {
		Song entity = new Song();
		entity.setId(song.getId());
		entity.setName(song.getName());
		entity.setArtist(song.getArtist());
		entity.setPrice(song.getPrice());
		return entity;
	}

}