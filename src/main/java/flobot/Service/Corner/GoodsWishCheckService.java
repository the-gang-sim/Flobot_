package flobot.Service.Corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.CornerMapper;
import flobot.Mapper.MemberShipMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import flobot.domain.WishVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishCheckService {
	@Autowired
	MemberShipMapper memberShipMapper;
	@Autowired
	CornerMapper cornerMapper;
	public void execute(String goodsNum, Model model, HttpSession session) {
		if(session.getAttribute("authInfo") != null) {
			AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
			if(authInfo.getGrade().equals("mem")) {
				MemberVO mem = memberShipMapper.myInfoSelect(authInfo.getUserId());
				WishVO vo = new WishVO();
				vo.setGoodsNum(goodsNum);
				vo.setMemberNum(mem.getMemberNum());
				model.addAttribute("wish", cornerMapper.wishCount(vo));
			}else {
				model.addAttribute("wish", "0")   ;
			}
		}else {
			model.addAttribute("wish", "0")   ;
		}
	}
}
