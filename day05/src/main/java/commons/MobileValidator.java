package commons;

public interface MobileValidator {
    default boolean checkMobile(String num){
        /*
        * 010 0000 0000
        * 011 000 0000
        * 016 000 0000
        *
        * 01000000000 -> 010 0000 0000/010-0000-0000/010.0000.0000
        * 다양한 형식으로 작성될 수 있으므로 숫자를 제외한 문자를 삭제한 뒤 숫자만 남긴다.
        * */

        String mobile = num.replaceAll("\\D","");
        String pattern = "^01[016]\\d{3,4}\\d{4}$";

        return mobile.matches(pattern);
    }
}
