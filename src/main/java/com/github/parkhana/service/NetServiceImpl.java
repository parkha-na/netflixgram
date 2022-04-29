package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

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
	public List<NetVo> selectNetList(Map<String, Object> params) {
		return dao.selectNetList(params);
	}

	@Override
	public void updateRecommend(NetVo vo) {
		dao.updateRecommend(vo);
	}

	@Override
	public void deleteNet(NetVo vo) {
		dao.deleteNet(vo);
	}

	@Override
	public NetVo TOTALREPLY(NetVo vo) {
		return dao.TOTALREPLY(vo);
	}

	@Override
	public List<NetVo> selectReplyList(NetVo vo) {
		return dao.selectReplyList(vo);
	}

	@Override
	public List<NetVo> selectBoardList(NetVo vo) {
		return dao.selectBoardList(vo);
	}

	@Override
	public void insertReply(NetVo vo) {
		dao.insertReply(vo);
	}
}
