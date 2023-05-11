package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.MarketCommand;
import flobot.Mapper.MarketMapper;
import flobot.domain.MarketVO;

@Service
public class MarketRegiService {
	@Autowired
	MarketMapper marketMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MarketCommand marketCommand, Model model) {
		MarketVO vo = new MarketVO();
		vo.setMarketAccount(marketCommand.getMarketAccount());
		vo.setMarketAddr(marketCommand.getMarketAddr());
		vo.setMarketAddr2(marketCommand.getMarketAddr2());
		vo.setMarketBank(marketCommand.getMarketBank());
		vo.setMarketEmail(marketCommand.getMarketEmail());
		vo.setMarketId(marketCommand.getMarketId());
		vo.setMarketKind(marketCommand.getMarketKind());
		vo.setMarketName(marketCommand.getMarketName());
		vo.setMarketNum(marketCommand.getMarketNum());
		vo.setMarketPhone(marketCommand.getMarketPhone());
		vo.setMarketPost(marketCommand.getMarketPost());
		vo.setMarketPw(passwordEncoder.encode(marketCommand.getMarketPw()));
		vo.setMarketRegiDate(marketCommand.getMarketRegiDate());
		int i = marketMapper.marketInsert(vo);
		if(i>0) {
			model.addAttribute("marketCommand", vo);
		}
	}
}
