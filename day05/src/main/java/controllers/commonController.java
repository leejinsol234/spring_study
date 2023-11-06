package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("controllers") //controllers를 포함한 하위 모든 경로로 제한
public class commonController {
    @ExceptionHandler(Exception.class) //여러 개의 예외를 배열로도 담을 수 있다.또한 Model,HttpServletRequest,HttpServletResponse,HttpSession도 주입할 수 있다.
    public String errorHandler(Exception e, Model model){ //발생한 예외 객체를 주입
        //BadRequestException와 DuplicateMemberException의 공통 상위 클래스인 RuntimeException으로 주입한다. {BadRequestException.class, DuplicateMemberException.class} 역시 가장 상위 클래스인 Exception.class로 대체할 수 있다.
        e.printStackTrace();
        model.addAttribute("message",e.getMessage()); //템플릿 쪽에서 에러를 확인할 수 있도록
        return "error/common";
    }
}
