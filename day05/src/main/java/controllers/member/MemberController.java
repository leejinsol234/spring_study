package controllers.member;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.member.JoinService;
import models.member.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member") //공통 url을 설정할 때
@RequiredArgsConstructor
public class MemberController {

    //@NonNull (final 대신)
    private final JoinValidator joinValidator;
    private final JoinService joinService;
    private final LoginValidator loginValidator;
    private final LoginService loginService;

    //요청 방식이 GET일 때
    @GetMapping("/join") // /member/join
    public String join(@ModelAttribute RequestJoin join ){
        //get방식일 때는 요청 데이터가 없으므로 th:field는 커맨드 객체가 없으면 오류 발생. 오류를 발생하지 않도록 하기 위해 비어있는 커맨드 객체 추가 -> 항상 해야 되는 과정이므로 @ModelAttribute로 비어있는 커맨드 객체 추가
//        RequestJoin requestJoin = new RequestJoin();
//        model.addAttribute("requestJoin", requestJoin);

        return "member/join";
    }

    //요청 방식이 POST일 때
    //@RequestMapping(method = RequestMethod.POST, path="/member/join") //spring4 이전 버전에서 사용
    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin join, Errors errors){
        //Errors객체는 커맨드 객체 바로 뒤에 작성해야 한다.

        joinValidator.validate(join,errors);

        if(errors.hasErrors()){
            // 한 개라도 reject 또는 rejectValue가 호출되면(검증에 실패하면) true가 된다.
            // 검증 실패시 유입
            return "member/join";
        }

        // 양식 제출 후 사용자가 입력한 기존 데이터가 보이지 않는 불편함을 해결하기 위해
        // model에 입력 받은 값들을 requestJoin이라는 이름으로(앞글자만 소문자로 바뀐 카멜케이스로 자동 생성) 담아준다.
        // (항상 해야되는 과정이므로 스프링이 RequestJoin을 통해 알아서 주입해준다.)단, 요청 데이터가 들어올 때만(POST)
        //model.addAttribute("requestJoin",join);

        // 검증 성공 시 회원가입
        joinService.join(join);

        return "redirect:/member/login"; // 응답 헤더를 통해 회원가입 후 로그인페이지로 이동
    }

    @GetMapping("/login") // /member/login
    public String login(@ModelAttribute RequestLogin form ,@CookieValue(required = false,name="saveId") String userId){ //saveId 키값의 이름과 동일해야 한다. 단, @CookieValue(required = false,name="saveId")에서 name값으로 키값과 동일한 이름을 설정해주면 다른 이름으로도 가능하다.
        if(userId !=null) { //쿠키가 있을 때

        }
        return "member/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String loginPs(@Valid RequestLogin form, Errors errors){

        loginValidator.validate(form, errors);

        if(errors.hasErrors()){
            return "member/login";
        }
        // 유효성 검사 성공 시 로그인 처리
        loginService.login(form);
        return "redirect:/"; //메인 페이지로 이동
    }

    @RequestMapping("/logout") //모든 요청에서 유입될 수 있도록
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/member/login";
    }
}
