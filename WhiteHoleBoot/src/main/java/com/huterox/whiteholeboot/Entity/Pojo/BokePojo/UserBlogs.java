package com.huterox.whiteholeboot.Entity.Pojo.BokePojo;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("userblogs")
public class UserBlogs {
    @TableId(type = IdType.AUTO)
    private Long Id;
    private String Title;
    private Long UserId;

    private Long BodyId;
    private String ShuoMing;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT )
    private Date UpdateTime;
    private String Autoer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE )
    private Date CreateTime;
    private int IsDelete; //逻辑删除，但是这部分还是自己手动实现好一点。
    private String BlogPic; // 博客的封面
    private int Pass;//文章是否过审，由于目前没有后台管理所以暂时让它为1
}
