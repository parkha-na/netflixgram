package com.github.parkhana.vo;

import java.util.Date;

import lombok.*;

import org.springframework.web.multipart.MultipartFile;

@Data
public class NetVo {
	private int id;						/* 게시판 id */
	private String contents;			/* 게시글 내용 */
	private String user_id;				/* 유저 id */
	private String nickname;			/* 유저 닉네임 */
	private Date createdate;			/* 글 작성일자 */
	private Date uploaddate;			/* 글 수정일자 */
	private int recommend;
	private int replyCnt;

	// 실제 이미지 파일
	private MultipartFile imgFile;
	private String img; // 테이블 이미지 이름
	private String ch1;
	private String ch2;
}
