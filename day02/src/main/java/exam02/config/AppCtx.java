package exam02.config;

import exam02.models.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx { //Autowired로 대체
    /*
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public JoinValidator joinValidator(){
        JoinValidator joinValidator = new JoinValidator();
        //JoinValidator에서 setter를 통해 의존성이 주입되었기 때문에
        joinValidator.setMemberDao(memberDao());
        return joinValidator;
    }

//    @Bean
//    LoginValidator loginValidator(){
//        //생성자 매개변수로 의존성이 주입되었기 때문에
//        return new LoginValidator(memberDao());
//    }

    @Bean
    public JoinService joinService(){
        return new JoinService(joinValidator(),memberDao());
    }

//    @Bean
//    public LoginService loginService(){
//        return new LoginService(loginValidator(),memberDao());
//    }

    @Bean
    public InfoService infoService(){
        return new InfoService(memberDao());
    }

     */
}
