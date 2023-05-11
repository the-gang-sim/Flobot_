package flobot.Service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MemberMapper;
import flobot.Mapper.PurchaseMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.CartVO;
import flobot.domain.MemberVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsBuyService {
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(String[] purChk, Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		CartVO vo = new CartVO();
		vo.setGoodsNums(purChk);
		vo.setMemberNum(authInfo.getUserNum());
		List<CartVO> list = purchaseMapper.goodsList(vo);
		Integer tot = 0;
		String goodsNums = "";
		for(CartVO vo1 : list) {
			tot = vo1.getDeliveryCost() + (vo1.getQty() * vo1.getGoodsPrice());
			goodsNums += vo1.getGoodsNum() + "-";
		}
		MemberVO mem =memberMapper.memberOneSelect(authInfo.getUserNum());
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", tot);
		model.addAttribute("mem", mem);
		model.addAttribute("goodsNums", goodsNums);
	}
	
}
