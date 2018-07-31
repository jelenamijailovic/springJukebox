package com.telnet.jukebox.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "songs")
public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3725445170400546463L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	@ManyToOne
	@JoinColumn(name = "price_id", nullable = false)
	private Price price;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
	@JsonIgnore
	private List<Traffic> traffic;

	public Song() {

	}

	public Song(String name) {
		super();
		this.name = name;
	}

	public Song(Long id, String name, String artistName, Long price) {
		super();
		this.id = id;
		this.name = name;
		this.artist = new Artist(artistName);
		this.price = new Price(price);
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

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public List<Traffic> getTraffic() {
		return traffic;
	}

	public void setTraffic(List<Traffic> traffic) {
		this.traffic = traffic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((traffic == null) ? 0 : traffic.hashCode());
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
		Song other = (Song) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (traffic == null) {
			if (other.traffic != null)
				return false;
		} else if (!traffic.equals(other.traffic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", price=" + price + ", traffic=" + traffic
				+ "]";
	}

}
