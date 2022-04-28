package com.github.parkhana.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NetVo {

	private int id;

	private String contents;

	private String nickname;

	private Date uploaddate;

	private int recommend;

	// 실제 이미지 파일
	private MultipartFile imgFile;
	private String img; // 테이블 이미지 이름
	private String ch1;
	private String ch2;
}
