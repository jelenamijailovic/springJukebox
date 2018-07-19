package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

	public List<Traffic> findTrafficBySongId(Long songId);
	public List<Traffic> findTrafficByUserId(Long userId);
	public List<Traffic> findAllByUserId(Long userId);
	
/*	@Query("select distinct so.name, count(tr.id) as repetition, p.price from traffic tr join songs so on tr.song_id= so.id join prices p on so.price_id= p.id group by so.name, so.price_id order by repetition desc limit 5;")
	public List<Traffic> findTop5Songs();
	
	@Query("select a.name, count(so.id) as repetition from traffic tr join songs so on tr.song_id= so.id join artists a on so.artist_id= a.id group by so.artist_id order by repetition desc limit 5;")
	public List<Traffic> findTop5Artists();*/
	
}
