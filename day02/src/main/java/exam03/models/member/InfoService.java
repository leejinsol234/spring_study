package exam03.models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class InfoService {
    //@Autowired
    //private MemberDao memberDao;
    @Autowired
    private Optional<MemberDao> opt;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
    //@Autowired(required = false) //setter형식에서도 의존성 자동 주입 가능
    //기본값이 true이므로 (required = false)로 설정하면 null값이 된다.
    @Autowired
    public void setFormatter(@Nullable DateTimeFormatter formatter){
        System.out.println("유입?");
        System.out.println(formatter);
        //@Nullabe setter메서드는 호출되고, 의존하고 있는 객체(formatter)에 null값이 주입됨
        this.formatter = formatter;
    }


    /*
    public InfoService(MemberDao memberDao){
        this.memberDao = memberDao; //생성자로 의존성 주입
    }
     */

    public void print(){
        MemberDao memberDao = opt.get();
        List<Member> members = memberDao.gets();
        members.stream().map(this::toConvert).forEach(System.out::println); //map(m ->
    }

    private Member toConvert(Member member){
        if(formatter == null){
            return member;
        }
        String regDtStr = formatter.format(member.getRegDt());
        member.setRegDtStr(regDtStr);
        return member;
    }
}
