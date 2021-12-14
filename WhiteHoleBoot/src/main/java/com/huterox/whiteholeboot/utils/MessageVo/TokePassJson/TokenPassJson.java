package com.huterox.whiteholeboot.utils.MessageVo.TokePassJson;

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
public class TokenPassJson {
    private String success;
    private String token;
}
