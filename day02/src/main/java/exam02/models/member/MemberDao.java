package exam02.models.member;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository //자동 스캔 대상이라고 알려주는 어노테이션
public class MemberDao {
    private static Map<String, Member> members = new HashMap<>();

    public void register(Member member) {

        String userPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt(12));
        member.setUserPw(userPw);
        members.put(member.getUserId(), member);
    }

    public Member get(String userId) {
        return members.get(userId);
    }

    public List<Member> gets(){ //가입된 회원을 List형태로 조회
        //Map에서 key값이 아닌 value값만 얻어오면 되는데 value값을 얻어오기 위해 Collections를 사용해야 하지만,
        //Collections는 형 변환해도 바뀌지 않음 -> ArrayList의 하위 클래스인 Collections<>으로 사용한다.
        //List<Member> data = new ArrayList<>(members.values());

        return new ArrayList<>(members.values()); //어차피 반환값만 필요하므로
    }

    public boolean exists(String userId) {
        return members.containsKey(userId);
    }

    public static void clearData() {
        members.clear();
    }
}
