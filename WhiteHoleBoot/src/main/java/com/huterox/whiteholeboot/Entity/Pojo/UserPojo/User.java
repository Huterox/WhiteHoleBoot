package com.huterox.whiteholeboot.Entity.Pojo.UserPojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long Id;
    private String UserName;
    private String UserPassword;
    private String UserPic; //没有默认值但是前端有人默认值
    private String UserInfo;
    private String UserSex;
    private String UserEmail;
    private String ChengHu;

}
