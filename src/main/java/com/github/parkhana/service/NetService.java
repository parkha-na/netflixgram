package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.NetVo;

public interface NetService {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	void updateRecommend(NetVo vo);
	
	void deleteNet(NetVo vo);
	NetVo TOTALREPLY(NetVo vo);
}
