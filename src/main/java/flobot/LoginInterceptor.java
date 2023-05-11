package flobot;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("authInfo") == null) {
			response.sendRedirect("../");
		} else {
			return true; // 가던길 가셈
		} 
		return false;
	}

}
