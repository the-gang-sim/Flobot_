package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("memVO")
public class MemberVO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberName;
	String memberAddr;
	String memberAddr2;
	String memberPost;
	Date memberRegiDate;
	String memberGender;
	String memberPhone;
	String memberEmail;
	Date memberBirth;
	String memberMarried;
	String grade;
	String memberOk;

}