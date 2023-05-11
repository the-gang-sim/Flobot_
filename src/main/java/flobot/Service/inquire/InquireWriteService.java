package flobot.Service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Command.InquireCommand;
import flobot.Mapper.InquireMapper;
import flobot.Mapper.MemberShipMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.InquireVO;
import flobot.domain.MemberVO;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireWriteService {
	@Autowired
	MemberShipMapper memberShipMapper;
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand inquireCommand,
			HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
		MemberVO mem = memberShipMapper.myInfoSelect(authInfo.getUserId());
		InquireVO vo = new InquireVO();
		vo.setGoodsNum(inquireCommand.getGoodsNum());
		vo.setInquireContent(inquireCommand.getInquireContent());
		vo.setInquireKind(inquireCommand.getInquireKind());
		vo.setInquireSubject(inquireCommand.getInquireSubject());
		vo.setMemberNum(mem.getMemberNum());
		vo.setMarketNum(inquireCommand.getMarketNum());
		
		String inquireNum = inquireMapper.inquireNumCreate();
		
		vo.setInquireNum(inquireNum);
		inquireMapper.inquireInsert(vo);
	}

}
