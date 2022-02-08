package com.cognixia.jump.react.databaseTest;

public class Book {
	
	private int bookID;
	private String title;
	
	private String authorName;
	private String publisher;
	private String Genre;
	private String coverURL;
	private String description;
	
	private boolean Released ;
	private int pageCount;
	private Integer franchiseId;
	private int seriesOrder;
	private Integer seriesId;
	public Book(int id, String title, String authorName, String publisher,int pageCount, String genre, Integer seriesId, int seriesOrder, boolean released,  Integer franchiseId, String coverURL, String description 
			   ) {
		
		
		this.bookID = id;
		this.title = title;
		this.authorName = authorName;
		this.publisher = publisher;
		Genre = genre;	
		this.coverURL = coverURL;
		Released = released;
		this.pageCount = pageCount;
		this.franchiseId = franchiseId;
		this.seriesOrder = seriesOrder;
		this.seriesId = seriesId;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Book [Title=" + title + ", id=" + bookID + ", Author=" + authorName + ", Publisher=" + publisher + ", Genre="
				+ Genre + ", coverURL=" + coverURL + ", description=" + description + ", Released=" + Released
				+ ", pageCount=" + pageCount + ", franchiseId=" + franchiseId + ", seriesOrder=" + seriesOrder
				+ ", seriesId=" + seriesId + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookId() {
		return bookID;
	}
	public void setBookId(int id) {
		this.bookID = id;
	}
	public String getAuthor() {
		return authorName;
	}
	public void setAuthor(String author) {
		this.authorName = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	public boolean isReleased() {
		return Released;
	}
	public void setReleased(boolean released) {
		Released = released;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getFranchiseId() {
		return franchiseId;
	}
	public void setFranchiseId(int franchiseId) {
		this.franchiseId = franchiseId;
	}
	public int getSeriesOrder() {
		return seriesOrder;
	}
	public void setSeriesOrder(int seriesOrder) {
		this.seriesOrder = seriesOrder;
	}
	public Integer getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
