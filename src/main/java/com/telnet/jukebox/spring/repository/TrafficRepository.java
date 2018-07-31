package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

	public List<Traffic> findTrafficByUser(String user);

	public int countBySongId(Long songId);

}
