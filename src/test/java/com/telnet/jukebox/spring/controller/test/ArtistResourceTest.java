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
import com.telnet.jukebox.spring.controller.ArtistResource;
import com.telnet.jukebox.spring.dto.ArtistDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ArtistResource.class,secure = false)
public class ArtistResourceTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private ArtistResource artistResource;

   @Test
   public void getArtists() throws Exception {
       ArtistDTO mockArtist1 = new ArtistDTO();
       mockArtist1.setId((long) 1);
       mockArtist1.setName("noviIzvodjac1");
       
       ArtistDTO mockArtist2 = new ArtistDTO();
       mockArtist2.setId((long) 2);
       mockArtist2.setName("noviIzvodjac2");
       
       List<ArtistDTO> mockList= new ArrayList<ArtistDTO>();
       mockList.add(mockArtist1);
       mockList.add(mockArtist2);
       
       Mockito.when(artistResource.getAllArtists()).thenReturn(mockList);
       
       String URI= "/artists";
       
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