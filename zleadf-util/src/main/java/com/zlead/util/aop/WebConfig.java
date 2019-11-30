///**
// * @program: zleadf-parent
// * @description:WebConfig
// * @author: ytchen
// * @create: 2019-02-15 11:39
// **/
//package com.zlead.util.aop;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.context.annotation.*;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.*;
//
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//
//
///**
// * Created by ThinkPad on 2019-02-15 11:45
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.zlead.util.aop"},useDefaultFilters = true)
//@PropertySource({"classpath:aop.WebConfig"})
//public class WebConfig extends WebMvcConfigurerAdapter{
//
//    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/views/jsp/function/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//    //静态文件
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        logger.info("addResourceHandlers");
//        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
//    }
//
//    //允许跨域的接口
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/*").allowedOrigins("*")
//                .allowCredentials(false)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Headers","Access-Control-Allow-Methods"
//                        ,"Access-Control-Max-Age")
//                .exposedHeaders("Access-Control-Allow-Origin")
//                .maxAge(3600);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getTokenHeader())
//                .addPathPatterns("/api/*")
//                .excludePathPatterns(
//                        "/robots.txt");
//    }
//
//    //token 在header的拦截器
//    @Bean
//    public HandlerInterceptor getTokenHeader(){
//        return new HeaderTokenInterceptor();
//    }
//
//
//}
