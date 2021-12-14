package com.huterox.whiteholeboot.Entity.Pojo.BasePojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("basechannel")
public class BaseChannel {
    @TableId(type = IdType.AUTO)
    private Long Id;
    private String ChannelName;
    private String ChannelPic;
}
