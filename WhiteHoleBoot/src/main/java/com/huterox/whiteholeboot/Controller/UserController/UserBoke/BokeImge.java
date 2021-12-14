package com.huterox.whiteholeboot.Controller.UserController.UserBoke;


import com.huterox.whiteholeboot.utils.MessageVo.BokeMessage.BokeImgUpMessage;
import com.huterox.whiteholeboot.utils.UploadImage.UpPhotoNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class BokeImge {

    @Autowired
    UpPhotoNameUtils upPhotoNameUtils;
    @Autowired
    BokeImgUpMessage bokeImgUpMessage;
    @PostMapping("/boke/bokeImg")
    public BokeImgUpMessage ChangePic(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        //这个不需要验证，没必要！
        String userPic = upPhotoNameUtils.SaveUserPic("BokeImg", file);
        if(userPic==null){
            bokeImgUpMessage.setSuccess(0);
        }

        bokeImgUpMessage.setSuccess(1);
        bokeImgUpMessage.setBokeImg(userPic);
        return bokeImgUpMessage;
    }



}
