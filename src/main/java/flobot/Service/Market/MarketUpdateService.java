package flobot.Service.Market;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.MarketCommand;
import flobot.Mapper.MarketMapper;
import flobot.domain.MarketVO;

@Service
public class MarketUpdateService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(MarketCommand marketCommand, Model model) {
		MarketVO vo = new MarketVO();
		vo.setMarketAccount(marketCommand.getMarketAccount());
		vo.setMarketAddr(marketCommand.getMarketAddr());
		vo.setMarketAddr2(marketCommand.getMarketAddr2());
		vo.setMarketBank(marketCommand.getMarketBank());
		vo.setMarketEmail(marketCommand.getMarketEmail());
		vo.setMarketName(marketCommand.getMarketName());
		vo.setMarketNum(marketCommand.getMarketNum());
		vo.setMarketPhone(marketCommand.getMarketPhone());
		vo.setMarketPost(marketCommand.getMarketPost());
		int i = marketMapper.marketUpdate(vo);
		if(i>0) {
			vo = marketMapper.marketSelectOne(vo.getMarketNum());
			model.addAttribute("marketCommand", vo);
		}
	}

}
