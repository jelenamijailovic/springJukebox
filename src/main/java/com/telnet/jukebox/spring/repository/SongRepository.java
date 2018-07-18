package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Song;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {

	public List<Song> findSongsByArtist(Long artistId);

//	public List<Song> findSongsByGenre(Long genreId);

	public List<Song> findSongsByPrice(Long priceId);

	//public Page<Song> songList(Pageable pageable);

	/*
	 * @Query("select ceil((select count(*) from songs)/5) 'broj strana', so.id, so.name, a.name, g.name, p.price from songs so join artists a on so.artist_id=a.id join genres g on a.genre_id=g.id join prices p on so.price_id=p.id order by so.id limit 5 offset ?;"
	 * ) public List<Song> findPag(@PathVariable int offsetNum);
	 */

	/*
	 * @Query("select g.id, so.name, count(tr.song_id) as repetition, u.id from traffic tr join songs so on tr.song_id= so.id join artists a on a.id=so.artist_id join genres g on g.id=a.genre_id join users u on tr.user_id= u.id group by so.name, u.email, g.id having u.id=27 order by repetition desc limit 1;"
	 * ) public List<Song> findRec(@PathVariable Long userId);
	 */

}
