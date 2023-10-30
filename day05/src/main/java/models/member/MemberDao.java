package models.member;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
//@Transactional
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;
    /*
    public boolean register(Member member) {

        String userPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt(12));
        member.setUserPw(userPw);
        String sql = "INSERT INTO MEMBER (USER_NO,USER_ID,USER_PW,USER_NM,EMAIL,MOBILE) " +
                " VALUES (SEQ_MEMBER.nextval,?,?,?,?,?)";
        int affectedRows = jdbcTemplate.update(sql, member.getUserId(), userPw, member.getUserNm(), member.getEmail(), member.getMobile());
        return affectedRows > 0;
    }
    */

    @Transactional
    public boolean register(Member member) {
        String userPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt(12));
        String sql = "INSERT INTO MEMBER (USER_NO,USER_ID,USER_PW,EMAIL,USER_NM,MOBILE) " +
                " VALUES (SEQ_MEMBER.nextval,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder(); //증감번호 조회 시 prepareStatement 사용

        int affectedRows = jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"USER_NO"});
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, userPw);
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getUserNm());
            pstmt.setString(5, member.getMobile());
            return pstmt;
        }, keyHolder);

        if(affectedRows>0){
            long userNo = keyHolder.getKey().longValue();//상황에 맞게 유동적으로 사용할 수 있도록 Number로 반환한다.
            member.setUserNo(userNo);
        }

        return affectedRows > 0;

    }

    public Member get(String userId) { //queryForObject : 한 개의 레코드만 조회할 때(복수의 데이터나 없는 데이터를 조회하게 되면 오류 발생하므로 서비스 중단을 예방하기 위해 예외처리 필요)
        try {
            String sql = "SELECT * FROM MEMBER WHERE USER_ID = ?";
            Member member = jdbcTemplate.queryForObject(sql, this::mapper, userId);
            return member;
        } catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists(String userId) {
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE USER_ID = ?";
        //통계형 쿼리문은 정수값을 반환하므로 예외처리 하지 않아도 됨.
        long cnt = jdbcTemplate.queryForObject(sql, long.class, userId);

        return cnt > 0;
    }

    public List<Member> gets(){ //회원 목록을 조회할 수 있는 메서드
        String sql = "SELECT * FROM MEMBER ORDER BY REG_DT DESC";

        List<Member> members = jdbcTemplate.query(sql, this::mapper); //메서드 참조

        return members;
    }

    private Member mapper(ResultSet rs, int i) throws SQLException{ //반복되는 코드는 메서드로 따로 빼서 작성
        return Member.builder()
                .userNo(rs.getLong("USER_NO"))
                .userId(rs.getString("USER_ID"))
                .userPw(rs.getString("USER_PW"))
                .userNm(rs.getString("USER_NM"))
                .email(rs.getString("EMAIL"))
                .mobile(rs.getString("MOBILE"))
                .regDt(rs.getTimestamp("REG_DT").toLocalDateTime())
                .build();
    }
}


