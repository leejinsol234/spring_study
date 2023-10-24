package exam03.models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
