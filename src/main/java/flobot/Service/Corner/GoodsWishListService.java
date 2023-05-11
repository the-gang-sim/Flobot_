package flobot.Service.Corner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.GoodsMapper;
import flobot.Mapper.MemberShipMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.GoodsVO;
import flobot.domain.MemberVO;
import flobot.domain.WishVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishListService {
	@Autowired
	MemberShipMapper memberShipMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
		MemberVO mem = memberShipMapper.myInfoSelect(authInfo.getUserId());
		WishVO vo = new WishVO();
		vo.setMemberNum(mem.getMemberNum());
		
		List<GoodsVO> list = goodsMapper.goodsWishListselect(vo);
		model.addAttribute("list", list);
	}

}
