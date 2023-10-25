package exam04.models.member;

import exam02.commons.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //초기화할 수 있도록 필수 인자(상수) 생성자의 매개변수로 넣어준다.
public class JoinService {

    private final Validator<Member> validator;

    private final MemberDao memberDao;

    /*
    public void JoinService(Validator<Member> validator, MemberDao memberDao){
        this.validator = validator;
        this.memberDao = memberDao;
    }
    */
    //생성자 매개변수에 의존 객체를 정의(컴포넌트 스캔할 때만 가능, @Autowired 생략) -> 기본 생성자는 없음


    public void join(Member member) {

        validator.check(member);
        memberDao.register(member);

    }
}
