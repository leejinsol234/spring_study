package exam03.config;

import exam03.models.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppCtx02 {
    @Bean
    public JoinValidator joinValidator(){
        return new JoinValidator();
    }

    @Bean
    public JoinService joinService(){
        return new JoinService();
    }

    @Bean
    public InfoService infoService(){
        return new InfoService();
    }

    /*
    @Bean
    public DateTimeFormatter dateTimeFormatter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return formatter;
    }
    */
}
