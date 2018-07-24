package com.telnet.jukebox.spring.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.User;

public class TrafficDTO {

	private Long id;
	private Date date;
	private String song;
	private User user;
	private String genre;
	private String artist;
//	private Long repetition;
	private Long price;

	/*@Autowired
	private Song song;*/

	/*@Autowired
	private Price price;*/

	/*@Autowired
	private Artist artist;

	@Autowired
	private User user;*/

	public TrafficDTO() {

	}

	public TrafficDTO(Long id, Date date, String songName, Long price, Long repetition, String artistName,
			String emailUsr) {
		super();
		this.id = id;
		this.date = date;
		//this.song = new Song(songName);
		//this.price = new Price(price);
	//	this.repetition = repetition;
		/*this.artist = new Artist(artistName);
		this.user = new User(emailUsr);*/
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/*public Long getRepetition() {
		return repetition;
	}

	public void setRepetition(Long repetition) {
		this.repetition = repetition;
	}*/

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	/*public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		//result = prime * result + ((repetition == null) ? 0 : repetition.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		//result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrafficDTO other = (TrafficDTO) obj;
		/*if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;*/
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		/*if (repetition == null) {
			if (other.repetition != null)
				return false;
		} else if (!repetition.equals(other.repetition))
			return false;*/
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		/*if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;*/
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist= artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
