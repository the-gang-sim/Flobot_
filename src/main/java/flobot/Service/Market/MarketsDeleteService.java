package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.MarketMapper;

@Service
public class MarketsDeleteService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(String marketDels []) {
		marketMapper.marketsDelete(marketDels);
	}

}
