package com.sparta.levelone.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    private static long visitCount = 0;

    @GetMapping("/static-hello")  // 직접 호출
    public String hello(){
        return "hello.html";
    }

    @GetMapping("/html/redirect")  // 재호출하는 법
    public String htmlStatic(){
        return "redirect:/hello.html";
    }

    @GetMapping("/html/templates")  // 템플릿에서 호출할 때는 html 빼고 호출
    public String htmlTemplates(){
        return  "hello";
    }

    @GetMapping("/html/dynamic")  // 모델에 담아서 출력해보기
    public String htmlDynamic(Model model){
        visitCount++;
        model.addAttribute("visits",visitCount);
        return  "hello-visit";

    }
}
