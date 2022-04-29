package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.ReplyVo;
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
	public int updateRecommend(NetVo vo) {
		return dao.updateRecommend(vo);
	}

	@Override
	public int deleteNet(NetVo vo) {
		return dao.deleteNet(vo);
	}

	@Override
	public NetVo TOTALREPLY(NetVo vo) {
		return dao.TOTALREPLY(vo);
	}

	@Override
	public List<ReplyVo> selectReplyList(NetVo vo) {
		return dao.selectReplyList(vo);
	}

	@Override
	public NetVo selectNet(NetVo vo) {
		return dao.selectNet(vo);
	}

	@Override
	public int insertReply(ReplyVo vo) {
		return dao.insertReply(vo);
	}
}
