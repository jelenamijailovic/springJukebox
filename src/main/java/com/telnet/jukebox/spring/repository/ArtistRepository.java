package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

	public List<Artist> findArtistsByGenre(Long genreId);
	
}
