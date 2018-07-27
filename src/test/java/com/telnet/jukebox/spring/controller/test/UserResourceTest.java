package com.telnet.jukebox.spring.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telnet.jukebox.spring.controller.UserResource;
import com.telnet.jukebox.spring.dto.UserDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserResource.class,secure = false)
public class UserResourceTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private UserResource userResource;

   @Test
	public void postUser() throws Exception {
		
		UserDTO mockUser = new UserDTO();
		mockUser.setId((long) 1);
		mockUser.setPassword("password");
		mockUser.setEmail("email@gmail.com");
		
		String inputInJson = this.mapToJson(mockUser);
		
		String URI = "/users";
		
		Mockito.when(userResource.addUser(Mockito.any(UserDTO.class))).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

   
   private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

  
}