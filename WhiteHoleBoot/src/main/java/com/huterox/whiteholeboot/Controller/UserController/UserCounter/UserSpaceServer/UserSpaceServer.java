package com.huterox.whiteholeboot.Controller.UserController.UserCounter.UserSpaceServer;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.EntityServer.UserServer.UserService;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import com.huterox.whiteholeboot.utils.MessageVo.SpaceMessage.SpaceBlogsMessage;
import com.huterox.whiteholeboot.utils.MessageVo.SpaceMessage.SpaceMessage;
import com.huterox.whiteholeboot.utils.TokenProccessor.TokenProccessor;
import com.huterox.whiteholeboot.utils.UploadImage.UpPhotoNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class UserSpaceServer {
    /**
     * 用户空间的接口的server
     */
    @Autowired
    UserService userService;
    @Autowired
    SpaceMessage spaceMessage;

    public SpaceMessage GetUserInfo(HttpServletRequest request){

        String token = request.getHeader("token");

        User user = VerifyUser(token);
        SpaceMessage spaceMessage = new SpaceMessage();
        if(user!=null){
            spaceMessage.setUserInfo(user.getUserInfo());
            spaceMessage.setUserEmail(user.getUserEmail());
            spaceMessage.setSuccess(1);
            //这里的UserName是指用户的昵称，这里疏忽了名字取得不好
            spaceMessage.setUserName(user.getChengHu());
            spaceMessage.setUserPic(user.getUserPic());
            spaceMessage.setUserSex(user.getUserSex());
            return spaceMessage;
        }
        spaceMessage.setSuccess(0);
        return spaceMessage;
    }


    public SpaceMessage ChangeUserInfo(HttpServletRequest request,
                                       @RequestBody Map<String, Object> usermap) throws Exception {
        String token = request.getHeader("token");

        User user = VerifyUser(token);

        String name = (String) usermap.get("name");
        String email = (String) usermap.get("email");
        String sex = (String) usermap.get("sex");
        String userinfo =(String) usermap.get("userinfo");

        user.setUserInfo(userinfo);
        user.setUserEmail(email);
        user.setUserSex(sex);
        user.setChengHu(name);

        boolean flag = false;
        flag = userService.UpdataUser(user);
        if(flag)
            spaceMessage.setSuccess(1);
        else
            spaceMessage.setSuccess(0);
        return spaceMessage;
    }

    @Autowired
    UpPhotoNameUtils upPhotoNameUtils;

    public SpaceMessage ChangePic(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{

        String userPic = upPhotoNameUtils.SaveUserPic("UserPic", file);
        if(userPic==null){
            spaceMessage.setSuccess(0);
        }
        String token = request.getHeader("token");
        User user = VerifyUser(token);

        user.setUserPic(userPic);
        boolean flag = false;
        flag = userService.UpdataUser(user);
        if(flag){
            spaceMessage.setUserPic(user.getUserPic());
            spaceMessage.setSuccess(1);
        }

        else
            spaceMessage.setSuccess(0);
        return spaceMessage;
    }

    @Autowired
    SpaceBlogsMessage spaceBlogsMessage;

    public SpaceBlogsMessage GetMyBlogs(HttpServletRequest request,int page,int size){
        try {

            String token = request.getHeader("token");
            User user = VerifyUser(token);

            Long userId = user.getId();
            Page<UserBlogs> userBlogsPage = userService.GetMyBlogs(userId, page, size);
            spaceBlogsMessage.setUserBlogs(userBlogsPage.getRecords());
            spaceBlogsMessage.setTotal(userBlogsPage.getTotal());

            spaceBlogsMessage.setSuccess(1);
            return spaceBlogsMessage;
        }catch (Exception e){
            e.printStackTrace();
            spaceBlogsMessage.setSuccess(0);
            return spaceBlogsMessage;
        }
    }


    public User VerifyUser(String token){
        String username = TokenProccessor.verify(token);
        User user = userService.GetUser(username);
        return user;
    }


}
