package com.project.Blog.model;

public class BlogBoardVO {
	
	String num;
	String thumbnail;
	String title;
	String content;
	String writeday;
	String category_number;
	String category_snumber;
	String viewcount;
	String subcontent;
	String category;
	
	public String getSubcontent() {
		return subcontent;
	}
	public void setSubcontent(String subcontent) {
		this.subcontent = subcontent;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public String getCategory_number() {
		return category_number;
	}
	public void setCategory_number(String category_number) {
		this.category_number = category_number;
	}
	public String getCategory_snumber() {
		return category_snumber;
	}
	public void setCategory_snumber(String category_snumber) {
		this.category_snumber = category_snumber;
	}
	public String getViewcount() {
		return viewcount;
	}
	public void setViewcount(String viewcount) {
		this.viewcount = viewcount;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
			String category = "";
			
			if(category_snumber == null) {
				category_snumber = "3";
			}
			switch (category_snumber) {
			case "101":
				category = "JAVA";
				break;
			case "102":
				category = "JAVASCRIPT";
				break;
			case "103":
				category = "HTML,CSS";
				break;
			case "104":
				category = "DB";
				break;
			case "201":
				category = "여행";
				break;
			case "202":
				category = "책";
				break;
			case "203":
				category = "음악";
				break;
			case "204":
				category = "게임";
				break;
			case "205":
				category = "사진";
				break;
			case "206":
				category = "운동";
				break;
			case "207":
				category = "일상";
				break;
			case "301":
				category = "기타";
			}
			
		return category;
	}
	

}
