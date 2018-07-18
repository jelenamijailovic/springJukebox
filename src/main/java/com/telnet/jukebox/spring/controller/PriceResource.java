package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.PriceNotFoundException;
import com.telnet.jukebox.spring.service.PriceService;
import com.telnet.jukebox.spring.service.SongService;

@RestController
@RequestMapping("/prices")
public class PriceResource {

	// final static Logger logger = Logger.getLogger(CenaResource.class);

	@Autowired
	PriceService priceService = new PriceService();

	@Autowired
	SongService songService = new SongService();

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<PriceDTO> getAllPrices() {
		// logger.info("Prikaz svih cena");

		List<PriceDTO> listOfPrices = new ArrayList<PriceDTO>();
		try {
			listOfPrices = priceService.getAllPrices();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfPrices;
	}

	@GetMapping("/{priceId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PriceDTO getPrice(@PathVariable Long priceId) {
		// logger.info("Prikaz cene sa id-om " + priceId);

		PriceDTO price = new PriceDTO();
		try {
			price = priceService.getPrice(priceId);
		} catch (PriceNotFoundException e) {
			e.printStackTrace();
		}

		return price;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addPrice(@RequestBody PriceDTO price) {
		// logger.info("Unosenje cene");

		try {
			priceService.addPrice(price);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}
	}

	@PutMapping("/{priceId}")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PriceDTO updatePrice(@PathVariable Long priceId, @RequestBody PriceDTO price) {
		price.setId(priceId);

		// logger.info("Modifikovanje cene sa id-om " + priceId);

		PriceDTO newPrice = new PriceDTO();

		try {
			newPrice = priceService.updatePrice(price);
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

		return newPrice;

	}

	@DeleteMapping("/{priceId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void deletePrice(@PathVariable Long priceId) {
		// logger.info("Brisanje cene sa id-om " + priceId);

		try {
			priceService.deletePrice(priceId);
		} catch (PriceNotFoundException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/{priceId}/songs")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<SongDTO> getSongsByPrice(@PathVariable Long priceId) {

		// logger.info("Prikaz pesama za cenu sa id-om " + priceId);

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.getSongsByPrice(priceId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

}
