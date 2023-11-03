package models.member;

import controllers.member.RequestJoin;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinValidator validator;
    private final MemberDao memberDao;

    public void join(RequestJoin form) {

        validator.check(form);
        Member member = new ModelMapper().map(form, Member.class);
        memberDao.register(member);
    }


}
