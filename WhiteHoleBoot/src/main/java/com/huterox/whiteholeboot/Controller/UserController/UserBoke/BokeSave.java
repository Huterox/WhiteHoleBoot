package com.huterox.whiteholeboot.Controller.UserController.UserBoke;


import com.huterox.whiteholeboot.Controller.UserController.UserBoke.BoKeSaveServer.BokeSaveServer;
import com.huterox.whiteholeboot.utils.MessageVo.BokeMessage.BokeSaveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@ResponseBody
/**
 * 这部分的接口需要用户的token验证
 */
public class BokeSave {

    @Autowired
    BokeSaveServer bokeSaveServer;
    @PostMapping("/userblog/saveblog")
    public BokeSaveMessage SaveBooke(HttpServletRequest request,@RequestBody Map<String, Object> boke) throws Exception {
        return bokeSaveServer.BokeSaveServer(request,boke);}

}
