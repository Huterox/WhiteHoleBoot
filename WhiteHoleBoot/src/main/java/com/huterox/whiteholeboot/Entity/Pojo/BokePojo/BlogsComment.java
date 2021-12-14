package com.huterox.whiteholeboot.Entity.Pojo.BokePojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blogscomment")
public class BlogsComment {
    @TableId(type = IdType.AUTO)
    private Long Id;
    private Long UserId;
    private Long BlogId;
    private String Comment;
}
