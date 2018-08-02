package com.telnet.jukebox.spring.service;

import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.model.Price;

@Service
public class PriceService {

	public Price DTOToEntity(PriceDTO song) {
		Price entity = new Price();
		entity.setId(song.getId());
		entity.setPrice(song.getPrice());
		return entity;
	}
	
	public PriceDTO entityToDTO(Price song) {
		PriceDTO dto = new PriceDTO();
		dto.setId(song.getId());
		dto.setPrice(song.getPrice());
		return dto;
	}
	
}
