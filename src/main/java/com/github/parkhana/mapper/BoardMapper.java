package com.github.parkhana.mapper;

import com.github.parkhana.data.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> selectBoardList();
}
