package flobot.Service.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MemberMapper;
import flobot.domain.MemberVO;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberWord, Model model) {
		List<MemberVO> list = memberMapper.memberAllSelect(memberWord);
		model.addAttribute("list", list);
		model.addAttribute("memberWord", memberWord);
	}
	
}
