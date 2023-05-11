package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("loginVO")
public class LoginVO {
	String userId;
	String userPw;
	String userName;
	String userNum;
	String grade;
}
