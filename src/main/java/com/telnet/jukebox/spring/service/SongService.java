package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.SongNotFoundException;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	SongRepository songRepository;

	public List<SongDTO> getSongsByArtist(Long artistId) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findSongsByArtistId(artistId);

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}

/*	public List<SongDTO> getSongsByGenre(Long genreId) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findSongsByGenre(genreId);

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}*/

	public List<SongDTO> getSongsByPrice(Long priceId) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findSongsByPriceId(priceId);

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}

	public List<SongDTO> getAllSongs() throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = (List<Song>) songRepository.findAll();

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}

	/*public List<SongDTO> getAllSongsPagination(int page) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		int offsetNum = (page - 1) * 5;

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findPag(offsetNum);

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}*/

	/*public Page<SongDTO> songList(Pageable pageable) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();
		
		listOfSongs = songRepository.songList(pageable).getContent();

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}
		}
		
		Page<SongDTO> pageOfSongs= new PageImpl<SongDTO>(listOfSongsDTO, pageable, listOfSongsDTO.size());
		
		return pageOfSongs;
		
	}*/
	
	public SongDTO getSong(Long songId) throws SongNotFoundException {
		Song song = new Song();
		song = songRepository.findById(songId).get();

		if (song.equals(null)) {
			throw new SongNotFoundException(songId);
		} else {
			return entityToDTO(song);
		}

	}

	public SongDTO addSong(SongDTO song) throws BadEntryException {
		return entityToDTO(songRepository.save(DTOToEntity(song)));
	}

	public SongDTO updateSong(SongDTO song) throws BadEntryException {
		return entityToDTO(songRepository.save(DTOToEntity(song)));
	}

	public void deleteSong(Long songId) throws SongNotFoundException {
		songRepository.deleteById(songId);
	}

	

	public Song DTOToEntity(SongDTO song) {
		Song entity = new Song();
		// entity.setNumOfPages(song.getNumOfPages());
		entity.setId(song.getId());
		entity.setName(song.getName());
		/*entity.setArtist(song.getArtist());
		//entity.setGenre(song.getGenre());
		entity.setPrice(song.getPrice());*/
		return entity;
	}

	public SongDTO entityToDTO(Song song) {
		SongDTO dto = new SongDTO();
		// dto.setNumOfPages(song.getNumOfPages());
		dto.setId(song.getId());
		dto.setName(song.getName());
	/*	dto.setArtist(song.getArtist());
		//dto.setGenre(song.getGenre());
		dto.setPrice(song.getPrice());*/
		return dto;
	}

}
