package controllers.member;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//커맨드 객체
@Data
public class RequestJoin {
    //NotBlank를 에러코드로 사용할 수 있다. (동일한 에러를 여러 곳에서 사용할 경우, 다국어 사용이 어려운 경우)
    @NotBlank //null이 아니고 최소한 한 개 이상의 공백아닌 문자를 포함하는지 검사한다.
    @Size(min=6) //최소 6자리 이상
    private String userId;

    @NotBlank
    @Size(min=8,max=16)
    private String userPw;

    @NotBlank
    private String confirmUserPw;

    @NotBlank
    private String userNm;

    @NotBlank
    @Email
    private String email;

    private String mobile;

    @AssertTrue
    private boolean agree;

    private Address addr;
    //중첩 커맨드 객체. addr.address 또는 addr.zipcode로 접근한다.

    //join.html의 name값과 동일하게 작성하면 알아서 매치해준다.
}
