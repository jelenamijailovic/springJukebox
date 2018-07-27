package com.telnet.jukebox.spring.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

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
   
   private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

  
}