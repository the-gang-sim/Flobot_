package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.GoodsGoodsIpgoPrpVO;
import flobot.domain.GoodsVO;
import flobot.domain.PrpVO;
import flobot.domain.WishVO;

@Mapper
public interface GoodsMapper {

	public void goodsRegi(GoodsVO vo);

	public String goodsCreateNum();

	public GoodsVO goodsInfo(String goodsNum);

	public int goodsDelete(String goodsNum);
	
	public int marketDelete(String userNum);

	public List<GoodsGoodsIpgoPrpVO> goodsList(String userNum);

	public int goodsUpdate(GoodsVO vo);
	
	public GoodsVO goodsListselect(String goodsNum);
	
	public List<GoodsVO> goodsWishListselect(WishVO vo);

	public void visitAdd(String goodsNum);

	public List<GoodsGoodsIpgoPrpVO> goodsPrpList(String userNum);

	public void goodsPrpRegi(PrpVO prp);
}
