package com.github.parkhana.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.github.parkhana.vo.NetVo;

@Mapper
public interface NetDao {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	void updateRecommend(NetVo vo);
	
	void deleteNet(NetVo vo);
}
