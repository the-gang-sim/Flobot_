package flobot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Command.DeliveryCommand;
import flobot.Command.PurchaseCommand;
import flobot.Mapper.PurchaseMapper;
import flobot.Service.iniPay.IniPayReqService;
import flobot.Service.iniPay.IniPayReturnService;
import flobot.Service.purchase.GoodsBuyService;
import flobot.Service.purchase.GoodsOrderListService;
import flobot.Service.purchase.GoodsOrderService;
import flobot.Service.purchase.MarketOrderListService;
import flobot.domain.DeliveryVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	IniPayReturnService iniPayReturnService;
	@Autowired
	GoodsOrderListService goodsOrderListService;
	@Autowired
	MarketOrderListService marketOrderListService;
	@Autowired
	PurchaseMapper purchaseMapper;
	
	@GetMapping("goodsBuy")
	public String goodsBuy(@RequestParam(value="purChk")String [] purChk, Model model, HttpSession session) {
		
		goodsBuyService.execute(purChk, model, session);
		return "thymeleaf/purchase/goodsBuy";
	}
	
	@GetMapping("order")
	public String order(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
		return "redirect:/purchase/paymentOk?purchaseNum="+purchaseNum+"&totalPrice="+purchaseCommand.getTotalPrice();		
	}
	@GetMapping("paymentOk")
	public String paymentOk(@RequestParam(value="purchaseNum")String purchaseNum
							,@RequestParam(value="totalPrice")Integer totalPrice,HttpSession session, Model model) {
		try {
			iniPayReqService.execute(purchaseNum, totalPrice,session, model );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";	
	}
	@PostMapping("INIstdpay_pc_return")
	public String INIstdpay_pc_return(
			HttpServletRequest request, HttpSession session, Model model ) {
		iniPayReturnService.execute(request, session, model);
		return "thymeleaf/purchase/buyfinished";
	}
	@PostMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
	
	@GetMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		goodsOrderListService.execute(session,model);
		
		return "thymeleaf/purchase/orderList";
	}
	
	@GetMapping("marketPurchaseList")
	public String marketPurchaseList(HttpSession session, Model model) {
		marketOrderListService.execute(session,model);
		return "thymeleaf/purchase/marketPurchaseList";
	}
	
	@GetMapping("deliveryUpdate")
	public String deliveryUpdate(DeliveryCommand deliveryCommand) {
		DeliveryVO vo = new DeliveryVO();
		vo.setDeliveryCompany(deliveryCommand.getDeliveryCompany());
		vo.setDeliveryNum(deliveryCommand.getDeliveryNum());
		vo.setPurchaseNum(deliveryCommand.getPurchaseNum());
		purchaseMapper.deliveryInsert(vo);
		purchaseMapper.deliveryUpdate(vo);
		return "redirect:/purchase/marketPurchaseList";
	}
	
	@GetMapping("temp")
	public String temp(@RequestParam(value="purchaseNum")String purchaseNum) {
		purchaseMapper.temp(purchaseNum);
		return "redirect:/purchase/marketPurchaseList";
	}
	
	@GetMapping("purchaseOk")
	public String purchaseOk(@RequestParam(value="purchaseNum")String purchaseNum) {
		purchaseMapper.purchaseOk(purchaseNum);
		return "redirect:/purchase/orderList";
	}
}
