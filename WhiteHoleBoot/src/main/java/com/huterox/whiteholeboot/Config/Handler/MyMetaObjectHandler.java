package com.huterox.whiteholeboot.Config.Handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("CreateTime",new Date(),metaObject);
        this.setFieldValByName("UpdateTime", new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updata_time", new Date(),metaObject);
    }
}
