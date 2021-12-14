package com.huterox.whiteholeboot.Controller.UserController.UserCounter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.whiteholeboot.Config.RouterPath;
import com.huterox.whiteholeboot.EntityServer.UserServer.UserService;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import com.huterox.whiteholeboot.utils.MessageVo.TokePassJson.TokenPassJson;
import com.huterox.whiteholeboot.utils.TokenProccessor.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserLogin {

    @Autowired
    UserService userService;
    @ResponseBody
    @PostMapping(RouterPath.Login)
    /**
     * return {'success':0,'token':token}
     * 0: 返回账号密码错位
     * 1：请求正常，返回token
     */
    public TokenPassJson Login(@RequestBody Map<String, Object> usermap){
        String username = (String) usermap.get("username");
        String password = (String) usermap.get("password");
        QueryWrapper<User> UsersWrapper = new QueryWrapper<>();
        UsersWrapper.eq("UserName",username);
        UsersWrapper.eq("UserPassword",password);
        TokenPassJson tokenPassJson = new TokenPassJson();
        if(userService.IsExitsUser(UsersWrapper)){
            String token = TokenProccessor.createToken(username);
            tokenPassJson.setSuccess("1");
            tokenPassJson.setToken(token);
            return tokenPassJson;
        }
        tokenPassJson.setSuccess("0");
        tokenPassJson.setToken(null);
        return tokenPassJson;
    }
}
