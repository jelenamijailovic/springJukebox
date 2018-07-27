package com.telnet.jukebox.spring.repository.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.repository.GenreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GenreRepository genreRepository;

	@Test
	public void getGenres() {
		Genre mockGenre1 = new Genre();
		mockGenre1.setName("noviZanr1");

		Genre mockGenre2 = new Genre();
		mockGenre2.setName("noviZanr2");

		entityManager.persist(mockGenre1);
		entityManager.persist(mockGenre2);

		Iterable<Genre> allGenres = genreRepository.findAll();
		List<Genre> genreList = new ArrayList<>();

		for (Genre genre : allGenres) {
			genreList.add(genre);
		}

		assertEquals(genreList, allGenres);
	}

}
