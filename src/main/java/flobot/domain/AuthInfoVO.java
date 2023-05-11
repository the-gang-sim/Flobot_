package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authInfoVO")
public class AuthInfoVO {
	String userId;
	String userPw;
	String userName;
	String userNum;
	String grade;
}
