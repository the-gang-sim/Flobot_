package flobot.Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.MemberCommand;
import flobot.Mapper.MemberMapper;

@Service
public class MemberAutoNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand, Model model) {
		String memberNum = memberMapper.autoNum(); 
		memberCommand.setMemberNum(memberNum);
		model.addAttribute("memberCommand", memberCommand);
		
	}
}
