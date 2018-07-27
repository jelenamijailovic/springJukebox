package com.telnet.jukebox.spring.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telnet.jukebox.spring.controller.SongResource;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.model.User;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SongResource.class,secure = false)
public class SongResourceTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private SongResource songResource;
  
/*   @Test
   public void getSongs() throws Exception {
	   TrafficDTO mockTraffic1= new TrafficDTO();
	   mockTraffic1.setId((long) 1);
	   mockTraffic1.setArtist("novaPesma1");
	   mockTraffic1.setGenre("genre");
	   mockTraffic1.setPrice((long) 50);
	   mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic1.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong("novaPesma1");
	   
	   TrafficDTO mockTraffic2= new TrafficDTO();
	   mockTraffic2.setId((long) 2);
	   mockTraffic2.setArtist("artist");
	   mockTraffic2.setGenre("genre");
	   mockTraffic2.setPrice((long) 50);
	   mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic2.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic2.setSong("novaPesma2");
	   
	   TrafficDTO mockTraffic3= new TrafficDTO();
	   mockTraffic3.setId((long) 3);
	   mockTraffic3.setArtist("artist");
	   mockTraffic3.setGenre("genre");
	   mockTraffic3.setPrice((long) 50);
	   mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic3.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic3.setSong("novaPesma3");
	   
	   TrafficDTO mockTraffic4= new TrafficDTO();
	   mockTraffic4.setId((long) 4);
	   mockTraffic4.setArtist("artist");
	   mockTraffic4.setGenre("genre");
	   mockTraffic4.setPrice((long) 50);
	   mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic4.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic4.setSong("novaPesma4");
	   
	   TrafficDTO mockTraffic5= new TrafficDTO();
	   mockTraffic5.setId((long) 5);
	   mockTraffic5.setArtist("artist");
	   mockTraffic5.setGenre("genre");
	   mockTraffic5.setPrice((long) 50);
	   mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic5.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic5.setSong("novaPesma5");
	   
	   TrafficDTO mockTraffic6= new TrafficDTO();
	   mockTraffic6.setId((long) 6);
	   mockTraffic6.setArtist("artist");
	   mockTraffic6.setGenre("genre");
	   mockTraffic6.setPrice((long) 50);
	   mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic6.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic6.setSong("novaPesma5");
	   
	   TrafficDTO mockTraffic7= new TrafficDTO();
	   mockTraffic7.setId((long) 7);
	   mockTraffic7.setArtist("artist");
	   mockTraffic7.setGenre("genre");
	   mockTraffic7.setPrice((long) 50);
	   mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic7.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic7.setSong("novaPesma6");
	   
       SongDTO mockSong1 = new SongDTO();
       mockSong1.setId((long) 1);
       mockSong1.setName("novaPesma1");
       mockSong1.setArtistName("artist");
       mockSong1.setGenreName("genre");
       mockSong1.setPrice((long) 50);
       
       SongDTO mockSong2 = new SongDTO();
       mockSong2.setId((long) 2);
       mockSong2.setName("novaPesma2");
       mockSong2.setArtistName("artist");
       mockSong2.setGenreName("genre");
       mockSong2.setPrice((long) 50);
       
       SongDTO mockSong3 = new SongDTO();
       mockSong3.setId((long) 3);
       mockSong3.setName("novaPesma3");
       mockSong3.setArtistName("artist");
       mockSong3.setGenreName("genre");
       mockSong3.setPrice((long) 50);
       
       SongDTO mockSong4 = new SongDTO();
       mockSong4.setId((long) 4);
       mockSong4.setName("novaPesma4");
       mockSong4.setArtistName("artist");
       mockSong4.setGenreName("genre");
       mockSong4.setPrice((long) 50);
       
       SongDTO mockSong5 = new SongDTO();
       mockSong5.setId((long) 5);
       mockSong5.setName("novaPesma5");
       mockSong5.setArtistName("artist");
       mockSong5.setGenreName("genre");
       mockSong5.setPrice((long) 50);
       
       SongDTO mockSong6 = new SongDTO();
       mockSong6.setId((long) 6);
       mockSong6.setName("novaPesma6");
       mockSong6.setArtistName("artist");
       mockSong6.setGenreName("genre");
       mockSong6.setPrice((long) 50);
       
       List<SongDTO> mockList= new ArrayList<SongDTO>();
       mockList.add(mockSong1);
       mockList.add(mockSong2);
       mockList.add(mockSong3);
       mockList.add(mockSong4);
       mockList.add(mockSong5);
       mockList.add(mockSong6);
       
       Page<SongDTO> pageOfSongs = new PageImpl<SongDTO>(mockList, PageRequest.of(0, 5, new Sort(Direction.ASC, "id")), 6);
   
       Mockito.when(songResource.getAllSongsPagination(1)).thenReturn(pageOfSongs);
       
       String URI= "songs/pagination/1";
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(pageOfSongs);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

   }
   */
/*   @Test
   public void getTop5Songs() throws Exception {
	   Traffic mockTraffic1= new Traffic();
	  // mockTraffic1.setId((long) 1);
	   mockTraffic1.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic1.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 1, "novaPesma1", "artist",(long)50));
	   
	   Traffic mockTraffic2= new Traffic();
	  // mockTraffic2.setId((long) 2);
	   mockTraffic2.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic2.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 2, "novaPesma2", "artist",(long)50));
	   
	   Traffic mockTraffic3= new Traffic();
	  // mockTraffic3.setId((long) 3);
	   mockTraffic3.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic3.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 3, "novaPesma3", "artist",(long)50));
	   
	   Traffic mockTraffic4= new Traffic();
	 //  mockTraffic4.setId((long) 4);
	   mockTraffic4.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic4.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 4, "novaPesma4", "artist",(long)50));
	   
	   Traffic mockTraffic5= new Traffic();
	  // mockTraffic5.setId((long) 5);
	   mockTraffic5.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic5.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 5, "novaPesma5", "artist",(long)50));
	   
	   Traffic mockTraffic6= new Traffic();
	 //  mockTraffic6.setId((long) 6);
	   mockTraffic6.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic6.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 5, "novaPesma5", "artist",(long)50));
	   
	   Traffic mockTraffic7= new Traffic();
	 //  mockTraffic7.setId((long) 7);
	   mockTraffic7.setDate(new java.sql.Date(new java.util.Date().getTime()));
	   mockTraffic7.setUser(new User("mejmijailovicpz@gmal.com"));
	   mockTraffic1.setSong(new Song((long) 6, "novaPesma6", "artist",(long)50));
	   
       SongDTO mockSong1 = new SongDTO();
     //  mockSong1.setId((long) 1);
       mockSong1.setName("novaPesma1");
       mockSong1.setArtist(new Artist("artist"));
       mockSong1.setPrice(new Price((long) 50));
       
       SongDTO mockSong2 = new SongDTO();
    //   mockSong2.setId((long) 2);
       mockSong2.setName("novaPesma2");
       mockSong2.setArtist(new Artist("artist"));
       mockSong2.setPrice(new Price((long) 50));
       
       SongDTO mockSong3 = new SongDTO();
    //   mockSong3.setId((long) 3);
       mockSong3.setName("novaPesma3");
       mockSong3.setArtist(new Artist("artist"));
       mockSong3.setPrice(new Price((long) 50));
       
       SongDTO mockSong4 = new SongDTO();
     //  mockSong4.setId((long) 4);
       mockSong4.setName("novaPesma4");
       mockSong4.setArtist(new Artist("artist"));
       mockSong4.setPrice(new Price((long) 50));
       
       SongDTO mockSong5 = new SongDTO();
   //    mockSong5.setId((long) 5);
       mockSong5.setName("novaPesma5");
       mockSong5.setArtist(new Artist("artist"));
       mockSong5.setPrice(new Price((long) 50));
       
       SongDTO mockSong6 = new SongDTO();
   //    mockSong6.setId((long) 6);
       mockSong6.setName("novaPesma6");
       mockSong6.setArtist(new Artist("artist"));
       mockSong6.setPrice(new Price((long) 50));
       
       List<SongDTO> mockList= new ArrayList<SongDTO>();
   //    mockList.add(mockSong1);
       mockList.add(mockSong2);
       mockList.add(mockSong3);
       mockList.add(mockSong4);
       mockList.add(mockSong5);
       mockList.add(mockSong6);
       
	   
	   Mockito.when(songResource.getTop5Songs()).thenReturn(mockList);
       
       String URI= "songs/top5songs";
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		//assertNotNull(outputInJson);
   }*/
   
   @Test
   public void getSongByGenre () throws Exception {
	   SongDTO mockSong1 = new SongDTO();
	     //  mockSong1.setId((long) 1);
	       mockSong1.setName("novaPesma1");
	       mockSong1.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong1.setPrice(new Price((long) 50));
	       
	       SongDTO mockSong2 = new SongDTO();
	    //   mockSong2.setId((long) 2);
	       mockSong2.setName("novaPesma2");
	       mockSong2.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong2.setPrice(new Price((long) 50));
	       
	       SongDTO mockSong3 = new SongDTO();
	    //   mockSong3.setId((long) 3);
	       mockSong3.setName("novaPesma3");
	       mockSong3.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong3.setPrice(new Price((long) 50));
	       
	       SongDTO mockSong4 = new SongDTO();
	     //  mockSong4.setId((long) 4);
	       mockSong4.setName("novaPesma4");
	       mockSong4.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong4.setPrice(new Price((long) 50));
	       
	       SongDTO mockSong5 = new SongDTO();
	   //    mockSong5.setId((long) 5);
	       mockSong5.setName("novaPesma5");
	       mockSong5.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong5.setPrice(new Price((long) 50));
	       
	       SongDTO mockSong6 = new SongDTO();
	   //    mockSong6.setId((long) 6);
	       mockSong6.setName("novaPesma6");
	       mockSong6.setArtist(new Artist((long)1,"artist", "genre"));
	       mockSong6.setPrice(new Price((long) 50));
	       
	       List<SongDTO> mockList= new ArrayList<SongDTO>();
	   //    mockList.add(mockSong1);
	       mockList.add(mockSong2);
	       mockList.add(mockSong3);
	       mockList.add(mockSong4);
	       mockList.add(mockSong5);
	       mockList.add(mockSong6);
	       
	       Mockito.when(songResource.getTop5Songs()).thenReturn(mockList);
	       
	       String URI= "genres/1/songs";
	       
	       RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URI).accept(
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