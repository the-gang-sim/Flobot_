package flobot.Service.Login;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import flobot.Command.LoginCommand;
import flobot.Mapper.LoginMapper;
import flobot.Service.EmailSendService;
import flobot.domain.MemberVO;
import jakarta.mail.MessagingException;

@Service
public class ResetPwService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmailSendService emailSendService;
	public void execute(LoginCommand loginCommand, BindingResult result) {
		MemberVO vo = loginMapper.resetPw(loginCommand.getUesrEmail());
		String tempPw = UUID.randomUUID().toString().substring(0,8);
		String encPw = passwordEncoder.encode(tempPw);
		vo.setMemberPw(encPw);
		loginMapper.updatePw(vo);
		String content = vo.getMemberId() + "님의 임시비밀번호는 " + tempPw + "입니다.<br />"
				+ " 로그인 후 비밀번호를 변경해 주세요.";
		String to = vo.getMemberEmail();
		try {
			emailSendService.mailsend(content,"임시비밀번호","highland0@nate.com" ,to);
		} catch (MessagingException e) {
			e.printStackTrace();		
		}
	}
}