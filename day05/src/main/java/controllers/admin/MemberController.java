package controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController {

    @GetMapping
    public String list(@ModelAttribute @Valid MemberSearch search, Errors errors){
        //커맨드 객체 앞에 @ModelAttribute를 붙여서 요청 데이터가 없어서 발생하는 오류가 발생하지 않도록 비어있는 커맨드 객체 추가
        //@Valid 커맨드 객체 검증을 위한 어노테이션, 검증 후 에러 객체에 정보가 담긴다.
        System.out.println(search);
        return "admin/member/list";
    }

    @GetMapping("/{num}/info/{id}")
    public String info(@PathVariable(required = false, name="id") String userId, @PathVariable Integer num){
        //getMapping에서 지정한 변수를 info의 매개변수로 받는다.(경로 변수의 개수는 제한이 없으나 변수 명이 같아야 한다.
        // 필수 항목(true)이므로 (required = false)로 설정하면 null값이 들어간다. 또한, name값으로 변수명을 변경할 수 있다.)
        System.out.println(userId);
        System.out.println(num);
        return "/admin/member/info";
    }
}
