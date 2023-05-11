package flobot.Service.MemberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import flobot.Command.LoginCommand;
import flobot.Mapper.MemberMapper;
import flobot.Mapper.MemberShipMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberPwModyfyService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MemberShipMapper memberShipMapper;
	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		MemberVO vo =memberMapper.memberOneSelect(authInfo.getUserNum());
		if(passwordEncoder.matches(loginCommand.getUserPw(), vo.getMemberPw())) {
			vo.setMemberPw(passwordEncoder.encode(loginCommand.getNewPw()));
			memberShipMapper.memberPwModipy(vo);
		}else {
			result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
			return;
		}
	}

}
