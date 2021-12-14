package com.huterox.whiteholeboot.EntityServer.BaseServer;



import com.huterox.whiteholeboot.Entity.Mapper.BaseMapper.BaseChannelMapper;
import com.huterox.whiteholeboot.Entity.Pojo.BasePojo.BaseChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseChannelServer {
    @Autowired
    BaseChannelMapper baseChannelMapper;
    public List<BaseChannel> GetAllChannel(){
        return baseChannelMapper.selectList(null);
    }

}
