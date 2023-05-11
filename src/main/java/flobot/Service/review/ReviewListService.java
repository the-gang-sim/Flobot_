package flobot.Service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.ReviewCommand;
import flobot.Mapper.MemberShipMapper;
import flobot.Mapper.ReviewMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import flobot.domain.ReviewVO;
import jakarta.servlet.http.HttpSession;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	MemberShipMapper memberShipMapper;
	
	public void execute(ReviewCommand reviewCommand, HttpSession session, Model model) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		MemberVO mem = memberShipMapper.myInfoSelect(authInfo.getUserId());
		ReviewVO vo = new ReviewVO();
		vo.setGoodsNum(reviewCommand.getGoodsNum());
		
		vo.setMemberNum(mem.getMemberNum());
		
		List<ReviewVO> list =reviewMapper.reviewList(vo);
		model.addAttribute("list",list);
	}

}
