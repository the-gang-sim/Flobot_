package flobot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Command.MemberCommand;
import flobot.Service.Member.MemberAutoNumService;
import flobot.Service.Member.MemberDeleteService;
import flobot.Service.Member.MemberDetailService;
import flobot.Service.Member.MemberInsertService;
import flobot.Service.Member.MemberListService;
import flobot.Service.Member.MemberUpdateService;
import flobot.Service.Member.MembersDeleteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberListService memberListService;
	@RequestMapping("memberList")
	public String memberList(
			@RequestParam(value="memberWord" , required = false) String memberWord
			,Model model) {
		memberListService.execute(memberWord, model);
		return "thymeleaf/member/memberList";
	}
	
	@Autowired
	MemberAutoNumService memberAutoNumService;
	@RequestMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand,Model model) {
		memberAutoNumService.execute(memberCommand, model);
		return "thymeleaf/member/memberForm";
	}
	
	@Autowired
	MemberInsertService memberInsertService;
	@RequestMapping(value = "memberWrite", method = RequestMethod.POST)
	public String write(@Validated MemberCommand memberCommand,
			BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()){
			System.out.println("비밀번호 확인이 다릅니다.");
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/member/memberForm"; 
		}
		memberInsertService.execute(memberCommand);		
		
		return "redirect:memberList";
	}
	
	@Autowired
	MemberDetailService memberDetailService;
	@RequestMapping("memberDetail")
	public String memberDetail(
			@RequestParam(value="memberNum")String memberNum,
			Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
	}
	
	@RequestMapping(value="memberModify" , method = RequestMethod.GET)
	public String memberModify(
			@RequestParam(value="memberNum")String memberNum,
			Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
	}
	
	@Autowired
	MemberUpdateService memberUpdateService;
	@RequestMapping(value="memberModify" , method = RequestMethod.POST)
	public String memberModify(
			@Validated MemberCommand memberCommand,
			BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberUpdate";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:/";
	}
	
	@Autowired
	MemberDeleteService memberDeleteService;
	@RequestMapping("memberDelete")
	public String memberDelete(
			@RequestParam(value="memberNum") String memberNum, HttpSession session ) {
		memberDeleteService.execute(memberNum);

		session.invalidate();
		return "redirect:/";
	}
	
	@Autowired
	MembersDeleteService membersDeleteService;
	@RequestMapping("membersDelete")
	public String membersDelete(
			@RequestParam(value="memDels") String memDels []) {
		membersDeleteService.execute(memDels);
		return "redirect:memberList";
	}

}
