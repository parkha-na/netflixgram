package com.github.parkhana.service;

import java.util.List;
import com.github.parkhana.vo.NetVo;

public interface NetService {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList();
}
