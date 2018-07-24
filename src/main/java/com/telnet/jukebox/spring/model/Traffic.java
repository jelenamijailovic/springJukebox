package com.telnet.jukebox.spring.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "traffic")
public class Traffic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5773971564012513934L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(nullable= false)
	private Date date;
	// private int songId;
	// private String songName;
	// private int price;
	//private Long repetition;
	// private String artistName;
	// private int idUsr;
	// private String emailUsr;

	@ManyToOne
	@JoinColumn(name= "song_id", nullable= false)
	private Song song;

	/*@Autowired
	private Price price;

	@Autowired
	private Artist artist;*/

	/*@Column(name="user_id")
	private Long userId; */
	
	
	@ManyToOne
	@JoinColumn(name= "user_id", nullable= false, insertable= false, updatable= false)
	private User user;

	public Traffic() {
	}

	public Traffic(Long id, Date date, String songName, Long price, String artistName,
			String emailUsr) {
		super();
		this.id = id;
		this.date = date;
		this.song = new Song(songName);
		//this.price = new Price(price);
		//this.repetition = repetition;
		//this.artist = new Artist(artistName);
		this.user = new User(emailUsr);
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

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	/*public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}*/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + ((price == null) ? 0 : price.hashCode());
		//result = prime * result + ((repetition == null) ? 0 : repetition.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Traffic other = (Traffic) obj;
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
		/*if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;*/
		/*
		 * if (repetition == null) { if (other.repetition != null) return false; } else
		 * if (!repetition.equals(other.repetition)) return false;
		 */
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/*public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}*/

}
