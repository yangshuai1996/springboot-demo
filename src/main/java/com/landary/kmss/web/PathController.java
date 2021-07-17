package com.landary.kmss.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 帅
 * @Date 2021/6/12 11:20
 * @Description 路径请求跳转
 **/
@Controller
public class PathController {

    @RequestMapping("/index")
    private String toIndexPage(){
        return "sun";
    }
}
