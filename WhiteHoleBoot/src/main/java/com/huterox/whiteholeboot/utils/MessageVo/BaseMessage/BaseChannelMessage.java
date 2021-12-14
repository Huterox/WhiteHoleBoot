package com.huterox.whiteholeboot.utils.MessageVo.BaseMessage;

import com.huterox.whiteholeboot.Entity.Pojo.BasePojo.BaseChannel;
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
public class BaseChannelMessage {
    private int success;
    private List<BaseChannel> baseChannels;
}
