package flobot.Service.Goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.GoodsMapper;
import flobot.Mapper.MemberMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.GoodsGoodsIpgoPrpVO;
import flobot.domain.PrpVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		if(authInfo!=null) {
			if(authInfo.getGrade().equals("mar")) {
				List<GoodsGoodsIpgoPrpVO> list = goodsMapper.goodsList(authInfo.getUserNum());
				model.addAttribute("list", list);
			}else if(authInfo.getGrade().equals("mem")){
				List<PrpVO> chk = memberMapper.memberPrpChk(authInfo.getUserNum());
				if(chk.isEmpty()) {
					List<GoodsGoodsIpgoPrpVO> list = goodsMapper.goodsList(null);
					model.addAttribute("list", list);
				}else {
					List<GoodsGoodsIpgoPrpVO> list = goodsMapper.goodsPrpList(authInfo.getUserNum());
					model.addAttribute("list", list);
				}
			}else{
				List<GoodsGoodsIpgoPrpVO> list = goodsMapper.goodsList(null);
				model.addAttribute("list", list);
			}
		}else {
			List<GoodsGoodsIpgoPrpVO> list = goodsMapper.goodsList(null);
			model.addAttribute("list", list);
		}
	}
}
