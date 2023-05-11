package flobot.Controller;

import java.io.PrintWriter;

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

import flobot.Command.InquireCommand;
import flobot.Mapper.InquireMapper;
import flobot.Service.inquire.GoodsInquireDetailService;
import flobot.Service.inquire.InquireAnswerService;
import flobot.Service.inquire.InquireListService;
import flobot.Service.inquire.InquireUpdateService;
import flobot.Service.inquire.InquireWriteService;
import flobot.Service.inquire.MarketInquireDetailService;
import flobot.Service.inquire.MarketInquireListService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireListService inquireListService;
	@RequestMapping("inquireList")
	public String inquireList(
			@RequestParam(value="goodsNum") String goodsNum, Model model
			,@ModelAttribute(value="marketNum") String marketNum
			) {
		inquireListService.execute(goodsNum, model);
		model.addAttribute("goodsNum", goodsNum);
		model.addAttribute("newLineChar", "\n");
		System.out.println(marketNum + "******************************************************");
		return "thymeleaf/inquire/inquireList";
	}
	
	@GetMapping("inquireWrite")
	public String inquireWrite(
			@ModelAttribute(value="goodsNum") String goodsNum
			,@ModelAttribute(value="marketNum") String marketNum) {
		System.out.println(marketNum + "===================================================");
		return "thymeleaf/inquire/inquireWrite";
	}
	
	@Autowired
	InquireWriteService inquireWriteService;
	@PostMapping("inquireWrite")
	public void  inquireWrite(InquireCommand inquireCommand,
			HttpSession session, HttpServletResponse response) {
		inquireWriteService.execute(inquireCommand, session);
		
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String str = "<script>"
					+ "opener.parent.inquire();"
					+ "window.self.close()"	
					+ "</script>";
			out.print(str);
			out.close();
		}catch(Exception e) {}
	}
	
	@Autowired
	GoodsInquireDetailService goodsInquireDetailService;
	@GetMapping("inquireUpdate")
	public String inquireUpdate(
			@RequestParam(value="inquireNum") String inquireNum, Model model) {
		goodsInquireDetailService.execute(inquireNum,model);
		return "thymeleaf/inquire/inquireUpdate"; 
	}
	
	@Autowired
	InquireUpdateService inquireUpdateService;
	@PostMapping("inquireUpdate")
	public void inquireUpdate(InquireCommand inquireCommand , HttpServletResponse response) {
		inquireUpdateService.execute(inquireCommand);		
		try {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String str  = "<script>"
					+ "opener.parent.inquire();"
					+ "window.self.close();"
					+ "</script>";
		out.print(str);
		out.close();
		}catch(Exception e) {}
		
	}
	
	@Autowired
	InquireMapper inquireMapper;
	@RequestMapping("inquireDelete")
	public @ResponseBody String inquireDelete(
			@RequestParam(value="inquireNum") String inquireNum) {
		Integer i = inquireMapper.inquireDelete(inquireNum);
		return i.toString();
	}
	
	@Autowired
	MarketInquireListService marketInquireListService;
	@RequestMapping("MarketInquireList")
	public String inquireList(Model model, HttpSession session) {
		marketInquireListService.execute(model, session);
		return "thymeleaf/inquire/MarketInquireList";
	}
	
	@Autowired
	MarketInquireDetailService marketInquireDetailService;
	@RequestMapping("marketInquireDetail/{num}")
	public String marketInquireDetail(
			@PathVariable(value="num") String inquireNum, Model model
			) {
		marketInquireDetailService.execute(inquireNum,model);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/marketInquireDetail"; 
	}
	
	@Autowired
	InquireAnswerService inquireAnswerService;
	@PostMapping("answerWrite")
	public String answerWrite(InquireCommand inquireCommand, HttpSession  session) {
		inquireAnswerService.execute(inquireCommand, session);
		return "redirect:MarketInquireList";
	}
	

}
