package models.member;

import lombok.*;

import java.time.LocalDateTime;

@Builder @Data
@AllArgsConstructor
@NoArgsConstructor //builder를 사용하면서 기본 생성자가 필요할 때 @AllArgsConstructor,@NoArgsConstructor로 만든다.
public class Member {
    private long userNo;
    private String userId;
    private String userPw;
    private String userNm;
    private String email;
    private String mobile;
    private LocalDateTime regDt; //가입 일시
    private LocalDateTime modDt;
}
