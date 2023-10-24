package exam02.models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //자동 스캔 범위임을 알려줌
public class InfoService {
    @Autowired
    private MemberDao memberDao;

    /*
    public InfoService(MemberDao memberDao){
        this.memberDao = memberDao; //생성자로 의존성 주입
    }
     */

    public void print(){
        List<Member> members = memberDao.gets();
        members.stream().forEach(System.out::println);
    }
}
