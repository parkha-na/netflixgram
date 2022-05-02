package com.github.parkhana.dao;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;

import com.github.parkhana.vo.NetVo;

@Mapper
public interface NetDao {
	
	int insertNet(NetVo vo);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	int updateRecommend(NetVo vo);
	
	int deleteNet(NetVo vo);

	NetVo TOTALREPLY(NetVo vo);

	List<ReplyVo> selectReplyList(NetVo vo);

	NetVo selectNet(NetVo vo);

	int insertReply(ReplyVo vo);
	void deleteReply(ReplyVo vo);
}
