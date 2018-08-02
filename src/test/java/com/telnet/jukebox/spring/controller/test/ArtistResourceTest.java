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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.controller.ArtistResource;
import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ArtistResource.class, secure = false)
public class ArtistResourceTest {

	private static final Long ID = 1L;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArtistResource artistResource;

	@Test
	public void getArtists() throws Exception {
		GenreDTO mockGenre = new GenreDTO();
		mockGenre.setId((long) 1);
		mockGenre.setName("genre");

		ArtistDTO mockArtist1 = new ArtistDTO();
		mockArtist1.setId((long) 1);
		mockArtist1.setName("artist1");
		mockArtist1.setGenre(mockGenre);

		ArtistDTO mockArtist2 = new ArtistDTO();
		mockArtist2.setId((long) 2);
		mockArtist2.setName("artist2");
		mockArtist2.setGenre(mockGenre);

		List<ArtistDTO> mockList = new ArrayList<ArtistDTO>();
		mockList.add(mockArtist1);
		mockList.add(mockArtist2);

		Mockito.when(artistResource.getAllArtists()).thenReturn(mockList);

		String URI = "/artists";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void getSongByGenre() throws Exception {
		GenreDTO mockGenre = new GenreDTO();
		mockGenre.setId((long) 1);
		mockGenre.setName("genre");

		ArtistDTO mockArtist = new ArtistDTO();
		mockArtist.setId(ID);
		mockArtist.setName("artist");
		mockArtist.setGenre(mockGenre);

		PriceDTO mockPrice = new PriceDTO();
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
		mockList.add(mockSong6);

		Mockito.when(artistResource.getSongsByArtist(ID)).thenReturn(mockList);

		String URI = "/artists/{artistId}/songs";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI, ID).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void getTop5Songs() throws Exception {
		PriceDTO mockPrice = new PriceDTO();
		mockPrice.setPrice((long) 50);

		GenreDTO mockGenre = new GenreDTO();
		mockGenre.setName("noviZanr1");

		ArtistDTO mockArtist1 = new ArtistDTO();
		mockArtist1.setName("noviZanr1");
		mockArtist1.setGenre(mockGenre);

		ArtistDTO mockArtist2 = new ArtistDTO();
		mockArtist2.setName("noviZanr1");
		mockArtist2.setGenre(mockGenre);

		ArtistDTO mockArtist3 = new ArtistDTO();
		mockArtist3.setName("noviZanr1");
		mockArtist3.setGenre(mockGenre);

		ArtistDTO mockArtist4 = new ArtistDTO();
		mockArtist4.setName("noviZanr1");
		mockArtist4.setGenre(mockGenre);

		ArtistDTO mockArtist5 = new ArtistDTO();
		mockArtist5.setName("noviZanr1");
		mockArtist5.setGenre(mockGenre);

		ArtistDTO mockArtist6 = new ArtistDTO();
		mockArtist6.setName("noviZanr1");
		mockArtist6.setGenre(mockGenre);

		SongDTO mockSong1 = new SongDTO();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist1);

		SongDTO mockSong2 = new SongDTO();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist2);

		SongDTO mockSong3 = new SongDTO();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist3);

		SongDTO mockSong4 = new SongDTO();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist4);

		SongDTO mockSong5 = new SongDTO();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist5);

		SongDTO mockSong6 = new SongDTO();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist6);

		TrafficDTO mockTraffic1 = new TrafficDTO();
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(mockSong1);
		mockTraffic1.setUser("auth0|123456789");

		TrafficDTO mockTraffic2 = new TrafficDTO();
		mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic2.setSong(mockSong1);
		mockTraffic2.setUser("auth0|123456789");

		TrafficDTO mockTraffic3 = new TrafficDTO();
		mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic3.setSong(mockSong1);
		mockTraffic3.setUser("auth0|123456789");

		TrafficDTO mockTraffic4 = new TrafficDTO();
		mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic4.setSong(mockSong2);
		mockTraffic4.setUser("auth0|123456789");

		TrafficDTO mockTraffic5 = new TrafficDTO();
		mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic5.setSong(mockSong2);
		mockTraffic5.setUser("auth0|123456789");

		TrafficDTO mockTraffic6 = new TrafficDTO();
		mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic6.setSong(mockSong2);
		mockTraffic6.setUser("auth0|123456789");

		TrafficDTO mockTraffic7 = new TrafficDTO();
		mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic7.setSong(mockSong2);
		mockTraffic7.setUser("auth0|123456789");

		TrafficDTO mockTraffic8 = new TrafficDTO();
		mockTraffic8.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic8.setSong(mockSong3);
		mockTraffic8.setUser("auth0|123456789");

		TrafficDTO mockTraffic9 = new TrafficDTO();
		mockTraffic9.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic9.setSong(mockSong3);
		mockTraffic9.setUser("auth0|123456789");

		TrafficDTO mockTraffic10 = new TrafficDTO();
		mockTraffic10.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic10.setSong(mockSong3);
		mockTraffic10.setUser("auth0|123456789");

		TrafficDTO mockTraffic11 = new TrafficDTO();
		mockTraffic11.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic11.setSong(mockSong4);
		mockTraffic11.setUser("auth0|123456789");

		TrafficDTO mockTraffic12 = new TrafficDTO();
		mockTraffic12.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic12.setSong(mockSong4);
		mockTraffic12.setUser("auth0|123456789");

		TrafficDTO mockTraffic13 = new TrafficDTO();
		mockTraffic13.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic13.setSong(mockSong5);
		mockTraffic13.setUser("auth0|123456789");

		TrafficDTO mockTraffic14 = new TrafficDTO();
		mockTraffic14.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic14.setSong(mockSong6);
		mockTraffic14.setUser("auth0|123456789");

		TrafficDTO mockTraffic15 = new TrafficDTO();
		mockTraffic15.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic15.setSong(mockSong6);
		mockTraffic15.setUser("auth0|123456789");

		TrafficDTO mockTraffic16 = new TrafficDTO();
		mockTraffic16.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic16.setSong(mockSong6);
		mockTraffic16.setUser("auth0|123456789");

		List<ArtistDTO> mockList = new ArrayList<>();
		mockList.add(mockArtist1);
		mockList.add(mockArtist2);
		mockList.add(mockArtist3);
		mockList.add(mockArtist4);
		mockList.add(mockArtist6);

		Mockito.when(artistResource.getTop5Artists()).thenReturn(mockList);

		String URI = "/artists/top5artists";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URI)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}
	
}