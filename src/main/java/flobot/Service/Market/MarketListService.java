package flobot.Service.Market;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.MarketVO;

@Service
public class MarketListService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(String marketWord, Model model) {
		List<MarketVO> list = marketMapper.marketAllSelect(marketWord);
		model.addAttribute("list", list);
		model.addAttribute("marketWord", marketWord);
		
	}

}




