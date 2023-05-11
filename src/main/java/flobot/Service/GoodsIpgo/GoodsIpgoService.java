package flobot.Service.GoodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Command.GoodsIpgoCommand;
import flobot.Mapper.GoodsIpgoMapper;
import flobot.domain.GoodsIpgoVO;


@Service
public class GoodsIpgoService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	public void execute(GoodsIpgoCommand goodsIpgoCommand, Model model) {
		String goodsIpgoNum=goodsIpgoMapper.goodsIpgoNumCreate();
		GoodsIpgoVO vo = new GoodsIpgoVO();
		vo.setGoodsIpgoNum(goodsIpgoNum);
		vo.setGoodsNum(goodsIpgoCommand.getGoodsNum());
		vo.setGoodsPrice(goodsIpgoCommand.getGoodsPrice());
		vo.setIpgoQty(goodsIpgoCommand.getIpgoQty());
		vo.setMadeDate(goodsIpgoCommand.getMadeDate());
		goodsIpgoMapper.goodsIpgo(vo);
		model.addAttribute("goodsIpgoCommand",vo);
	}

}
