package com.huterox.whiteholeboot.utils.MessageVo.BaseMessage;


import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class BaseGetBlogsMessage {
    private int success;
    private List<UserBlogs> userBlogs;
    private Long total;
}
