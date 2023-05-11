package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.MarketVO;

@Service
public class MarketDetailService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(String marketNum, Model model) {
		
		MarketVO vo = marketMapper.marketOneDetail(marketNum);
		model.addAttribute("marketCommand", vo);
	}

}
