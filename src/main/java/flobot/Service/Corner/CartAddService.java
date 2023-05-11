package flobot.Service.Corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.CornerMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.CartVO;
import jakarta.servlet.http.HttpSession;

@Service
public class CartAddService {

	@Autowired
	CornerMapper cornerMapper;
	public String execute(String goodsNum, Integer qty, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		CartVO vo = new CartVO();
		vo.setGoodsNum(goodsNum);
		vo.setQty(qty);
		vo.setMemberNum(authInfo.getUserNum());
		return cornerMapper.cartAdd(vo).toString();
	}
	public String execute(String goodsNum, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		CartVO vo = new CartVO();
		vo.setGoodsNum(goodsNum);
		vo.setMemberNum(authInfo.getUserNum());
		return cornerMapper.cartDown(vo).toString();
	}

}
