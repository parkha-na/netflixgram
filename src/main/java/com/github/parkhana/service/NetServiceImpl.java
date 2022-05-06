package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.RecommendedVo;
import com.github.parkhana.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.parkhana.dao.NetDao;
import com.github.parkhana.vo.NetVo;

@Service
public class NetServiceImpl implements NetService {

	@Value("${app.upload.dir}")
	private String uploadDirTarget;

	private final NetDao dao;

	public NetServiceImpl(NetDao dao) {
		this.dao = dao;
	}

	@Override
	public int insertNet(NetVo vo) {
		return dao.insertNet(vo);
	}

	@Override
	public int insertReply(ReplyVo vo) {
		return dao.insertReply(vo);
	}

	@Override
	public int insertRecommended(Map<String, Object> params) {
		return dao.insertRecommended(params);
	}

	@Override
	public int updateNet(NetVo vo) {
		return dao.updateNet(vo);
	}

	@Override
	public int updateNetWithImg(NetVo vo) {
		return dao.updateNetWithImg(vo);
	}

	@Override
	public int updateRecommended(Map<String, Object> params) {
		return dao.updateRecommended(params);
	}

	@Override
	public List<NetVo> selectNetList(Map<String, Object> params) {
		return dao.selectNetList(params);
	}

	@Override
	public NetVo selectNet(NetVo vo) {
		return dao.selectNet(vo);
	}

	@Override
	public List<ReplyVo> selectReplyList(NetVo vo) {
		return dao.selectReplyList(vo);
	}

	@Override
	public NetVo TOTALREPLY(NetVo vo) {
		return dao.TOTALREPLY(vo);
	}

	@Override
	public List<RecommendedVo> selectRecommended(Map<String, Object> params) {
		return dao.selectRecommended(params);
	}

	@Override
	public int deleteNet(NetVo vo) {
		return dao.deleteNet(vo);
	}

	@Override
	public int deleteReply(ReplyVo vo) {
		return dao.deleteReply(vo);
	}

	@Override
	public int deleteRecommended(Map<String, Object> params) {
		return dao.deleteRecommended(params);
	}

	@Override
	public String getServerLocation() {
		return uploadDirTarget;
	}
}
