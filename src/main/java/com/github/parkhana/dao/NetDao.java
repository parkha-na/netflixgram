package com.github.parkhana.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.parkhana.vo.NetVo;

@Mapper
public interface NetDao {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList(NetVo vo);
	void updateRecommend(NetVo vo);
	void deleteNet(NetVo vo);
}
