package flobot.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Command.LoginCommand;
import flobot.Command.MarketCommand;
import flobot.Service.Market.MarketDeleteService;
import flobot.Service.Market.MarketDetailService;
import flobot.Service.Market.MarketInfoService;
import flobot.Service.Market.MarketListService;
import flobot.Service.Market.MarketRegiService;
import flobot.Service.Market.MarketStatService;
import flobot.Service.Market.MarketUpdateService;
import flobot.Service.Market.MarketsDeleteService;
import flobot.Service.Market.PwCheckService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MarketController {
	@Autowired
	MarketRegiService marketRegiService;
	@Autowired
	MarketInfoService marketInfoService;
	@Autowired
	MarketUpdateService marketUpdateService;
	@Autowired
	MarketDeleteService marketDeleteService;
	@Autowired
	PwCheckService pwCheckService;
	@Autowired
	MarketStatService marketStatService;
	
	@GetMapping("/join/marketInsert")
	public String marketAgree(@ModelAttribute(value="Agree")String Agree) {
		
		return "thymeleaf/market/marketAgree";
	}
	
	@GetMapping("/join/marketRegi")
	public String marketInsert(@ModelAttribute(value="Agree")String Agree, MarketCommand marketCommand) {
		if(Agree.equals("yes")) {
			return "thymeleaf/market/marketRegi";
		}
		return "thymeleaf/market/marketAgree";
	}
	
	@PostMapping("/join/marketRegi")
	public String marketInsert(@Validated MarketCommand marketCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "thymeleaf/market/marketRegi";
		}
		if(!marketCommand.getMarketPw().equals(marketCommand.getMarketPwCon())){
			result.rejectValue("marketPwCon", "memberCommand.memberPwCon", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/market/marketRegi";
		}
		marketRegiService.execute(marketCommand, model);
		return "thymeleaf/market/marketInfo";
	}
	
	@GetMapping("/market/marketInfo")
	public String marketInfo(Model model, HttpSession session) {
		marketInfoService.execute(model, session);
		return "thymeleaf/market/marketInfo";
	}
	
	@GetMapping("/market/marketUpdate")
	public String marketUpdate(MarketCommand marketCommand, Model model, HttpSession session) {
		session.removeAttribute("pwCheck");
		marketInfoService.execute(model, session);
		return "thymeleaf/market/marketUpdate";
	}
	
	@PostMapping("/market/marketUpdate")
	public String marketUpdate(@Validated MarketCommand marketCommand, BindingResult result,Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			System.out.println("11111111111111111111111111111111111111111111111111111111111111111111S");
			return "thymeleaf/market/marketUpdate";
		}
		marketUpdateService.execute(marketCommand, model);
		return "thymeleaf/market/marketInfo";
	}
	
	@GetMapping("/market/marketDelete")
	public String marketDelete(HttpSession session) {
		marketDeleteService.execute(session);
		return "redirect:/";
	}
	
	@Autowired
	MarketsDeleteService marketsDeleteService;
	@RequestMapping("/market/marketsDelete")
	public String marketsDelete(
			@RequestParam(value="marketDels") String marketDels []) {
		marketsDeleteService.execute(marketDels);
		return "redirect:marketList";
	}
	
	@GetMapping("/market/pwCheck")
	public String pwCheck(@ModelAttribute(value="upde")String upde,LoginCommand loginCommand) {
		return "thymeleaf/market/pwCheck";
	}
	@PostMapping("/market/pwCheck")
	public String pwCheck(@ModelAttribute(value="upde")String upde, LoginCommand loginCommand, BindingResult result, HttpServletResponse response, HttpSession session) {
		if(!loginCommand.getUserPw().equals(loginCommand.getUserPwCon())) {
			result.rejectValue("userPwCon","loginCommand.userPwCon", "입력한 비밀번호를 다시 확인해주세요.");
			return "thymeleaf/market/pwCheck";
		}
		pwCheckService.execute(upde ,loginCommand, result, response, session);
		if(result.hasErrors()) {
			return "thymeleaf/market/pwCheck";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "<script language='javascript'>"
				   + " opener.location.reload();"
				   + " window.self.close();"
				   + " </script>"; 
		out.print(str);
		out.close();
		return null;
	}
	
	@Autowired
	MarketListService marketListService;
	@RequestMapping("/market/marketList")
	public String marketList(
			@RequestParam(value="marketWord" , required = false) String marketWord
			,Model model) {
		marketListService.execute(marketWord, model);
		return "thymeleaf/market/marketList";
	}
	
	@Autowired
	MarketDetailService marketDetailService;
	@RequestMapping("/market/marketDetail")
	public String marketDetail(
			@RequestParam(value="marketNum")String marketNum,
			Model model) {
		marketDetailService.execute(marketNum, model);
		return "thymeleaf/market/marketDetail";
	}
	
	@RequestMapping("/market/stat")
	public String marketStat(Model model, HttpSession session) {
		marketStatService.execute(model, session);
//		PRP 테이블 추가(상품추천가중치)
//		  - 상품번호, 회원번호, 화려함, 무배여부, 뿌리여부
//
//		화려함 선호도 10*0.4
//		무료배송 선호도 1*3
//		뿌리 선호도 1*3
//		구매종류 선호도 1번구매 당 1

		return "thymeleaf/market/marketStat";
	}
}
