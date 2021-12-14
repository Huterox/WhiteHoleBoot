package com.huterox.whiteholeboot.utils.MessageVo.SpaceMessage;

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
public class SpaceMessage {
    private String userName;
    private String userInfo;
    private String userSex;
    private String userEmail;
    private String userPic;
    private int success;
}
