package com.github.parkhana.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.parkhana.dao.NetDao;
import com.github.parkhana.vo.NetVo;

@Service
public class NetServiceImpl implements NetService {

	private final NetDao dao;

	public NetServiceImpl(NetDao dao) {
		this.dao = dao;
	}

	@Override
	public int insertNet(NetVo vo) {
		return dao.insertNet(vo);
	}

	@Override
	public List<NetVo> selectNetList(int startPage, int endPage) {
		return dao.selectNetList(startPage, endPage);
	}

	@Override
	public void updateRecommend(NetVo vo) {
		dao.updateRecommend(vo);
	}

}
