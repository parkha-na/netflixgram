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
	private int replyCnt;

	// 실제 이미지 파일
	private MultipartFile imgFile;
	private String img; // 테이블 이미지 이름
	private String ch1;
	private String ch2;
	private int K1;
}
