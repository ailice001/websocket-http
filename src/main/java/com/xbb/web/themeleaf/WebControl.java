package com.xbb.web.themeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xuebinmeng on 2017/12/10.
 */
@Controller
public class WebControl {

    @RequestMapping("/socketDemo")
    public String toSocketIndex(){
        System.out.println("in socketDemo");
        return "webSocket";
    }

    @RequestMapping("/httpDemo")
    public String toSocketIndex_new(){
        System.out.println("in TestDemo");
        return "httpPoll";
    }

}
