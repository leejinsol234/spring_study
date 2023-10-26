package exam02.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Order(1) //프록시의 실행 순서가 중요한 경우는 순서를 명시해주며, 숫자가 작을수록 먼저 수행됨.
@Aspect
public class ProxyCached {
    private Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(long exam02.aopex..*(long))")
    public void publicTarget(){}

    @Around("publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        Long num = (long)args[0];
        if(cache.containsKey(num)){
            System.out.println("캐시에서 결과 가져옴");
            return cache.get(num);
        }
        Object result = joinPoint.proceed();
        cache.put(num,result);
        System.out.println("캐시에 값을 저장함");
        return result;
    }
}
