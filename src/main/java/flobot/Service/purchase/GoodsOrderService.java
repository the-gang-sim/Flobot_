package flobot.Service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.PurchaseCommand;
import flobot.Mapper.PurchaseMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.CartVO;
import flobot.domain.PurchaseVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsOrderService {
	@Autowired
	PurchaseMapper purchaseMapper;

	public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		String purchaseNum = purchaseMapper.selectNum();
		String goodsNums [] =  purchaseCommand.getGoodsNums().split("-");
		
		PurchaseVO vo = new PurchaseVO();
		vo.setMemberNum(authInfo.getUserNum());
		vo.setPurchaseName(purchaseCommand.getPurchaseName());
		vo.setPurchaseAddr(purchaseCommand.getPurchaseAddr());
		vo.setPurchaseAddr2(purchaseCommand.getPurchaseAddr2());
		vo.setPurchaseMessage(purchaseCommand.getPurchaseMessage());
		vo.setPurchasePhone(purchaseCommand.getPurchasePhone());
		vo.setPurchasePost(purchaseCommand.getPurchasePost());
		vo.setPurchaseNum(purchaseNum);
		int i = purchaseMapper.goodsOrder(vo);
		if (i > 0) {
			vo.setGoodsNums(goodsNums);
			i = purchaseMapper.purchaseListInsert(vo);
			if(i > 0) {
				CartVO cartVO = new CartVO();
				cartVO.setMemberNum(authInfo.getUserNum());
				cartVO.setGoodsNums(goodsNums);
				purchaseMapper.cartItemDelete(cartVO);
			}
		}
		return purchaseNum;
	}

}
