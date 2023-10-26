package exam02.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcut { //Pointcut만 떼어내서 따로 관리

    @Pointcut("execution(* exam02.aopex..*(..))") //proxy가 적용될 범위
    public void publicTarget(){}
}
