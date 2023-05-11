package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.GoodsVO;
import flobot.domain.WishVO;

@Mapper
public interface GoodsMapper {

	public void goodsRegi(GoodsVO vo);

	public String goodsCreateNum();

	public GoodsVO goodsInfo(String goodsNum);

	public int goodsDelete(String goodsNum);
	
	public int marketDelete(String userNum);

	public List<GoodsVO> goodsList(String userNum);

	public int goodsUpdate(GoodsVO vo);
	
	public GoodsVO goodsListselect(String goodsNum);
	
	public List<GoodsVO> goodsWishListselect(WishVO vo);

	public void visitAdd(String goodsNum);
}
