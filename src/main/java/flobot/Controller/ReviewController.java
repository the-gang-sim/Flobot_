package flobot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Command.ReviewCommand;
import flobot.Mapper.GoodsMapper;
import flobot.Mapper.ReviewMapper;
import flobot.Service.review.ReviewListService;
import flobot.Service.review.ReviewRegiService;
import flobot.Service.review.ReviewUpdateService;
import flobot.domain.GoodsVO;
import flobot.domain.ReviewVO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	ReviewRegiService reviewRegiService;
	@Autowired
	ReviewListService reviewListService;
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	ReviewUpdateService reviewUpdateService;
	
	@GetMapping("reviewRegi")
	public String reviewRegi(ReviewCommand reviewCommand, Model model) {
		GoodsVO vo = goodsMapper.goodsInfo(reviewCommand.getGoodsNum());
		model.addAttribute("vo", vo);
		model.addAttribute("purchaseNum", reviewCommand.getPurchaseNum());
		return "thymeleaf/review/reviewRegi";
	}
	
	@PostMapping("reviewRegi")
	public String reviewRegi(ReviewCommand reviewCommand) {
		reviewRegiService.execute(reviewCommand);
		return "redirect:/purchase/orderList";
	}
	
	@GetMapping("reviewList")
	public String reviewList(ReviewCommand reviewCommand,HttpSession session, Model model ) {
		reviewListService.execute(reviewCommand,session, model);
		return"thymeleaf/review/reviewList";
	}
	
	@GetMapping("reviewUpdate")
	public String reviewUpdate(ReviewCommand reviewCommand,Model model) {
		ReviewVO vo = reviewMapper.reviewOne(reviewCommand.getReviewNum());
		model.addAttribute("reviewCommand", vo);
		return"thymeleaf/review/reviewUpdate";
	}
	
	@PostMapping("reviewUpdate")
	public String reviewUpdate(ReviewCommand reviewCommand) {
		reviewUpdateService.execute(reviewCommand);
		return "redirect:/review/reviewList";
	}
	
	@GetMapping("reviewDelete")
	public String reviewDelete(@RequestParam(value="reviewNum")String reviewNum) {
		reviewMapper.reviewDelete(reviewNum);
		return "redirect:/review/reviewList";
	}
}
