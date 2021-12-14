package com.huterox.whiteholeboot.Controller.BaseController.BaseBlogsController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Config.RouterPath;
import com.huterox.whiteholeboot.Controller.BaseController.BaseBlogsController.BaseControllerServer.BaseBlogsGetServer;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.BlogsBody;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.EntityServer.BokeServer.BlogsServer;
import com.huterox.whiteholeboot.utils.MessageVo.BaseMessage.BaseGetBlogsMessage;
import com.huterox.whiteholeboot.utils.MessageVo.BaseMessage.BaseViewBlogsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 这部分的功能就是做首页的博客展示，官方的频道的博客展示 /base
 * 这边由于手残所有的 blog 都写错了 写成了 bolg 所以将错就错吧，顺便误导一下别人
 */
@Controller
@ResponseBody
public class BaseBlogsGet {

    @Autowired
    BaseGetBlogsMessage baseGetBlogsMessage;
    @Autowired
    BlogsServer blogsServer;

    @Autowired
    BaseBlogsGetServer baseBlogsGetServer;
    @GetMapping(RouterPath.GetBlogs)
    public BaseGetBlogsMessage GetBlogs(@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "size",defaultValue = "5") int size){
        return baseBlogsGetServer.GetBlogs(page,size);
    }

    @GetMapping(RouterPath.GetBaseChannelBlogs)
    public BaseGetBlogsMessage GetBaseChannelBlogs(@RequestParam(value ="channel_id",defaultValue = "1") int channel_id,@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "size",defaultValue = "5") int size){
        return baseBlogsGetServer.GetBaseChannelBlogs(channel_id,page,size);
    }


    @GetMapping(RouterPath.ViewBlogs)
    public BaseViewBlogsMessage ViewBlogs(@RequestParam("BlogId") Long BlogId){
      return baseBlogsGetServer.ViewBlogs(BlogId);
    }

    @PostMapping(RouterPath.SearchBlogs)
    public BaseGetBlogsMessage SearchBlogs(@RequestBody Map<String, Object> usermap){
        return baseBlogsGetServer.SearchBlogs(usermap);
    }


}
