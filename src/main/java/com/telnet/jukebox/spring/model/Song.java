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
	// private String artistName;
	// private String genreName;
	// private int price;
	// private int artistId;
	// private int priceId;
	// private Long numOfPages;

	/*@Autowired
	private Genre genre;*/

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	@ManyToOne
	@JoinColumn(name = "price_id", nullable = false)
	private Price price;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "song")
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
		//this.genre = new Genre(genreName);
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

	/*
	 * public Long getNumOfPages() { return numOfPages; }
	 * 
	 * public void setNumOfPages(Long numOfPages) { this.numOfPages = numOfPages; }
	 */

	/*public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}*/

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
	//	result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		// result = prime * result + ((numOfPages == null) ? 0 : numOfPages.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		/*if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;*/
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
		/*
		 * if (numOfPages == null) { if (other.numOfPages != null) return false; } else
		 * if (!numOfPages.equals(other.numOfPages)) return false;
		 */
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	public List<Traffic> getTraffic() {
		return traffic;
	}

	public void setTraffic(List<Traffic> traffic) {
		this.traffic = traffic;
	}

}
