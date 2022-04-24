package com.github.parkhana.data;

import lombok.Data;
import java.util.Date;

@Data
public class Board {

    int id;
    String contents;
    String nickname;
    Date uploaddate;
    int hits;
    int recommend;
}