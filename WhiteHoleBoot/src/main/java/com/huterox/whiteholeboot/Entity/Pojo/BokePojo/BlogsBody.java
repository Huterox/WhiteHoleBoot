package com.huterox.whiteholeboot.Entity.Pojo.BokePojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blogsbody")
public class BlogsBody {
    @TableId(type = IdType.AUTO)
    private Long Id;
    private String Body;
    private Long UserBlogId;
}
