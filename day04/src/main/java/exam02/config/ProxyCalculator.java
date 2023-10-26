package exam02.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(2)
@Aspect //proxy로 정의하는 어노테이션
public class ProxyCalculator {

//    @Pointcut("execution(* exam02.aopex..*(..))") //proxy가 적용될 범위
//    public void publicTarget(){}

    //@Around("publicTarget()") //핵심 기능과 공통 기능을 수행할 메서드
    //@Around("execution(* exam02.aopex..*(..))") //PointCut에 아무것도 없으면 Around에서 정의할 수 있음.
    @Around("CommonPointcut.publicTarget()") // Pointcut 따로 떼어냈기 때문에(동일 패키지 내에서는 패키지 경로 생략 가능)
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{
        long stime = System.nanoTime();

//        Signature sig = joinPoint.getSignature(); //반환값 타입 + 메서드 명 + 매개 변수 정보
//        System.out.println(sig.toLongString());

        Object[] args = joinPoint.getArgs(); //매개 변수에 대입된 값
        long num = (Long) args[0];
        System.out.println(num);

        try {
            System.out.println("proxy!");
            Object result = joinPoint.proceed(); // RecCalculator의 factorial(핵심기능) 대신 수행
            return result;
        } finally {
            long etime = System.nanoTime();
            System.out.printf("걸린 시간: %d%n", etime-stime);
        }
    }
    }
