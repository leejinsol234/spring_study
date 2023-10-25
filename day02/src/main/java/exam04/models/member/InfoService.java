package exam04.models.member;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoService {

    @NonNull //-> 생성자의 매개변수로 추가해준다.(상수로 만들면 값의 변경이 어려우므로)
    private MemberDao memberDao;
    //또는 private final MemberDao memberDao;

    /*
    public InfoService(MemberDao memberDao){ //기본 생성자가 없으므로 Autowired가 없어도 스프링이 의존성을 주입해준다.
        this.memberDao = memberDao;
    }
    */

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

    @Autowired
    public void setFormatter(@Nullable DateTimeFormatter formatter){
        //@Nullabe setter메서드는 호출되고, 의존하고 있는 객체(formatter)에 null값이 주입됨
        this.formatter = formatter;
    }

    public void print(){
        List<Member> members = memberDao.gets();
        members.stream().map(this::toConvert).forEach(System.out::println);
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
