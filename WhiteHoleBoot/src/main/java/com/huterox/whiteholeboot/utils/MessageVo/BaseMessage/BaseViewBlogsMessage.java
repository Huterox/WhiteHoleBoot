package com.huterox.whiteholeboot.utils.MessageVo.BaseMessage;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.BlogsBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class BaseViewBlogsMessage {
    private int success;
    private BlogsBody Body;
}
