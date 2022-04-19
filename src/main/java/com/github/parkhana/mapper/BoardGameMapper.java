package com.github.parkhana.mapper;

import com.github.parkhana.data.BoardGame;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardGameMapper {

    List<BoardGame> selectBoardGameList();
}
