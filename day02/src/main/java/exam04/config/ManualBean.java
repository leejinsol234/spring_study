package exam04.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //커스텀 애노테이션이 적용될 대상을 지정하는 애노테이션으로 선언할 수 있는 enum값
@Retention(RetentionPolicy.RUNTIME) //정보 전달 시점
public @interface ManualBean {

}
