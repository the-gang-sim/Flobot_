package flobot.Service.MemberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import flobot.Command.LoginCommand;
import flobot.Mapper.MemberMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberPwChkService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	public int execute(LoginCommand loginCommand, BindingResult result,HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		MemberVO vo =memberMapper.memberOneSelect(authInfo.getUserNum());
		if(passwordEncoder.matches(loginCommand.getUserPw(), vo.getMemberPw())) {
			return 1;
		}else {
			result.rejectValue("userPw", "loginCommand.userPw","비밀번호가 틀렸습니다.");
			return 0;
		}
	}

}
