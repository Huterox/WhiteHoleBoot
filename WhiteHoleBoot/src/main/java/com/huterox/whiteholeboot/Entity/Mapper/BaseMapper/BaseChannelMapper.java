package com.huterox.whiteholeboot.Entity.Mapper.BaseMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Pojo.BasePojo.BaseChannel;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BaseChannelMapper extends BaseMapper<BaseChannel> {
//    @Select("select * from userblogs as blog inner join blogandbasechannel as channel on channel.ChannelId=#{id}")
    IPage<UserBlogs> GetChannelBlogs(@Param("page") IPage<UserBlogs> page, @Param("id") int id);

}
