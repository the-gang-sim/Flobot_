package flobot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
	@RequestMapping("adminPage")
	public String adminPage() {
		
		return "thymeleaf/admin/admin";
	}

}
