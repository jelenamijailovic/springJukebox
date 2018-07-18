/*package com.telnet.jukebox.spring.repository;

import java.util.List;

public class SongsByPage<T> {

	 public static final long DEFAULT_OFFSET = 0;
	    public static final int DEFAULT_MAX_NO_OF_ROWS = 100;
	    private int offset;
	    private int limit;
	    private long totalElements;
	    private List<Song> songs;
	    public SongsByPage(List<Song> songs, long totalElements, int offset, int limit) {
	        this.songs = songs;
	        this.totalElements = totalElements;
	        this.offset = offset;
	        this.limit = limit;
	    }
	    public boolean hasMore() {
	        return totalElements > offset + limit;
	    }
	    public boolean hasPrevious() {
	        return offset > 0 && totalElements > 0;
	    }
	    public long getTotalElements() {
	        return totalElements;
	    }
	    public int  getOffset() {
	        return offset;
	    }
	    public int getLimit() {
	        return limit;
	    }
	    public List<Song> getElements() {
	        return songs;
	    }
	
}
*/