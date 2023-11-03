package controllers.member;

import lombok.RequiredArgsConstructor;
import models.member.Member;
import models.member.MemberDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final MemberDao memberDao;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestLogin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestLogin form = (RequestLogin) target;

        /*
        * 1. 아이디가 존재하는지 체크
        * 2. 가입된 회원인지 데이터에서 조회-> 비밀번호 검증
        */

        //레코드 클래스로부터 검증할 데이터 가져오기
        String userId = form.userId();
        String userPw = form.userPw();

        Member member = null;
        if(userId != null && !userId.isBlank()){
            member = memberDao.get(userId);
            if(member == null){
                errors.reject("NotFound.userId");
            }
        }

        if(member != null && userPw != null && !userPw.isBlank()){
            boolean matched = BCrypt.checkpw(userPw, member.getUserPw()); //회원 비밀번호, 디비에 해시화된 비밀번호
            if(!matched){
                errors.reject("Incorrect.userPw");
            }
        }

    }
}
