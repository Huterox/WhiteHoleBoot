package com.huterox.whiteholeboot.Controller.UserController.UserBoke.BoKeSaveServer;

import com.huterox.whiteholeboot.EntityServer.UserServer.UserService;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import com.huterox.whiteholeboot.EntityServer.BokeServer.BlogsServer;
import com.huterox.whiteholeboot.utils.MessageVo.BokeMessage.BokeSaveMessage;
import com.huterox.whiteholeboot.utils.TokenProccessor.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class BokeSaveServer {
    @Autowired
    BokeSaveMessage bokeSaveMessage;
    @Autowired
    UserService userService;
    @Autowired
    BlogsServer blogsServer;
    public BokeSaveMessage BokeSaveServer(HttpServletRequest request,Map<String,Object> boke) throws Exception {
    String token = request.getHeader("token");
    User user = VerifyUser(token);

    Map blog = (Map) boke.get("boke");
    String title = (String) blog.get("title");
    String description = (String) blog.get("description");
    String first_picture = (String) blog.get("first_picture");
    Long channel_id = Long.valueOf((String) blog.get("channel_id") );

    String content = (String) blog.get("content");
    Long UserID = user.getId();

    UserBlogs userBlogs = new UserBlogs();
        userBlogs.setBlogPic(first_picture);
        userBlogs.setAutoer(user.getChengHu());
        userBlogs.setShuoMing(description);
        userBlogs.setUserId(UserID);
        userBlogs.setTitle(title);

        userBlogs.setPass(1); //暂时先默认通过，不然拿不显示的博客数据

    boolean flag = false;

    flag = blogsServer.SaveBlog(userBlogs, content,channel_id);
        if(flag){
        bokeSaveMessage.setSuccess(1);
    }
       else {
        bokeSaveMessage.setSuccess(0);
    }
        return bokeSaveMessage;
}


    public User VerifyUser(String token){
        String username = TokenProccessor.verify(token);
        User user = userService.GetUser(username);
        return user;
    }

}
