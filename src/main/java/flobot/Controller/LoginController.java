package flobot.Controller;


import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import flobot.Command.LoginCommand;
import flobot.Mapper.LoginMapper;
import flobot.Mapper.MemberMapper;
import flobot.Service.Login.LoginService;
import flobot.Service.Login.ResetPwService;
import flobot.domain.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	ResetPwService resetPwService;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	LoginMapper loginMapper;
	
	@GetMapping("login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletResponse response) {
		loginService.execute(loginCommand, session, result, response);
		if(result.hasErrors()) {
			return "thymeleaf/flobot";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(LoginCommand loginCommand, HttpSession session, HttpServletResponse response) {
		Cookie cookie = new Cookie("rememLogin", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("resetPw")
	public String resetPw(LoginCommand loginCommand) {
		return "thymeleaf/login/resetPw";
	}
	@PostMapping("resetPw")
	public String resetPw(@Validated LoginCommand loginCommand, BindingResult result) {
		MemberVO vo= memberMapper.memberOneSelect(loginCommand.getUserId());
		if(vo!=null) {
			if(vo.getMemberEmail().equals(loginCommand.getUesrEmail())) {
				resetPwService.execute(loginCommand, result);
				return "thymeleaf/login/emailChkPlz";
			}else {
				result.rejectValue("userEmail", "loginCommand.userEmail", "일치하는 이메일이 없습니다.");
				return "thymeleaf/login/resetPw";
			}
		}else {
			result.rejectValue("userId", "loginCommand.userId", "일치하는 아이디가 없습니다.");
			return "thymeleaf/login/resetPw";
		}
	}
	@GetMapping("findId")
	public String findId(LoginCommand loginCommand) {
		return "thymeleaf/login/findId";
	}
	@PostMapping("findId")
	public String findId(@Validated LoginCommand loginCommand, BindingResult result,Model model) {
		MemberVO vo = loginMapper.findId(loginCommand.getUserName());
		if(vo == null) {
			result.rejectValue("userId", "loginCommand.userId", "없는 이름입니다. 다시확인해주세요");
			return "thymeleaf/login/findId";
		}else if(vo.getMemberPhone().equals(loginCommand.getUserPhone())) {
			model.addAttribute("userId",vo.getMemberId());
			return "thymeleaf/login/findIdResult";
		}else {
			result.rejectValue("userId", "loginCommand.userId", "연락처가 틀렸습니다. 다시확인해주세요");
			return "thymeleaf/login/findId";
		}
	}
	
	@RequestMapping(value="item.login",method= RequestMethod.GET)
	public String item(LoginCommand loginCommand) {
		return "thymeleaf/login";
	}
	@RequestMapping(value="item.login",method= RequestMethod.POST)
	public String item(LoginCommand loginCommand, BindingResult result , HttpSession session,
			HttpServletResponse response) {
		loginService.execute(loginCommand, session, result, response);
		if(result.hasErrors()) {
			return "thymeleaf/login";
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
	
}

