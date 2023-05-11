package flobot.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flobot.Command.FileDelCommand;
import flobot.Command.GoodsCommand;
import flobot.Service.Goods.GoodsDeleteService;
import flobot.Service.Goods.GoodsFileDelService;
import flobot.Service.Goods.GoodsInfoService;
import flobot.Service.Goods.GoodsListService;
import flobot.Service.Goods.GoodsRegiService;
import flobot.Service.Goods.GoodsUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsRegiService goodsRegiService;
	@Autowired
	GoodsInfoService goodsInfoService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@Autowired
	GoodsListService GoodsListService;
	@Autowired
	GoodsFileDelService goodsFileDelService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	
	@GetMapping("goodsList")
	public String goodsList(Model model, HttpSession session) {
		GoodsListService.execute(model, session);
		return "thymeleaf/goods/goodsList";
	}
	
	@GetMapping("goodsRegi")
	public String goodsRegi(GoodsCommand Goodscommand) {
		return "thymeleaf/goods/goodsRegi";
	}
	
	@PostMapping("goodsRegi")
	public String goodsRegi(@Validated GoodsCommand GoodsCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsRegi";
		}
		goodsRegiService.execute(GoodsCommand, model, session, result);
		return "thymeleaf/goods/goodsInfo";
	}
	
	@GetMapping("goodsInfo")
	public String goodsInfo(@RequestParam(value="goodsNum")String goodsNum,Model model) {
		goodsInfoService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsInfo";
	}
	@GetMapping("goodsDelete")
	public String goodsDelete(@RequestParam(value="goodsNum")String goodsNum, HttpSession session) {
		goodsDeleteService.execute(goodsNum, session );
		return "thymeleaf/Flobot";
	}
	@GetMapping("goodsUpdate")
	public String goodsUpdate(@RequestParam(value="goodsNum")String goodsNum, Model model) {
		goodsInfoService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsUpdate";
	}
	
	@PostMapping("fileDel")
	public @ResponseBody String fileDel(FileDelCommand fileDelCommand, HttpSession session) {
		String i = goodsFileDelService.execute(fileDelCommand, session);
		return i;
	}
	@PostMapping("goodsUpdate")
	public String goodsUpdate(GoodsCommand goodsCommand, Model model, HttpSession session) {
		goodsUpdateService.execute(goodsCommand, model,session);
		return "redirect:goodsList";
	}
	
	
}
