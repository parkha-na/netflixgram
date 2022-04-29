package com.github.parkhana.service;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.NetVo;
import com.github.parkhana.vo.ReplyVo;

public interface NetService {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	int updateRecommend(NetVo vo);
	
	int deleteNet(NetVo vo);

	NetVo TOTALREPLY(NetVo vo);

	List<ReplyVo> selectReplyList(NetVo vo);

	NetVo selectNet(NetVo vo);

	int insertReply(ReplyVo vo);
}
