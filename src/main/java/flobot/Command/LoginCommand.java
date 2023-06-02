package flobot.Command;

import lombok.Data;

@Data
public class LoginCommand {
	String userId;
	String userPw;
	String userPwCon;
	
	String newPw;
	String uesrEmail;
	String userName;
	String userPhone;
	Boolean rememId;
	Boolean rememLogin;
	String up;
	String de;
}
