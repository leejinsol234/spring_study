package exam02.main;

import exam02.config.*;
import exam02.models.member.InfoService;
import exam02.models.member.JoinService;
import exam02.models.member.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx3.class);

        JoinService joinService = ctx.getBean("joinService", JoinService.class);
        InfoService infoService = ctx.getBean("infoService", InfoService.class);

        Member member = Member.builder()
                .userId("user01")
                .userPw("12345678")
                .confirmUserPw("12345678")
                .userNm("사용자01")
                .email("user01@test.org")
                .agree(true)
                .build();

        joinService.join(member);
        infoService.print();
        ctx.close();
    }
}
