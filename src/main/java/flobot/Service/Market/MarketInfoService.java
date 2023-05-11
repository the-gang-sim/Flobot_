package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.MarketVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MarketInfoService {
	@Autowired
	MarketMapper marketMapper;

	public void execute(Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		MarketVO vo = marketMapper.marketSelectOne(authInfo.getUserNum());
		model.addAttribute("marketCommand", vo);
	}
	
}
