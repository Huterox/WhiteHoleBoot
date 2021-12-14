package com.huterox.whiteholeboot.EntityServer.BokeServer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Mapper.BaseMapper.BaseChannelMapper;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.BlogAndBaseChannelMapper;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.BlogsBodyMapper;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.UserBlogsMapper;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.BlogAndBaseChannel;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.BlogsBody;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BlogsServer {

    @Autowired
    UserBlogsMapper userBlogsMapper;
    @Autowired
    BlogsBodyMapper blogsBodyMapper;

    @Autowired
    BlogAndBaseChannelMapper blogAndBaseChannelMapper;

    @Transactional(rollbackFor=Exception.class)  //开启事务
    public boolean SaveBlog(UserBlogs userBlogs,String content,Long channel_id ) throws Exception {
        //用户博客的保存
        try{
            BlogsBody blogsBody = new BlogsBody();
            blogsBody.setBody(content);
            blogsBodyMapper.insert(blogsBody);
            userBlogs.setBodyId(blogsBody.getId());
            userBlogsMapper.insert(userBlogs);
            blogsBody.setUserBlogId(userBlogs.getId());
            blogsBodyMapper.updateById(blogsBody);
            BlogAndBaseChannel blogAndBaseChannel = new BlogAndBaseChannel();
            blogAndBaseChannel.setChannelId(channel_id);
            blogAndBaseChannel.setUserBlogsId(userBlogs.getId());
            blogAndBaseChannelMapper.insert(blogAndBaseChannel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("博客保存异常");
        }
    }
    //网站首页的博客显示
    public Page GetNewBlogs(int page,int number){

        //属于基本base功能
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.plusDays(-7);

        QueryWrapper<UserBlogs> wrapper = new QueryWrapper<>();
        wrapper.eq("Pass",1);
        wrapper.eq("IsDelete",0);
        wrapper.between("CreateTime",yesterday,today);
        wrapper.last("order by Id desc");

        Page<UserBlogs> userBlogsPage = new Page<>(page,number);

        userBlogsMapper.selectPage(userBlogsPage, wrapper);
        return userBlogsPage;

    }
//官方频道的显示
    @Autowired
    BaseChannelMapper baseChannelMapper;
    public Page GetChannelBlogs(int channel_id,int page,int size){
        Page<UserBlogs> page2 = new Page<>(page,size);
        baseChannelMapper.GetChannelBlogs(page2,channel_id);
        return page2;
    }

//获取博客内容的玩意
    public List<BlogsBody> GetBlogBody(Long BlogId){
        QueryWrapper<BlogsBody> wrapper = new QueryWrapper<>();
        wrapper.eq("UserBlogId",BlogId);
        wrapper.last("order by Id desc");
        List<BlogsBody> blogsBodies = blogsBodyMapper.selectList(wrapper);
        return blogsBodies;
    }

//搜索页面使用的玩意
    public List<UserBlogs> GetSearchBlogs(String Like){
        QueryWrapper<UserBlogs> wrapper = new QueryWrapper<>();
        wrapper.likeRight("Title",Like);
        wrapper.or();
        wrapper.likeRight("ShuoMing",Like);
        List<UserBlogs> blogs = userBlogsMapper.selectList(wrapper);
        return blogs;
    }

}
