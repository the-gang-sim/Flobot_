package flobot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flobot.Mapper.MarketMapper;
import flobot.domain.StatVO;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	MarketMapper marketMapper;
	
	@RequestMapping("adminPage")
	public String adminPage() {
		
		return "thymeleaf/admin/admin";
	}
	@RequestMapping("chart")
	public String chart(@RequestParam(value="qut")String statQut,Model model) {
		List<StatVO> list = marketMapper.adminStat(statQut);
		String i = "[";
		String j = "[";
		String k = "[";
		int num =1;
		for(StatVO vo : list) {
			i += "["+num+","+vo.getStatSales()+"],";
			j += "["+num+","+vo.getStatProfit()+"],";
			k += "["+num+","+vo.getStatTotprofit()+"],";
			num++;
		}
		i=i.substring(0,i.lastIndexOf(","));
		j=j.substring(0,j.lastIndexOf(","));
		k=k.substring(0,k.lastIndexOf(","));
		i +="]";
		j +="]";
		k +="]";
		model.addAttribute("sales", i);
		model.addAttribute("profit", j);
		model.addAttribute("totProfit", k);
		model.addAttribute("list",list);
		return "thymeleaf/admin/chart";
	}

}
