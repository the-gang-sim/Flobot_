package flobot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.StatVO;
import jakarta.servlet.http.HttpSession;

@Service
public class StatService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(Model model, HttpSession session) {
		
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		List<StatVO> list = new ArrayList<StatVO>();
		String i = "[";
		String j = "[";
		String k = "[";
		if(authInfo != null) {
			list = marketMapper.statList(authInfo.getUserNum());
			for(StatVO vo : list) {
				i += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatSales()+"],";
				j += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatProfit()+"],";
				k += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatTotprofit()+"],";
			}
			i=i.substring(0,i.lastIndexOf(","));
			j=j.substring(0,j.lastIndexOf(","));
			k=k.substring(0,k.lastIndexOf(","));
		}
		i +="]";
		j +="]";
		k +="]";
		model.addAttribute("statSales", i);
		model.addAttribute("statProfit", j);
		model.addAttribute("statTotPorfit", k);
		
	}

}
