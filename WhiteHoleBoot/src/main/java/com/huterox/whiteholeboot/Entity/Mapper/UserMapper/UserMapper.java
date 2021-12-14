package com.huterox.whiteholeboot.Entity.Mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
