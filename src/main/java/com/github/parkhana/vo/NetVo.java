package com.github.parkhana.vo;

import java.util.Date;
import lombok.Data;

@Data
public class NetVo {

	private int id;

	private String contents;

	private String nickname;

	private Date uploaddate;

	private int hits;

	private int recommend;
}
