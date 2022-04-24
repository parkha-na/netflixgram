package com.github.parkhana.service;

import com.github.parkhana.data.Board;
import com.github.parkhana.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper bgMapper;

    /**
     * 게시판 리스트 가져오기 (DB select)
     * @param page
     * @param len
     * @return returnList
     * */
    public List<Board> selectBoardListToDB(int page, int len) {

        List<Board> returnList = new ArrayList<>();
        List<Board> list = bgMapper.selectBoardList();

        int start = 1 + ((page - 1) * len);
        int end = page * len;

        for (int cnt = (start - 1); cnt < end; cnt++) {
            if (cnt < list.size()) {
                Board board = list.get(cnt);
                returnList.add(board);
            }
        }

        return returnList;
    }
}
