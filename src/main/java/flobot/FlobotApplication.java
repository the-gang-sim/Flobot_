package flobot;


import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import flobot.Command.LoginCommand;
import flobot.Service.CookiesService;
import flobot.Service.StatService;
import flobot.Service.Goods.GoodsListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class FlobotApplication {
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	CookiesService cookiesService;
	@Autowired
	StatService statService;
	
	@ModelAttribute
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	public static void main(String[] args) {
		SpringApplication.run(FlobotApplication.class, args);
		
		
	}
	@RequestMapping("/")
	public String index(Model model, HttpSession session, HttpServletRequest request) {
		goodsListService.execute(model, session);
		cookiesService.execute(request, model);
		statService.execute(model, session);
		return "thymeleaf/flobot";
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public PythonInterpreter intPre() {
		return new PythonInterpreter();
	}
}
