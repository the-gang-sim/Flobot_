package flobot.Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MemberMapper;
import flobot.domain.MemberVO;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberNum, Model model) {
		MemberVO vo = memberMapper.memberOneSelect(memberNum);
		model.addAttribute("memberCommand", vo);
	}
}