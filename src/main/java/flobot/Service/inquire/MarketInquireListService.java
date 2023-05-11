package flobot.Service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.InquireMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.InquireVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MarketInquireListService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO)session.getAttribute("authInfo");
		InquireVO vo = new InquireVO();
		vo.setMarketNum(authInfo.getUserNum());
		
		List<InquireVO> list = inquireMapper.MarketInquireList(vo);
		model.addAttribute("list", list);
	}

}
