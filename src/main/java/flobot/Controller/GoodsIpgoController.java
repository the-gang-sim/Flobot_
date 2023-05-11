package flobot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Command.GoodsIpgoCommand;
import flobot.Service.Goods.GoodsInfoService;
import flobot.Service.GoodsIpgo.GoodsIpgoService;

@Controller
@RequestMapping("goodsIpgo")
public class GoodsIpgoController {
	@Autowired
	GoodsInfoService goodsInfoService;
	@Autowired
	GoodsIpgoService goodsIpgoService;
	
	@GetMapping("goodsIpgo")
	public String goodsIpgo(@RequestParam(value="goodsNum")String goodsNum, GoodsIpgoCommand goodsIpgoCommand, Model model) {
		goodsInfoService.execute(goodsNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	
	@PostMapping("goodsIpgo")
	public String goodsIpgo(GoodsIpgoCommand goodsIpgoCommand, Model model) {
		System.out.println(goodsIpgoCommand.getGoodsNum());
		goodsIpgoService.execute(goodsIpgoCommand, model);
		return "redirect:/goods/goodsList";
	}
}
