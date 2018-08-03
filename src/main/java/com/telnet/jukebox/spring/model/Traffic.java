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

@Entity
@Table(name = "traffic")
public class Traffic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5773971564012513934L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private String user;

	@ManyToOne
	@JoinColumn(name = "song_id", nullable = false)
	private Song song;

	public Traffic() {
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Traffic))
			return false;
		return id!=null && id.equals(((Traffic) obj).id);  
	}

	@Override
	public String toString() {
		return "Traffic [id=" + id + ", date=" + date + ", user=" + user + ", song=" + song + "]";
	}

}
