package flobot.Service.Corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.CornerMapper;
import flobot.Mapper.MemberShipMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;
import flobot.domain.WishVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishService {
	@Autowired
	MemberShipMapper memberShipMapper;
	@Autowired
	CornerMapper cornerMapper;
	public String execute(String goodsNum, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
		MemberVO mem = memberShipMapper.myInfoSelect(authInfo.getUserId());
		WishVO vo = new WishVO();
		vo.setGoodsNum(goodsNum);
		vo.setMemberNum(mem.getMemberNum());
		cornerMapper.wishAdd(vo);
		return cornerMapper.wishCount(vo);
	}
}
