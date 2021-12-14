package com.huterox.whiteholeboot.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class TokenConfig implements WebMvcConfigurer {



//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowedOrigins("*");
//
//    }
//   // 跨域，我们前端做了跨域所以的话后端不用，也是为了安全，不能什么阿猫阿狗都过来访问
//
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> addinclidePath = new ArrayList<>();
        //拦截敏感页面

        addinclidePath.add("/space/**");  //个人空间接口
        addinclidePath.add("/userblog/**"); //处理用户的博客，修改删除，etc...


        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns(addinclidePath);

        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
