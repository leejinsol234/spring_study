package exam04.main;


import exam04.config.*;
import exam04.models.member.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        JoinService joinService = ctx.getBean(JoinService.class);
        InfoService infoService = ctx.getBean(InfoService.class);

        Member member = Member.builder()
                .userId("user01")
                .userPw("12345678")
                .confirmUserPw("12345678")
                .userNm("사용자01")
                .email("user01@test.org")
                .agree(true)
                .regDt(LocalDateTime.now())
                .build();

        joinService.join(member);

        infoService.print();

        ctx.close();
    }
}
