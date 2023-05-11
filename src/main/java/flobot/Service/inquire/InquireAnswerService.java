package flobot.Service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Command.InquireCommand;
import flobot.Mapper.InquireMapper;
import flobot.Mapper.MemberMapper;
import flobot.Service.EmailSendService;
import flobot.domain.AuthInfoVO;
import flobot.domain.InquireVO;
import flobot.domain.MarketVO;
import flobot.domain.MemberVO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireAnswerService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	MemberMapper memberMapper;
	public void execute(InquireCommand inquireCommand, HttpSession  session) {
		
		MarketVO market = new MarketVO();
		market.setMarketNum(inquireMapper.SelectMarketNum(inquireCommand.getMarketNum()));
		//marketName // select 해서 가져오기
		
		AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
		
		InquireVO vo = new InquireVO();
		vo.setInquireNum(inquireCommand.getInquireNum());
		vo.setInquireAnswer(inquireCommand.getInquireAnswer());
		
		
		
		vo.setMarketNum(market.getMarketNum());
		
		
		int i = inquireMapper.inquireAnswerUpdate(vo);
		if(i > 0) { // 메일링
			
			MemberVO mem = memberMapper.memberOneSelect(inquireCommand.getMemberNum()); 
			
			String subject = inquireCommand.getInquireSubject() +"의 답변";
			String content = inquireCommand.getInquireSubject() + "의 답변 <br />"
					       + inquireCommand.getInquireAnswer().replace("\n","<br />");
			String from = "highalnd0@naver.com";
			String to = mem.getMemberEmail();
			try {
				emailSendService.mailsend(content, subject, from, to);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
