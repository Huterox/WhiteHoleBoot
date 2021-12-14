package com.huterox.whiteholeboot.utils.UploadImage;



import com.huterox.whiteholeboot.Config.PhotosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 调好的别乱动！
 * 生成 uuid 用户为图片，按照年月日创建文件夹，将文件放置与此
 * 返回 static 下图片的相对存放位置：/UserPic/2021/12/1/2c1c1a31-bd2f-463d-9290-bb05221a802a1638351137690black.jpg
 * @param: LodPath static目录下存放图片的跟目录
 *
 */
@Component
public class UpPhotoNameUtils {
    @Autowired
    PhotosConfig photosConfig;
    static String UserPicRoot = "static/";

    public String SaveUserPic(String LodPath ,MultipartFile file) {

        String contentType = file.getContentType();
        //限制文件格式
        if(!photosConfig.getAllowTypes().contains(contentType)){
            return null;
        }

        String filename = UUID.randomUUID().toString() + System.currentTimeMillis();
        //先创建文件夹
        Calendar cad = Calendar.getInstance();
        String month = String.valueOf(cad.get(Calendar.MONTH) + 1);
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = String.valueOf(cad.get(Calendar.DAY_OF_MONTH));
        try {
            UserPicRoot= ResourceUtils.getURL("classpath:").getPath()+UserPicRoot;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String RevPath = LodPath +"/"+ String.valueOf(cad.get(Calendar.YEAR)) +"/"+ month+"/"+day;
        String savePath = UserPicRoot+RevPath;
//        System.out.println(savePath);
        String realsavePath = savePath.replace('/', '\\').substring(1,savePath.length());
//        System.out.println(realsavePath);
        //这个是为了拿到真实的存储地址，返回结果如下：C:\Users\31395\Desktop\WhiteHole\WhiteHoleBoot\target\classes\static\UserPic\2021\12\1
        File dirFile = new File(realsavePath);
        boolean  bFile = dirFile.exists();
        if(!bFile){
            dirFile.mkdirs();
        }


        RevPath = RevPath+"/"+ filename + file.getOriginalFilename();
        savePath = (UserPicRoot+RevPath);

        try {
            file.transferTo(new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(savePath);
        return "/" + RevPath;
    }
}
