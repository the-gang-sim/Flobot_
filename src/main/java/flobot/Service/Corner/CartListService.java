package flobot.Service.Corner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.CornerMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.CartVO;
import jakarta.servlet.http.HttpSession;

@Service
public class CartListService {
	@Autowired
	CornerMapper cornerMapper;

	public void execute(HttpSession session, Model model) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		List<CartVO> vo = cornerMapper.cartList(authInfo.getUserNum());
		model.addAttribute("list", vo);
		
	}

}
