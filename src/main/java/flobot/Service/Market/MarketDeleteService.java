package flobot.Service.Market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.GoodsMapper;
import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import jakarta.servlet.http.HttpSession;

@Service
public class MarketDeleteService {
	@Autowired
	MarketMapper marketMapper;
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		
			goodsMapper.marketDelete(authInfo.getUserNum());
			marketMapper.marketDelete(authInfo.getUserNum());
			
			session.removeAttribute("pwCheck");
			session.removeAttribute("authInfo");
			session.invalidate();
		
		
	}
}
