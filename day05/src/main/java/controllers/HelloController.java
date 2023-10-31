package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="nm",defaultValue = "이름없음") String name, int num1, boolean agree){
        //핸들러어댑터에서 컨트롤러를 실행할 때 같은 이름의 매개변수로 들어온 값을 찾아서 대입해 줌.
        //다른 이름으로 값을 넣을 경우 @RequestParam으로 매핑할 값을 알려줌
        System.out.printf("name=%s, num1=%d, agree=%s%n",name,num1,agree);
        return "hello";
    }
    // /WEB-INF/templates/hello.jsp


    /*
    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","반갑습니다."); //출력할 데이터(model) EL식으로 접근 가능
        mv.setViewName("hello"); //템플릿 경로(view)

        return mv;
    }
    */
}
