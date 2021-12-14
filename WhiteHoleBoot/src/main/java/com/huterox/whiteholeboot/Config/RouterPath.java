package com.huterox.whiteholeboot.Config;

public class RouterPath {

    //基础功能接口
    public static final String GetBlogs = "/base/bolgs";
    public static final String GetBaseChannelBlogs = "/base/getChannelBolgs";
    public static final String ViewBlogs = "/base/viewblog";
    public static final String GetAllChannels = "/getbasechannels";
    public static final String SearchBlogs = "/base/searchblogs";

    //基本的用户功能接口
    public static final String Login = "/login";
    public static final String SaveUser = "/regist";
    public static final String GetUserInfo = "/space/userinfo";
    public static final String ChangeUserInfo = "/space/changeuserinfo";
    public static final String ChangePic = "/space/changepic";
    public static final String GetMyBlogs = "/space/myblogs";

    //未来的管理员接口
}
