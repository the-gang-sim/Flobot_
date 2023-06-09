package flobot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.PurchaseVO;
import flobot.domain.StatVO;
import jakarta.servlet.http.HttpSession;

@Service
public class StatService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(Model model, HttpSession session) {
		
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		List<StatVO> list = new ArrayList<StatVO>();
		List<PurchaseVO> list2 = new ArrayList<PurchaseVO>();
		String i = "[";
		String j = "[";
		String k = "[";
		String l = "[";
		if(authInfo != null) {
			list = marketMapper.statList(authInfo.getUserNum());
			list2 = marketMapper.chartList(authInfo.getUserNum());
			if(authInfo.getGrade().equals("mar")) {
				for(StatVO vo : list) {
					i += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatSales()+"],";
					j += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatProfit()+"],";
					k += "["+vo.getStatYear().substring(2)+"."+vo.getStatQut()+","+vo.getStatTotprofit()+"],";
				}
				for(PurchaseVO vo : list2 ) {
					l += "["+vo.getGoodsNum().substring(9)+","+(vo.getPurchaseQty()*vo.getPurchasePrice())+"],";
				}
				i=i.substring(0,i.lastIndexOf(","));
				j=j.substring(0,j.lastIndexOf(","));
				k=k.substring(0,k.lastIndexOf(","));
				l=l.substring(0,l.lastIndexOf(","));
			}
		}
		i +="]";
		j +="]";
		k +="]";
		l +="]";
		model.addAttribute("statSales", i);
		model.addAttribute("statProfit", j);
		model.addAttribute("statTotPorfit", k);
		model.addAttribute("marketSum", l);
	}

}
