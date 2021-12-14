package com.huterox.whiteholeboot.Controller.BaseController.BaseChannel;

import com.huterox.whiteholeboot.Config.RouterPath;
import com.huterox.whiteholeboot.EntityServer.UserServer.UserService;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import com.huterox.whiteholeboot.EntityServer.BaseServer.BaseChannelServer;
import com.huterox.whiteholeboot.utils.MessageVo.BaseMessage.BaseChannelMessage;
import com.huterox.whiteholeboot.utils.TokenProccessor.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.huterox.whiteholeboot.Entity.Pojo.BasePojo.BaseChannel;

import java.util.List;

@Controller
@ResponseBody
public class BaseChannelController {

    @Autowired
    UserService userService;
    @Autowired
    BaseChannelMessage baseChannelMessage;
    @Autowired
    BaseChannelServer baseChannelServer;

    @PostMapping(RouterPath.GetAllChannels)
    public BaseChannelMessage GetAllChannels(){
        try {
            List<BaseChannel> baseChannels = baseChannelServer.GetAllChannel();
            baseChannelMessage.setSuccess(1);
            baseChannelMessage.setBaseChannels(baseChannels);
            return baseChannelMessage;
        }catch (Exception e){
            baseChannelMessage.setSuccess(0);
            return baseChannelMessage;
        }
    }






    public User VerifyUser(String token){
        String username = TokenProccessor.verify(token);
        User user = userService.GetUser(username);
        return user;
    }

}
