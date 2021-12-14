package com.huterox.whiteholeboot.Controller.UserController.UserCounter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Config.RouterPath;
import com.huterox.whiteholeboot.Controller.UserController.UserCounter.UserSpaceServer.UserSpaceServer;
import com.huterox.whiteholeboot.utils.MessageVo.SpaceMessage.SpaceBlogsMessage;
import com.huterox.whiteholeboot.utils.MessageVo.SpaceMessage.SpaceMessage;
import com.huterox.whiteholeboot.utils.UploadImage.UpPhotoNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;


@Controller
@ResponseBody
public class UserSpeace {
    /**
     * 用户空间的接口
     */
    @Autowired
    UserSpaceServer userSpaceServer;
    @PostMapping(RouterPath.GetUserInfo)
    public SpaceMessage GetUserInfo(HttpServletRequest request){
        return userSpaceServer.GetUserInfo(request);
    }

    @PostMapping(RouterPath.ChangeUserInfo)
    public SpaceMessage ChangeUserInfo(HttpServletRequest request,
                                       @RequestBody Map<String, Object> usermap) throws Exception {
        return userSpaceServer.ChangeUserInfo(request,usermap);
    }

    @Autowired
    UpPhotoNameUtils upPhotoNameUtils;
    @PostMapping(RouterPath.ChangePic)
    public SpaceMessage ChangePic(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception{

        return userSpaceServer.ChangePic(file,request);
    }


    @GetMapping(RouterPath.GetMyBlogs)
    public SpaceBlogsMessage GetMyBlogs(HttpServletRequest request,@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "size",defaultValue = "3") int size){
        return userSpaceServer.GetMyBlogs(request,page,size);
    }

}
