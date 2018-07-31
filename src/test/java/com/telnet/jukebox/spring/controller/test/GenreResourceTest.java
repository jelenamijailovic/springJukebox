package com.telnet.jukebox.spring.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telnet.jukebox.spring.controller.GenreResource;
import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;

@RunWith(SpringRunner.class)
@WebMvcTest(value=GenreResource.class,secure = false)
public class GenreResourceTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private GenreResource genreResource;

   @Test
   public void getGenres() throws Exception {
       GenreDTO mockGenre1 = new GenreDTO();
       mockGenre1.setId((long) 1);
       mockGenre1.setName("noviZanr1");
       
       GenreDTO mockGenre2 = new GenreDTO();
       mockGenre2.setId((long) 2);
       mockGenre2.setName("noviZanr2");
       
       List<GenreDTO> mockList= new ArrayList<GenreDTO>();
       mockList.add(mockGenre1);
       mockList.add(mockGenre2);
       
       Mockito.when(genreResource.getAllGenres()).thenReturn(mockList);
       
       String URI= "/genres";
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

   }
   
	@Test
	public void getSongByGenre() throws Exception {
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
		mockList.add(mockSong6);

		Mockito.when(genreResource.getSongsByGenre((long) 1)).thenReturn(mockList);

		String URI = "/genres/1/songs";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockList);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
	}
   
   private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

  
}