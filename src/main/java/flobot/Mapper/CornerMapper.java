package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.CartVO;
import flobot.domain.WishVO;

@Mapper
public interface CornerMapper {
	public String wishCount(WishVO vo);
	public Integer wishAdd(WishVO vo);
	public Integer cartAdd(CartVO cartVO);
	public List<CartVO> cartList(String memberNum);
	public Integer cartDown(CartVO vo);

}
