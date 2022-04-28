package com.github.parkhana.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.parkhana.dao.NetDao;
import com.github.parkhana.vo.GoodVo;
import com.github.parkhana.vo.NetVo;

@Service
public class NetServiceImpl implements NetService {

	@Autowired
	private NetDao dao;
	
	@Override
	public int insertNet(NetVo vo) {
		return dao.insertNet(vo);
	}

	@Override
	public List<NetVo> selectNetList() {
		return dao.selectNetList();
	}

	@Override
	public void updateRecommend(NetVo vo) {
		dao.updateRecommend(vo);
	}

}
