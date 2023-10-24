package exam03.models.member;

import exam02.commons.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


public class JoinService {
    @Autowired
    private Validator<Member> validator;
    @Autowired
    @Qualifier("memberDao")
    //중복되는 Bean 객체가 있는 경우 이름으로 자동 주입할 Bean 객체를 한정하여 정확하게 알려주는 어노테이션
    private MemberDao memberDao3;

/*
    public JoinService(Validator<Member> validator, MemberDao memberDao) {
        this.validator = validator;
        this.memberDao = memberDao;
    }

 */

    public void join(Member member) {

        validator.check(member);
        memberDao3.register(member);
        //validator와 memberDao를 의존하고 있음 -> @Autowired
    }

//    public void join(HttpServletRequest request) {
//        String _agree = Objects.requireNonNullElse(request.getParameter("agree"), "false");
//        boolean agree = _agree.equals("true") ? true : false;
//
//        Member member = Member.builder()
//                .userId(request.getParameter("userId"))
//                .userPw(request.getParameter("userPw"))
//                .confirmUserPw(request.getParameter("confirmUserPw"))
//                .email(request.getParameter("email"))
//                .userNm(request.getParameter("userNm"))
//                .agree(agree)
//                .build();
//        join(member);
//    }
}
