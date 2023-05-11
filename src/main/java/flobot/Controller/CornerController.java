package flobot.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flobot.Service.Corner.CartAddService;
import flobot.Service.Corner.CartListService;
import flobot.Service.Corner.GoodsWishCheckService;
import flobot.Service.Corner.GoodsWishListService;
import flobot.Service.Corner.GoodsWishService;
import flobot.Service.Corner.IndexGoodsListService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("corner")
public class CornerController {

	@Autowired
	IndexGoodsListService indexGoodsListService;
	@Autowired
	GoodsWishCheckService goodsWishCheckService;
	@Autowired
	CartAddService	cartAddService;
	@Autowired
	CartListService cartListService;
	
	@RequestMapping("prodInfo/{num}")
	public String prodInfo(
			@PathVariable(value="num") String goodsNum,Model model, HttpSession session) {
		indexGoodsListService.execute(goodsNum, model);
		goodsWishCheckService.execute(goodsNum, model, session);
		model.addAttribute("newLineChar", "\n");
		
		return "thymeleaf/corner/prodInfo";
	}
	
	@Autowired
	GoodsWishService goodsWishService;
	@RequestMapping(value="goodsWishAdd")
	public @ResponseBody String goodsWishAdd(
			@RequestParam(value="goodsNum")String goodsNum,
			HttpSession session) {
		return goodsWishService.execute(goodsNum, session);
	}
	@RequestMapping(value="goodsWishAdd2")
	public String goodsWishAdd2(@RequestParam(value="goodsNum")String goodsNum,@ModelAttribute(value="keyword")String keyword,@ModelAttribute(value="page")Integer page,	HttpSession session) {
		goodsWishService.execute(goodsNum, session);
		return "redirect:/search/goodsSearch?page="+page+"&keyword="+keyword;
	}
	
	@Autowired
	GoodsWishListService goodsWishListService;
	@RequestMapping(value="wishList")
	public String wishList(HttpSession session,Model model) {
		goodsWishListService.execute(session, model);
		return "thymeleaf/corner/wishList";
	}
	
	
	@GetMapping("cartAdd2")
	public String cartAdd(@RequestParam(value="goodsNum")String goodsNum,@ModelAttribute(value="keyword")String keyword,@ModelAttribute(value="page")Integer page, HttpSession session) {
		cartAddService.execute(goodsNum, 1,session);
		return "redirect:/search/goodsSearch?keyword="+keyword+"&page="+page;
	}
	@GetMapping("cartAdd3")
	public String cartAdd(@RequestParam(value="goodsNum")String goodsNum, HttpSession session) {
		cartAddService.execute(goodsNum, 1,session);
		return "redirect:/corner/wishList";
	}
	@PostMapping("cartAdd")
	public @ResponseBody String cartAdd2(@RequestParam(value="goodsNum")String goodsNum, @RequestParam(value="qty")Integer qty, HttpSession session) {
		String i = cartAddService.execute(goodsNum, qty,session);
		return i;
	}
	@PostMapping("CartDown")
	public @ResponseBody String CartDown(@RequestParam(value="goodsNum")String goodsNum, HttpSession session) {
		String i = cartAddService.execute(goodsNum, session);
		return i;
	}
	
	@GetMapping("cartList")
	public String cartList(HttpSession session,Model model) {
		cartListService.execute(session, model);
		return "thymeleaf/corner/cartList";
	}
	@PostMapping("buyItem")
	public String buyItem(
			@RequestParam(value="goodsNum") String goodsNum,
			@RequestParam(value="qty") Integer qty,
			HttpSession session,HttpServletResponse response) {
		cartAddService.execute(goodsNum, qty, session);
		
		return "redirect:/purchase/goodsBuy?purChk="+goodsNum;
	}
}