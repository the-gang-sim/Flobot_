package flobot.Service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Command.InquireCommand;
import flobot.Mapper.InquireMapper;
import flobot.domain.InquireVO;

@Service
public class InquireUpdateService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand inquireCommand) {
		InquireVO vo = new InquireVO();
		vo.setInquireContent(inquireCommand.getInquireContent());
		vo.setInquireKind(inquireCommand.getInquireKind());
		vo.setInquireNum(inquireCommand.getInquireNum());
		vo.setInquireSubject(inquireCommand.getInquireSubject());
		inquireMapper.inquireUpdate(vo);
		
	}

}
