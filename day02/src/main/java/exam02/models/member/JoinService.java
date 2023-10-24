package exam02.models.member;

import exam02.commons.Validator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JoinService {
    @Autowired
    private Validator<Member> validator;
    @Autowired
    private MemberDao memberDao;
/*
    public JoinService(Validator<Member> validator, MemberDao memberDao) {
        this.validator = validator;
        this.memberDao = memberDao;
    }

 */

    public void join(Member member) {

        validator.check(member);
        memberDao.register(member);
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
