package exam03.main;


import exam03.models.member.*;
import exam03.config.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx01.class);

        JoinService joinService = ctx.getBean(JoinService.class);
        InfoService infoService = ctx.getBean(InfoService.class);

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
