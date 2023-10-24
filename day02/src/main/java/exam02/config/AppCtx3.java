package exam02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("exam02.models") //자동 스캔할 범위 설정
public class AppCtx3 {
}
