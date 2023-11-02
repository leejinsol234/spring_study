package controllers.member;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") //공통 url을 설정할 때
@RequiredArgsConstructor
public class MemberController {
    /*
    @Autowired
    private HttpServletRequest request; //spring container 안에 모두 담겨 있으므로 의존성 자동주입만 해도 사용 가능
    @GetMapping("/member/login")
    public String login(RequestLogin form, HttpServletResponse response){
        System.out.println(form);
        System.out.println(response);
        System.out.println(request.getParameter("userId"));
        return "member/login";
    }
     */

    /*
    @GetMapping("/member/join")
    public String join(Model model){
        String[] addCss = {"member/test1","member/test2"};
        List<String> addScript = Arrays.asList("member/script1","member/script2");

        model.addAttribute("addCss",addCss);
        model.addAttribute("addScript",addScript);
        model.addAttribute("pageTitle","회원가입");
        return "member/join";
    }

    @GetMapping("/member/login")
    public String login(Model model){
        model.addAttribute("userId","user99");
        model.addAttribute("userPw", "비밀번호");
        return "member/login"; //login.html로 인식
    }

    @GetMapping("/member/info")
    public String info(Model model){ //회원 조회
        Member member = Member.builder()
                .userNo(1L)
                .userId("<h1>user01</h1>")
                .userPw("123456")
                .userNm("사용자01")
                .email("user01@test.org")
                .mobile("010-0000-0000")
                .build();

        model.addAttribute("member",member);

        return "member/info";
    }
    @GetMapping("/member/list")
    public String members(Model model){
        List<Member> members = IntStream.rangeClosed(1,10).mapToObj(this::addMember).toList();
        model.addAttribute("members",members);
        return "member/list";
    }

    private Member addMember(int i){
        return Member.builder()
                .userNo(i * 1000)
                .userId("user"+i)
                .userPw("123456")
                .userNm("사용자"+i)
                .email("user"+i+"@test.org")
                .regDt(LocalDateTime.now())
                .build();
    }
     */
    //@NonNull (final 대신)
    private final JoinValidator joinValidator;

    //요청 방식이 GET일 때
    @GetMapping("/join") // /member/join
    public String join(@ModelAttribute RequestJoin join){
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
        return "redirect:/member/login"; // 응답 헤더를 통해 회원가입 후 로그인페이지로 이동

    }

    @GetMapping("/login") // /member/login
    public String login(){
        return "member/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String loginPs(){
        return "member/login";
    }
}
