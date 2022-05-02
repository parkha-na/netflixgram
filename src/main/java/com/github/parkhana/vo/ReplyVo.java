package com.github.parkhana.vo;

import lombok.Data;

@Data
public class ReplyVo {

    private int replynumber;
    private String replynickname;
    private String replycontents;
    private int board_id;
    private String user_id;
}
