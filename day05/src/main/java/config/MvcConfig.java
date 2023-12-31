package config;

import commons.Utils;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@Import(DbConfig.class)
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext ctx; //다형성을 기반으로 가장 상위 인터페이스로 정의한다.

//    @Autowired
//    private JoinValidator joinValidator;
//
//    @Override
//    public Validator getValidator() { //전역 Validator 설정(공통적인 Validator)
//        return joinValidator;
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberOnlyInterceptor())
                .addPathPatterns("/mypage/**");

        registry.addInterceptor(commonInterceptor())
                .addPathPatterns("/**"); //main을 포함한 전체 하위 경로
    }

    @Bean
    public CommonInterceptor commonInterceptor(){
        return new CommonInterceptor();
    }

    @Bean
    public MemberOnlyInterceptor memberOnlyInterceptor(){
        return new MemberOnlyInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //controller없이 view(url)를 연결
        registry.addViewController("/")
                .setViewName("main/index");
        registry.addViewController("/mypage/**") //mypage를 포함한 하위 모든 경로
                .setViewName("member/mypage");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 컨트롤러에 있는 Bean객체와 상관 없이 정적인 경로(css,js 등)에서 찾도록 활성화
        registry.addResourceHandler("/**") // "/**" : 하위 경로를 포함한 모든 경로
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(ctx);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false); //동일한 페이지는 한 번 번역해놓고 캐시에 담아놓을 수 있음. 개발 시 false로 템플릿 변화를 바로 반영되도록 한다.
               return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true); //타임리프에서도 EL식을 번역할 수 있다.
        templateEngine.addDialect(new Java8TimeDialect()); //dialect: 확장 모듈
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();

        ms.setBasenames("messages.commons");
        ms.setDefaultEncoding("UTF-8");

        return ms;
    }

    //내장 식 객체에 없는 기능은 Spring Bean으로 생성해서 사용할 수 있다.
    @Bean
    public Utils utils(){
        return new Utils();
    }

}
