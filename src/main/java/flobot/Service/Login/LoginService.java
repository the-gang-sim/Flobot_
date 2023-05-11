package flobot.Service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import flobot.Command.LoginCommand;
import flobot.Mapper.LoginMapper;
import flobot.Mapper.MemberMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result, HttpServletResponse response) {
		AuthInfoVO vo = loginMapper.login(loginCommand.getUserId());
		if(vo != null) {
			if(vo.getGrade().equals("mem")) {
				MemberVO mem =memberMapper.memberOneSelect(vo.getUserNum());
				if(mem.getMemberOk()!=null) {
					if(passwordEncoder.matches(loginCommand.getUserPw(), vo.getUserPw())) {
						//로그인 성공
						session.setAttribute("authInfo", vo);
						if(loginCommand.getRememId() != null) {
							Cookie cookie = new Cookie("rememId", loginCommand.getUserId());
							cookie.setPath("/");
							cookie.setMaxAge(864000);
							response.addCookie(cookie);
						}else {
							Cookie cookie = new Cookie("rememId", "");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
						if(loginCommand.getRememLogin() != null) {
							Cookie cookie = new Cookie("rememLogin", loginCommand.getUserId());
							cookie.setPath("/");
							cookie.setMaxAge(60*60*24*30);
							response.addCookie(cookie);
						}
					}else {
						result.rejectValue("userPw", "loginCommand.userId", "비밀번호를 확인하세요.");
						//비밀번호를 확인하세요.
						return ;
					}
				}else {
					result.rejectValue("userId", "loginCommand.userId", "이메일을 확인하세요.");
					//이메일을 확인하세요.
					return ;
				}
			}
			if(passwordEncoder.matches(loginCommand.getUserPw(), vo.getUserPw())) {
				//로그인 성공
				session.setAttribute("authInfo", vo);
			}else {
				result.rejectValue("userPw", "loginCommand.userId", "비밀번호를 확인하세요.");
				//비밀번호를 확인하세요.
			}
		}else{
			result.rejectValue("userId", "loginCommand.userId", "아이디를 확인하세요.");
			//아이디를 확인하세요.
		}
	}
	
}
