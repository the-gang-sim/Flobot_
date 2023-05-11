package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import flobot.Command.LoginCommand;
import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MarketVO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class PwCheckService {
	@Autowired
	MarketMapper marketMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String upde, LoginCommand loginCommand, BindingResult result, HttpServletResponse response, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		MarketVO vo =marketMapper.marketSelectOne(authInfo.getUserNum());
		if(passwordEncoder.matches(loginCommand.getUserPw(), vo.getMarketPw())) {
			if(upde.equals("up")) {
				session.setAttribute("pwCheck", "up");
			}else {
				session.setAttribute("pwCheck", "de");
			}
		}else {
			result.rejectValue("userPw", "loginCommand.userPw","비밀번호가 틀렸습니다.");
		}
	}

}
