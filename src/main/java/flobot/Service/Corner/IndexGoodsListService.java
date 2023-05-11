package flobot.Service.Corner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.GoodsMapper;
import flobot.domain.GoodsVO;

@Service
public class IndexGoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Model model) {
		//WishVO vo = new WishVO();
		//vo.setGoodsNum(goodsNum);
		goodsMapper.visitAdd(goodsNum);
		GoodsVO vo = goodsMapper.goodsListselect(goodsNum);
		//List<GoodsVO> list = goodsMapper.goodsListselect(goodsNum);
		model.addAttribute("goodsCommand", vo);
	}
}
