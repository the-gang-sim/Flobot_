package flobot.Service.Goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.GoodsMapper;
import flobot.domain.GoodsVO;

@Service
public class GoodsInfoService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Model model) {
		GoodsVO vo = goodsMapper.goodsInfo(goodsNum);
		model.addAttribute("goodsCommand", vo);
	}

}
