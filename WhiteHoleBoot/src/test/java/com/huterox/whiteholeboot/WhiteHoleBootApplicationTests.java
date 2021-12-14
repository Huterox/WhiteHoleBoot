package com.huterox.whiteholeboot;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Mapper.BaseMapper.BaseChannelMapper;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.BlogAndBaseChannelMapper;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.UserBlogsMapper;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.EntityServer.BokeServer.BlogsServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WhiteHoleBootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserBlogsMapper userBlogsMapper;
    @Autowired
    BaseChannelMapper baseChannelMapper;
    @Autowired
    BlogsServer blogsServer;

    @Test
    void GetChannelBlogs(){



        Page<UserBlogs> page2 = new Page<>(2,5);
        baseChannelMapper.GetChannelBlogs(page2,2);
        List<UserBlogs> records = page2.getRecords();
        records.forEach(System.out::println);
        System.out.println(page2.getTotal());


    }

}
