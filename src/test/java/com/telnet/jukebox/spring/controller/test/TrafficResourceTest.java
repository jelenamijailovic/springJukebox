package com.telnet.jukebox.spring.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TrafficResource.class, secure = false)
public class TrafficResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrafficResource trafficResource;

	@Test
	public void postTraffic() throws Exception {
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

		TrafficDTO mockTraffic1 = new TrafficDTO();
		mockTraffic1.setId((long) 1);
		mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
		mockTraffic1.setSong(mockSong1);
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

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}