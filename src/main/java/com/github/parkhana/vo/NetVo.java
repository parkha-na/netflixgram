package com.github.parkhana.vo;

import java.util.Date;

import lombok.*;

import org.springframework.web.multipart.MultipartFile;

@Data
public class NetVo {

	private int id;
	private String contents;
	private String nickname;
	private Date uploaddate;
	private int recommend;
	private String img;	/* 테이블 이미지 이름 */
	private MultipartFile imgFile;	/* 실제 이미지 파일 */
//	@Builder
//	public NetVo(int id, String contents, String nickname, Date uploaddate, int recommend, String img) {
//		this.id = id;
//		this.contents = contents;
//		this.nickname = nickname;
//		this.uploaddate = uploaddate;
//		this.recommend = recommend;
//		this.img = img;
//	}
}
