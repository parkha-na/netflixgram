package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.NetVo;
import com.github.parkhana.vo.RecommendedVo;
import com.github.parkhana.vo.ReplyVo;

public interface NetService {
	
	int insertNet(NetVo vo);

	int insertReply(ReplyVo vo);

	int insertRecommended(Map<String, Object> params);

	int updateRecommended(Map<String, Object> params);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	NetVo selectNet(NetVo vo);

	List<ReplyVo> selectReplyList(NetVo vo);

	NetVo TOTALREPLY(NetVo vo);

	List<RecommendedVo> selectRecommended(Map<String, Object> params);

	int deleteNet(NetVo vo);

	int deleteReply(ReplyVo vo);

	int deleteRecommended(Map<String, Object> params);

	String getServerLocation();
}
