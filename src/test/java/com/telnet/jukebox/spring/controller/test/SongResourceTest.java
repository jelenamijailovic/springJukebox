package com.telnet.jukebox.spring.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;

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
		GenreDTO mockGenre = new GenreDTO();
		mockGenre.setId((long) 1);
		mockGenre.setName("genre");

		ArtistDTO mockArtist = new ArtistDTO();
		mockArtist.setId((long) 1);
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

		Page<SongDTO> pageOfSongs = new PageImpl<SongDTO>(mockList, PageRequest.of(0, 5, new Sort(Direction.ASC, "id")),
				6);

		Mockito.when(songResource.getAllSongsPagination(PAGE)).thenReturn(pageOfSongs);

		String URI = "/songs/pagination/{page}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI, PAGE).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void getTop5Songs() throws Exception {
		PriceDTO mockPrice = new PriceDTO();
		mockPrice.setPrice((long) 50);

		GenreDTO mockGenre = new GenreDTO();
		mockGenre.setName("noviZanr1");

		ArtistDTO mockArtist = new ArtistDTO();
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

	@Test
	public void recomended() throws Exception {
		PriceDTO mockPrice = new PriceDTO();
		mockPrice.setPrice((long) 50);

		GenreDTO mockGenre1 = new GenreDTO();
		mockGenre1.setName("noviZanr1");

		GenreDTO mockGenre2 = new GenreDTO();
		mockGenre2.setName("noviZanr2");

		ArtistDTO mockArtist1 = new ArtistDTO();
		mockArtist1.setName("noviZanr1");
		mockArtist1.setGenre(mockGenre1);

		ArtistDTO mockArtist2 = new ArtistDTO();
		mockArtist2.setName("noviIzvodjac1");
		mockArtist2.setGenre(mockGenre2);

		ArtistDTO mockArtist3 = new ArtistDTO();
		mockArtist3.setName("noviIzvodjac2");
		mockArtist3.setGenre(mockGenre2);

		SongDTO mockSong1 = new SongDTO();
		mockSong1.setName("novaPesma1");
		mockSong1.setPrice(mockPrice);
		mockSong1.setArtist(mockArtist3);

		SongDTO mockSong2 = new SongDTO();
		mockSong2.setName("novaPesma2");
		mockSong2.setPrice(mockPrice);
		mockSong2.setArtist(mockArtist2);

		SongDTO mockSong3 = new SongDTO();
		mockSong3.setName("novaPesma3");
		mockSong3.setPrice(mockPrice);
		mockSong3.setArtist(mockArtist1);

		SongDTO mockSong4 = new SongDTO();
		mockSong4.setName("novaPesma4");
		mockSong4.setPrice(mockPrice);
		mockSong4.setArtist(mockArtist2);

		SongDTO mockSong5 = new SongDTO();
		mockSong5.setName("novaPesma5");
		mockSong5.setPrice(mockPrice);
		mockSong5.setArtist(mockArtist3);

		SongDTO mockSong6 = new SongDTO();
		mockSong6.setName("novaPesma6");
		mockSong6.setPrice(mockPrice);
		mockSong6.setArtist(mockArtist2);

		List<SongDTO> songsByGenre = new ArrayList<>();
		songsByGenre.add(mockSong1);
		songsByGenre.add(mockSong2);
		songsByGenre.add(mockSong4);
		songsByGenre.add(mockSong5);
		songsByGenre.add(mockSong6);

		Random randomGenerator = new Random();

		List<SongDTO> recomended = new ArrayList<SongDTO>();

		for (int br = 0; br < 3; br++) {
			int index = randomGenerator.nextInt(songsByGenre.size());
			SongDTO randomSong = new SongDTO();
			randomSong = songsByGenre.get(index);
			for (int i = 0; i < recomended.size(); i++) {
				if (randomSong == recomended.get(i)) {
					System.out.println("The song is repeated");
					randomSong = songsByGenre.get(index + 1);
					i = recomended.size();
				} else {
					randomSong = songsByGenre.get(index);
				}
			}
			recomended.add(randomSong);
		}

		String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJEZENORUZGTWtGRU5VWkdOelV6TnpRek4wRkdNak0xUmpCR1F6VXdSVE00TmpZNE1EUXhRUSJ9.eyJuaWNrbmFtZSI6Im1pbGlqYW4iLCJuYW1lIjoibWlsaWphbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvMTM3OWMxYzdhNzFmNGI2NzM3MGFhZmVjYjgxZWEzY2Q_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtaS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAxOC0wNy0zMVQwOToxMDoyNi44NTVaIiwiaXNzIjoiaHR0cHM6Ly9uZWRvdmljbS5ldS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NWI1ZjE4ZjBiZDRkNmI3ZTU2MmI2MDU5IiwiYXVkIjoiNGd6NlZEVmkyMGp4bzFucXRrNWlnUjRDMGhTeWtOQ04iLCJpYXQiOjE1MzMwMjgyMjYsImV4cCI6MTUzMzAzNTQyNiwiYXRfaGFzaCI6IkRRREJtSXQzTzZzUUpaZmJrT1c2VGciLCJub25jZSI6InRxU2ZqS1ZmR042RUJKNUlDcndYWjVxOUtZS3R1RUR3In0.tQcIlXr7axLiTFlzMIh0dojrcpcStGI_ua4EWvVEL1nwPOTIfHwE-aiH-tUibyWTMvhuvl348r8Dr0HwSAgGN4ojniQQ47fsO_JSYB4zsYRQB-6dzmZbp8F_a4RBr_9RrJmcqGRRGT05ucIZ0sfCogo2QPgpPw5lccOX8nbI54rHPJPrM-Rr1WzugMRrGA5rIkM_Cve9_6vCgLYzOVPz3ca-zocyz4KiXwrJY5D6cxq2RWr-SD-CkHhAs5SZv83Us4ZY2YigSbf-zDe0FckfzDRA3KmysD6joGyLmz_DvtLqInc5pm_xf8ZVkCvZRv3aEJSzhH60s0nzW004-izZaw";

		Mockito.when(songResource.recomended(authorization)).thenReturn(recomended);
		String URI = "/songs/recomended";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Content-Type", "application/json")
				.header("Authorization", authorization);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

}