package com.huterox.whiteholeboot.Controller.BaseController.BaseBlogsController.BaseControllerServer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.BlogsBody;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.EntityServer.BokeServer.BlogsServer;
import com.huterox.whiteholeboot.utils.MessageVo.BaseMessage.BaseGetBlogsMessage;
import com.huterox.whiteholeboot.utils.MessageVo.BaseMessage.BaseViewBlogsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 这部分的功能就是做首页的博客展示，官方的频道的博客展示 /base
 * 这边由于手残所有的 blog 都写错了 写成了 bolg 所以将错就错吧，顺便误导一下别人
 */
@Service
public class BaseBlogsGetServer {

    @Autowired
    BaseGetBlogsMessage baseGetBlogsMessage;
    @Autowired
    BlogsServer blogsServer;

    public BaseGetBlogsMessage GetBlogs(int page,int size){
        try {
            Page pages = blogsServer.GetNewBlogs(page, size);
            baseGetBlogsMessage.setUserBlogs(pages.getRecords());
            baseGetBlogsMessage.setSuccess(1);
            baseGetBlogsMessage.setTotal(pages.getTotal());
            return baseGetBlogsMessage;
        }catch (Exception e){
            baseGetBlogsMessage.setSuccess(0);
            return baseGetBlogsMessage;
        }

    }


    public BaseGetBlogsMessage GetBaseChannelBlogs(int channel_id,int page,int size){

        try {
            Page page1 = blogsServer.GetChannelBlogs(channel_id, page, size);
            List<UserBlogs> records = page1.getRecords();
            baseGetBlogsMessage.setUserBlogs(records);
            baseGetBlogsMessage.setTotal(page1.getTotal());
            baseGetBlogsMessage.setSuccess(1);
            return baseGetBlogsMessage;
        }catch(Exception e){
            baseGetBlogsMessage.setSuccess(0);
            return baseGetBlogsMessage;
        }
    }

    @Autowired
    BaseViewBlogsMessage baseViewBlogsMessage;


    public BaseViewBlogsMessage ViewBlogs(Long BlogId){
        try{

            List<BlogsBody> blogsBodies = blogsServer.GetBlogBody(BlogId);
            BlogsBody blogsBody = blogsBodies.get(0);
            baseViewBlogsMessage.setBody(blogsBody);
            baseViewBlogsMessage.setSuccess(1);
            return baseViewBlogsMessage;
        }catch (Exception e){
            e.printStackTrace();
            baseViewBlogsMessage.setSuccess(0);
            return baseViewBlogsMessage;
        }
    }

    public BaseGetBlogsMessage SearchBlogs(Map<String, Object> usermap) {
        try {
            String Like = (String) usermap.get("search");
            System.out.println(Like);
            List<UserBlogs> userBlogs = blogsServer.GetSearchBlogs(Like);
            baseGetBlogsMessage.setSuccess(1);
            baseGetBlogsMessage.setUserBlogs(userBlogs);
            baseGetBlogsMessage.setTotal((long) userBlogs.size());
            System.out.println(userBlogs.size());
            return baseGetBlogsMessage;
        }catch (Exception e){
            baseGetBlogsMessage.setSuccess(0);
            return baseGetBlogsMessage;
        }
    }
}
