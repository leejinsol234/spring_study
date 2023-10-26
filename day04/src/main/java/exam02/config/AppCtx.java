package exam02.config;


import exam02.aopex.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) //설정이 proxy형태로 추가됨(AspectJ 설정 자동화)
public class AppCtx {
    @Bean
    public Calculator calculator(){
        return new RecCalculator();
    }

    @Bean
    public ProxyCached proxyCached(){
        return new ProxyCached();
    }

    @Bean
    public ProxyCalculator proxyCalculator(){
        return new ProxyCalculator();
    }
}
