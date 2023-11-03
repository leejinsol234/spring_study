package controllers.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestLogin(
        @NotBlank
        String userId,
        @NotBlank
        String userPw,
        Boolean saveId
) {

}
