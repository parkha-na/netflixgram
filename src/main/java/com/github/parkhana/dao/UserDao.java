package com.github.parkhana.dao;

import com.github.parkhana.vo.Users;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface UserDao {

    int insertUser(Users vo);

    Users selectUserOne(Map<String, Object> params);
}
