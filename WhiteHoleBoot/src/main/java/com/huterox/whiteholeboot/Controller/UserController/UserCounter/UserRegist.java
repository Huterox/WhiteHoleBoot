package com.huterox.whiteholeboot.Controller.UserController.UserCounter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.whiteholeboot.Config.RouterPath;
import com.huterox.whiteholeboot.EntityServer.UserServer.UserService;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import com.huterox.whiteholeboot.utils.MessageVo.RegistMessage.RegistMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserRegist {
    @Autowired
    UserService userService;
    @Autowired
    RegistMessage registMessage;
    @ResponseBody
    @PostMapping(RouterPath.SaveUser)
    public RegistMessage SaveUser(@RequestBody Map<String, Object> usermap) throws Exception {
        String username = (String) usermap.get("username");
        String password = (String) usermap.get("password");

        QueryWrapper<User> UsersWrapper = new QueryWrapper<>();
        UsersWrapper.eq("UserName",username);
        if(userService.IsExitsUser(UsersWrapper)){
            registMessage.setFlag(0);
            return registMessage;
        }
        else {
            User user = new User();
            user.setUserName(username);
            user.setUserPassword(password);
            userService.SaveUser(user);
            registMessage.setFlag(1);
            return registMessage;
        }


    }
}
