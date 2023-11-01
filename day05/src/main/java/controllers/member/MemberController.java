package controllers.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") //공통 url을 설정할 때
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

    //요청 방식이 GET일 때
    @GetMapping("/join") // /member/join
    public String join(){
        return "member/join";
    }

    //요청 방식이 POST일 때
    //@RequestMapping(method = RequestMethod.POST, path="/member/join") //spring4 이전 버전에서 사용
    @PostMapping("/join")
    public String joinPs(){
        System.out.println("유입?");
        return "redirect:/member/login"; //회원가입 후 로그인페이지로 이동
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
