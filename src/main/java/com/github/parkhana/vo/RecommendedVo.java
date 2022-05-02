package com.github.parkhana.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecommendedVo {
    private int id;						/* 추천정보 id */
    private String board_id;			/* 게시판 id */
    private String user_id;				/* 유저 id */
    private Date create_date;			/* 글 작성일자 */
    private String use_yn;				/* 사용여부 */
}