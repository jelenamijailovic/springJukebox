package com.telnet.jukebox.spring.dto;

import java.sql.Date;

import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.User;

public class TrafficDTO {

	private Long id;
	private Date date;
	private Song song;
	private User user;

	public TrafficDTO() {

	}

	public TrafficDTO(Long id, Date date, String songName, String emailUsr) {
		super();
		this.id = id;
		this.date = date;
		this.song = new Song(songName);
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

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TrafficDTO other = (TrafficDTO) obj;
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

}
