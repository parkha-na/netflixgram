package com.github.parkhana.dao;

import java.util.List;
import java.util.Map;

import com.github.parkhana.vo.RecommendedVo;
import com.github.parkhana.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;

import com.github.parkhana.vo.NetVo;

@Mapper
public interface NetDao {
	
	int insertNet(NetVo vo);

	int insertReply(ReplyVo vo);

	int insertRecommended(Map<String, Object> params);

	int updateNet(NetVo vo);

	int updateNetWithImg(NetVo vo);

	int updateRecommended(Map<String, Object> params);
	
	List<NetVo> selectNetList(Map<String, Object> params);

	NetVo selectNet(NetVo vo);

	List<ReplyVo> selectReplyList(NetVo vo);

	NetVo TOTALREPLY(NetVo vo);

	List<RecommendedVo> selectRecommended(Map<String, Object> params);

	int deleteNet(NetVo vo);

	int deleteReply(ReplyVo vo);

	int deleteRecommended(Map<String, Object> params);
}
