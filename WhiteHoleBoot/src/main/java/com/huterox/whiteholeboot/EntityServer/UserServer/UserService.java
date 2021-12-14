package com.huterox.whiteholeboot.EntityServer.UserServer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huterox.whiteholeboot.Entity.Mapper.BokeMapper.UserBlogsMapper;
import com.huterox.whiteholeboot.Entity.Mapper.UserMapper.UserMapper;
import com.huterox.whiteholeboot.Entity.Pojo.BokePojo.UserBlogs;
import com.huterox.whiteholeboot.Entity.Pojo.UserPojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Transactional(rollbackFor=Exception.class)  //开启事务
    public void SaveUser(User user) throws Exception {
        try {
            userMapper.insert(user);
        }catch (Exception e){
            throw new Exception("用户注册异常");
        }

    }

    public boolean IsExitsUser(QueryWrapper<User> userQueryWrapper){
        List<User> users = userMapper.selectList(userQueryWrapper);
        if (users.size()>0){
            return true;
        }
        return false;
    }

    public User GetUser(QueryWrapper<User> userQueryWrapper){
        List<User> users = userMapper.selectList(userQueryWrapper);
        return users.get(0);
    }


    public User GetUser(String UserName){

        QueryWrapper<User> UsersWrapper = new QueryWrapper<>();
        UsersWrapper.eq("UserName",UserName);
        List<User> users = userMapper.selectList(UsersWrapper);
        if(users.size()==0){
            return null;
        }
        return users.get(0);

    }

    @Transactional(rollbackFor=Exception.class)
    public boolean UpdataUser(User user) throws Exception {
        try{
            userMapper.updateById(user);
            return true;
        }catch (Exception e){
            throw new Exception("用户数据更新异常");
        }
    }

    @Autowired
    UserBlogsMapper userBlogsMapper;
    public Page<UserBlogs> GetMyBlogs(Long userId,int page,int size){

        Page<UserBlogs> page1 = new Page<>(page,size);
        QueryWrapper<UserBlogs> blogwrapper = new QueryWrapper<>();
        blogwrapper.eq("UserId",userId);
        userBlogsMapper.selectPage(page1,blogwrapper);

        return page1;


    }


}
