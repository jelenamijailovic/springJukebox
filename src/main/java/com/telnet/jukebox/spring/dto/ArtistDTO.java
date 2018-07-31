package com.telnet.jukebox.spring.dto;

import com.telnet.jukebox.spring.model.Genre;

public class ArtistDTO {

	private Long id;
	private String name;
	private Genre genre;

	public ArtistDTO() {
	}

	public ArtistDTO(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "ArtistDTO [id=" + id + ", name=" + name + ", genre=" + genre + "]";
	}

}
