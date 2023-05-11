package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.ReviewVO;

@Mapper
public interface ReviewMapper {

	public String selectNum();

	public void reviewRegi(ReviewVO vo);

	public List<ReviewVO> reviewList(String memberNum);

	public ReviewVO reviewOne(String reviewNum);

	public void reviewUpdate(ReviewVO vo);

	public void reviewDelete(String reviewNum);

	public List<ReviewVO> reviewList(ReviewVO vo);

	public int goodsCount(String goodsNum);

	public int reviewScore(String goodsNum);

}
