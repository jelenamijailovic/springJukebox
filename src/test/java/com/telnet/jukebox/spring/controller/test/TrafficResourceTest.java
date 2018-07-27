package com.telnet.jukebox.spring.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.telnet.jukebox.spring.controller.TrafficResource;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.User;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TrafficResource.class,secure = false)
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
       mockTraffic1.setSong(new Song("pesma1"));
       mockTraffic1.setUser(new User("mrjmijailovicpz@gmail.com"));
       
       Mockito.when(trafficResource.addTraffic("", new TrafficDTO())).thenReturn(mockTraffic1);
       
       String URI= "/traffic";
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON).header("Authorization", "");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockTraffic1);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

   }
   
   private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

  
}