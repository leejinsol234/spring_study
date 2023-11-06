package controllers.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RequestLogin {

        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
        private boolean saveId;

}


