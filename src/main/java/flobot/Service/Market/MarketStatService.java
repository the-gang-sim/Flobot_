package flobot.Service.Market;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.StatVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MarketStatService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		List<StatVO> list =marketMapper.statList(authInfo.getUserNum());
		model.addAttribute("list",list);
	}

}
