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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telnet.jukebox.spring.controller.TrafficResource;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TrafficResource.class, secure = false)
public class TrafficResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrafficResource trafficResource;

	@Test
	public void postTraffic() throws Exception {
		TrafficDTO mockTraffic1 = new TrafficDTO();
		mockTraffic1.setId((long) 1);
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(new Song("song1"));
		mockTraffic1.setUser("auth0|123456789");

		String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJEZENORUZGTWtGRU5VWkdOelV6TnpRek4wRkdNak0xUmpCR1F6VXdSVE00TmpZNE1EUXhRUSJ9.eyJuaWNrbmFtZSI6Im1pbGlqYW4iLCJuYW1lIjoibWlsaWphbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvMTM3OWMxYzdhNzFmNGI2NzM3MGFhZmVjYjgxZWEzY2Q_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtaS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAxOC0wNy0zMVQwOToxMDoyNi44NTVaIiwiaXNzIjoiaHR0cHM6Ly9uZWRvdmljbS5ldS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NWI1ZjE4ZjBiZDRkNmI3ZTU2MmI2MDU5IiwiYXVkIjoiNGd6NlZEVmkyMGp4bzFucXRrNWlnUjRDMGhTeWtOQ04iLCJpYXQiOjE1MzMwMjgyMjYsImV4cCI6MTUzMzAzNTQyNiwiYXRfaGFzaCI6IkRRREJtSXQzTzZzUUpaZmJrT1c2VGciLCJub25jZSI6InRxU2ZqS1ZmR042RUJKNUlDcndYWjVxOUtZS3R1RUR3In0.tQcIlXr7axLiTFlzMIh0dojrcpcStGI_ua4EWvVEL1nwPOTIfHwE-aiH-tUibyWTMvhuvl348r8Dr0HwSAgGN4ojniQQ47fsO_JSYB4zsYRQB-6dzmZbp8F_a4RBr_9RrJmcqGRRGT05ucIZ0sfCogo2QPgpPw5lccOX8nbI54rHPJPrM-Rr1WzugMRrGA5rIkM_Cve9_6vCgLYzOVPz3ca-zocyz4KiXwrJY5D6cxq2RWr-SD-CkHhAs5SZv83Us4ZY2YigSbf-zDe0FckfzDRA3KmysD6joGyLmz_DvtLqInc5pm_xf8ZVkCvZRv3aEJSzhH60s0nzW004-izZaw";

		Mockito.when(trafficResource.addTraffic(authorization, mockTraffic1)).thenReturn(mockTraffic1);

		String input = mapToJson(mockTraffic1);

		String URI = "/traffic";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).header("Content-Type", "application/json")
				.header("Authorization", authorization).content(input);

		mockMvc.perform(requestBuilder).andExpect(status().is(201));

		/*
		 * String expectedJson = this.mapToJson(mockTraffic1); String outputInJson =
		 * result.getResponse().getContentAsString();
		 * assertThat(outputInJson).isEqualTo(expectedJson);
		 */

	}

	@Test
	public void recomended() throws Exception {
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

		List<Song> songsByGenre = new ArrayList<>();
		songsByGenre.add(mockSong1);
		songsByGenre.add(mockSong2);
		songsByGenre.add(mockSong4);
		songsByGenre.add(mockSong5);
		songsByGenre.add(mockSong6);

		Random randomGenerator = new Random();

		List<Song> recomended = new ArrayList<Song>();
		List<SongDTO> recomendedDTO = new ArrayList<SongDTO>();

		for (int br = 0; br < 3; br++) {
			int index = randomGenerator.nextInt(songsByGenre.size());
			Song randomSong = new Song();
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

		for (int i = 0; i < recomended.size(); i++) {
			recomendedDTO.add(entityToDTO(recomended.get(i)));
		}

		String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJEZENORUZGTWtGRU5VWkdOelV6TnpRek4wRkdNak0xUmpCR1F6VXdSVE00TmpZNE1EUXhRUSJ9.eyJuaWNrbmFtZSI6Im1pbGlqYW4iLCJuYW1lIjoibWlsaWphbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvMTM3OWMxYzdhNzFmNGI2NzM3MGFhZmVjYjgxZWEzY2Q_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtaS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAxOC0wNy0zMVQwOToxMDoyNi44NTVaIiwiaXNzIjoiaHR0cHM6Ly9uZWRvdmljbS5ldS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NWI1ZjE4ZjBiZDRkNmI3ZTU2MmI2MDU5IiwiYXVkIjoiNGd6NlZEVmkyMGp4bzFucXRrNWlnUjRDMGhTeWtOQ04iLCJpYXQiOjE1MzMwMjgyMjYsImV4cCI6MTUzMzAzNTQyNiwiYXRfaGFzaCI6IkRRREJtSXQzTzZzUUpaZmJrT1c2VGciLCJub25jZSI6InRxU2ZqS1ZmR042RUJKNUlDcndYWjVxOUtZS3R1RUR3In0.tQcIlXr7axLiTFlzMIh0dojrcpcStGI_ua4EWvVEL1nwPOTIfHwE-aiH-tUibyWTMvhuvl348r8Dr0HwSAgGN4ojniQQ47fsO_JSYB4zsYRQB-6dzmZbp8F_a4RBr_9RrJmcqGRRGT05ucIZ0sfCogo2QPgpPw5lccOX8nbI54rHPJPrM-Rr1WzugMRrGA5rIkM_Cve9_6vCgLYzOVPz3ca-zocyz4KiXwrJY5D6cxq2RWr-SD-CkHhAs5SZv83Us4ZY2YigSbf-zDe0FckfzDRA3KmysD6joGyLmz_DvtLqInc5pm_xf8ZVkCvZRv3aEJSzhH60s0nzW004-izZaw";

		Mockito.when(trafficResource.recomended(authorization)).thenReturn(recomendedDTO);
		String URI = "/traffic/recomended";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Content-Type", "application/json")
				.header("Authorization", authorization);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	public SongDTO entityToDTO(Song song) {
		SongDTO dto = new SongDTO();
		dto.setId(song.getId());
		dto.setName(song.getName());
		dto.setArtist(song.getArtist());
		dto.setPrice(song.getPrice());
		return dto;
	}

}