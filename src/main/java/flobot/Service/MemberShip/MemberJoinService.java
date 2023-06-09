package flobot.Service.MemberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.MemberCommand;
import flobot.Mapper.MemberShipMapper;
import flobot.Service.EmailSendService;
import flobot.Service.SMSSendService;
import flobot.domain.MemberVO;
import jakarta.mail.MessagingException;

@Service
public class MemberJoinService {
	@Autowired
	MemberShipMapper memberShipMapper;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	SMSSendService sMSSendService;

	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MemberCommand memberCommand, Model model) {
		
		String memberNum= 
		memberShipMapper.memberNumCreate();
		
		MemberVO vo = new MemberVO();
		vo.setMemberNum(memberNum);
		vo.setMemberAddr(memberCommand.getMemberAddr());
		vo.setMemberAddr2(memberCommand.getMemberAddr2());
		vo.setMemberBirth(memberCommand.getMemberBirth());
		vo.setMemberEmail(memberCommand.getMemberEmail());
		vo.setMemberGender(memberCommand.getMemberGender());
		vo.setMemberId(memberCommand.getMemberId());
		vo.setMemberName(memberCommand.getMemberName());
		vo.setMemberPhone(memberCommand.getMemberPhone());
		vo.setMemberPost(memberCommand.getMemberPost());
		vo.setMemberMarried(memberCommand.getMemberMarried());
		vo.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
		Integer i = memberShipMapper.MemberShipInsert(vo);
		System.out.println(i + " 행 이(가) 삽입되었습니다");
		
		model.addAttribute("userName", vo.getMemberName());
		model.addAttribute("userEmail", vo.getMemberEmail());
		
		String content="<html><body>"
		        + vo.getMemberName() + "님 가입을 환영합니다. <br/>"
		        + "<a href='http://localhost:8080/register/memberConfirm?chk=" + vo.getMemberEmail() +"'>"
		        + "가입하시려면 여기를 눌러주세요.</a>"
		        + "</body></html>";
		String subject=vo.getMemberId()+"님 환영 인사입니다.";
		String toEmail=vo.getMemberEmail();
		if(i>0) {
			try {
				emailSendService.mailsend(content, subject, "highland0@nate.com", toEmail);
				
				/// SMS
				content = "안녕하세요. Flobot쇼핑몰입니다. <br/>"
						 + vo.getMemberName() + "님 가입을 축하드립니다."
						 + "신고는 080-123-1234으로 연락주세요.";
				String _from = "010-7146-1970";
				String _to = vo.getMemberPhone();
				sMSSendService.smsSend(_from, _to, content);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}
