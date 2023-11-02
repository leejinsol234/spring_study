package controllers.member;

import commons.MobileValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JoinValidator implements Validator, MobileValidator {

    @Override
    public boolean supports(Class<?> clazz) { //검증하고자 하는 커맨드 객체 클래스
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) { //target은 커맨드 객체
        RequestJoin form = (RequestJoin) target;

        /*
        추가 검증 부분
        1. 아이디 중복 여부 - 중복 가입X
        2. 비밀번호와 비밀번호 확인 일치 여부
        3. 휴대전화 번호(필수X) -> 입력되면 형식 체크
        */

        String userId = form.getUserId();
        String userPw = form.getUserPw();
        String confirmUserPw = form.getConfirmUserPw();
        String mobile = form.getMobile();

        //2. 비밀번호와 비밀번호 확인 일치 여부
        if(userPw != null && !userPw.isBlank() && confirmUserPw != null && !confirmUserPw.isBlank() && !userPw.equals(confirmUserPw)){
            errors.rejectValue("confirmUserPw","Mismatch");
        }

        //3. 휴대전화 번호(필수X) -> 입력되면 형식 체크
        // 재사용이 가능하도록 따로 떼서 작성하는 것이 좋다.
        if(mobile != null && !mobile.isBlank() && !checkMobile(mobile)) {
            errors.rejectValue("mobile","Mobile");
        }

        // 필수 항목 검증(userId, userPw, confirmUserPw, userNm, email) 데이터 가져오기
//        String userId = form.getUserId();
//        String userPw = form.getUserPw();
//        String confirmUserPw = form.getConfirmUserPw();
//        String userNm = form.getUserNm();
//        String email = form.getEmail();
//
//        if(userId == null || userId.isBlank()){
//            errors.rejectValue("userId","required");
//            // 기본 메세지가 없으면 messages/commons.properties로 대체할 수 있다.
//        }
//        if(userPw == null || userPw.isBlank()){
//            errors.rejectValue("userPw","required","비밀번호를 입력하세요.");
//        }
//        if(confirmUserPw == null || confirmUserPw.isBlank()){
//            errors.rejectValue("confirmUserPw","required","비밀번호를 확인하세요.");
//        }
//        if(userNm == null || userNm.isBlank()){
//            errors.rejectValue("userNm","required","회원명을 입력하세요.");
//        }
//        if(email == null || email.isBlank()){
//            errors.rejectValue("email","required","이메일을 입력하세요.");
//        }

        //위의 과정을 ValidationUtils를 통해 다음과 같이 작성할 수 있다.
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userId","required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userPw","required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmUserPw","required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userNm","required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","required");
    }

}
