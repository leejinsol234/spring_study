package exam04.config;

import exam04.models.member.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*
@ComponentScan(basePackages = "exam04.models",
               excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = ManualBean.class))
//exam04.models를 포함한 하위 패키지 전체(한 개 일때는 value 생략 가능)

 */
/*
@ComponentScan(basePackages = "exam04.models",
        excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes=MemberDao.class))

 */
@ComponentScan(basePackages = "exam04.models",
               excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ,
               pattern = "exam04.models..*Dao"))
public class AppCtx {

    /*
    @Bean
    public MemberDao memberDao(){

        return new MemberDao();
    }
    */
    //수동 등록된 Bean객체가 자동 등록된 Bean객체보다 적용 시 우선 순위가 높다.
}
