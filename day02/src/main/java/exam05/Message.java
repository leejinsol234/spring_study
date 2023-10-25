package exam05;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Message implements InitializingBean, DisposableBean {
    public void send(String message){
        System.out.printf("전송 메세지:%s%n",message);
    }

    @Override
    public void afterPropertiesSet() throws Exception { //초기화 단계에서 호출됨
        System.out.println("afterPropertiesSet()!!");
    }

    @Override
    public void destroy() throws Exception { //소멸 전에 호출됨
        System.out.println("destroy()!!!");
    }
}
