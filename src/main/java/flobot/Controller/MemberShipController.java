package flobot.Controller;

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
import flobot.Command.MemberCommand;
import flobot.Service.Member.MemberDetailService;
import flobot.Service.MemberShip.EmailCheckService;
import flobot.Service.MemberShip.MemberJoinService;
import flobot.Service.MemberShip.MemberPwChkService;
import flobot.Service.MemberShip.MemberPwModyfyService;
import flobot.domain.AuthInfoVO;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("register")
public class MemberShipController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberPwChkService memberPwChkService;	
	@Autowired
	MemberPwModyfyService memberPwModyfyService;
	
	@RequestMapping("agree")
	public String agree() {
		return "thymeleaf/memberShip/memberShipAgree";
	}
	
	@RequestMapping("regist")
	public String regist(
			@RequestParam(value = "agree", defaultValue = "false" ) boolean agree
			, Model model) {
		if(agree == false) {
			return "thymeleaf/memberShip/memberShipAgree";
		}
		
		model.addAttribute("memberCommand", new MemberCommand());
		
		return "thymeleaf/memberShip/memberJoinForm";
	}
	
	@Autowired
	MemberJoinService memberJoinService;
	@RequestMapping("welcome")
	public String register( 
			@Validated MemberCommand memberCommand,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/memberShip/memberJoinForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			
			return "thymeleaf/memberShip/memberJoinForm";
		}
		memberJoinService.execute(memberCommand, model);
		return "thymeleaf/memberShip/welcome";
	}
	
	@GetMapping("memberConfirm")
	public String memberConfirm(@RequestParam(value="chk")String chk) {
		emailCheckService.execute(chk);
		return "redirect:/";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(HttpSession session, Model model) {
		AuthInfoVO authInfo  = (AuthInfoVO) session.getAttribute("authInfo");
		memberDetailService.execute(authInfo.getUserNum(), model);
		return "thymeleaf/memberShip/memberShipDetail";
	}
	
	@GetMapping("memberPwChk")
	public String memberPwChk(@ModelAttribute(value="chk")String chk,LoginCommand loginCommand) {
		return "thymeleaf/memberShip/memberPwChk";
	}
	
	@PostMapping("memberPwChk")
	public String memberPwChk(@RequestParam(value="chk")String chk,LoginCommand loginCommand,Model model, BindingResult result,HttpSession session) {
		int i = memberPwChkService.execute(loginCommand, result, session);
		AuthInfoVO authInfo  = (AuthInfoVO) session.getAttribute("authInfo");
		if(i>0) {
			if(chk.equals("up")) {
				memberDetailService.execute(authInfo.getUserNum(), model);
				return "thymeleaf/memberShip/memberShipUpdate";
			}else if(chk.equals("de")) {
				return "redirect:/member/memberDelete?memberNum="+authInfo.getUserNum();
			}else {
				return "thymeleaf/memberShip/memberPwModyfy";
			}
		}else {
			result.rejectValue(chk, chk);
			return "thymeleaf/memberShip/memberPwChk";
		}
	}
	@PostMapping("memberPwModyfy")
	public String memberPwModyfy(@Validated LoginCommand loginCommand, BindingResult result,HttpSession session) {
		if(!loginCommand.getNewPw().equals(loginCommand.getUserPwCon())) {
			result.rejectValue("userPwCon", "loginCommand.userPwCon", "비밀번호를 다시 확인하세요.");
			return "thymeleaf/memberShip/memberPwModyfy";
		}
		memberPwModyfyService.execute(loginCommand, result, session);
		return "redirect:/";
	}
}
