package com.telnet.jukebox.spring.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "artist")
public class ArtistDTO {

	@ApiModelProperty(dataType = "Long", example = "[3, 5, 1]", position = -1)
	private Long id;
	
	@ApiModelProperty(dataType = "String", example = "[EKV, Miroslav Ilic, Hladno pivo]", position = 0)
	private String name;
	
	@ApiModelProperty(position = 1)
	private GenreDTO genre;

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

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "ArtistDTO [id=" + id + ", name=" + name + ", genre=" + genre + "]";
	}

}
