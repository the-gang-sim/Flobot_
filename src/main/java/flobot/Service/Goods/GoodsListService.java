package flobot.Service.Goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.GoodsMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.GoodsVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		if(authInfo!=null) {
			if(authInfo.getGrade().equals("mar")) {
				List<GoodsVO> list = goodsMapper.goodsList(authInfo.getUserNum());
				model.addAttribute("list", list);
			}else {
				List<GoodsVO> list = goodsMapper.goodsList(null);
				model.addAttribute("list", list);
			}
		}else {
			List<GoodsVO> list = goodsMapper.goodsList(null);
			model.addAttribute("list", list);
		}
		
	}
	
}
