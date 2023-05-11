package flobot.Service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.PurchaseMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.OrderListVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsOrderListService {
	@Autowired
	PurchaseMapper purchaseMapper;

	public void execute(HttpSession session, Model model) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		List<OrderListVO> list = purchaseMapper.purchaseList(authInfo.getUserNum());
		model.addAttribute("list", list);
	}
	
}
