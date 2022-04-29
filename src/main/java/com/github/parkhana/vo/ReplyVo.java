package com.github.parkhana.vo;

import lombok.Data;

@Data
public class ReplyVo {

    private int replynumber;
    private String replynickname;
    private String replycontents;
    private int boardId;
}
