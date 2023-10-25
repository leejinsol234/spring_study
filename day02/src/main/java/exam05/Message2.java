package exam05;


public class Message2 { //InitializingBean, DisposableBean를 구현할 수 없는 외부 클래스라고 가정
    public void send(String message){
        System.out.printf("전송 메세지:%s%n",message);
    }


    public void init(){
        System.out.println("init!!");
    }

    public void close(){
        System.out.println("close!!");
    }

}
