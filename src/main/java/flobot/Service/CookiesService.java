package flobot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.LoginCommand;
import flobot.Mapper.LoginMapper;
import flobot.domain.AuthInfoVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CookiesService {
	@Autowired
	LoginMapper loginMapper;
	public void execute(HttpServletRequest request, Model model) {
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("idStore")) {
					LoginCommand loginCommand = new LoginCommand();
					loginCommand.setUserId(cookie.getValue());
					loginCommand.setRememId(true);
					model.addAttribute("loginCommand", loginCommand);
				}
				if(cookie.getName().equals("rememLogin")) {
					AuthInfoVO authInfo = loginMapper.login(cookie.getValue());
					HttpSession session = request.getSession();
					session.setAttribute("authInfo", authInfo);
				}
			}
		}
	}
}
