package flobot.Service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Command.ReviewCommand;
import flobot.Mapper.ReviewMapper;
import flobot.domain.ReviewVO;

@Service
public class ReviewRegiService {

	@Autowired
	ReviewMapper reviewMapper;

	public void execute(ReviewCommand reviewCommand) {
		String reviewNum = reviewMapper.selectNum();
		ReviewVO vo = new ReviewVO();
		System.out.println(reviewCommand.getGoodsNum()+reviewCommand.getPurchaseNum()+reviewCommand.getReviewContent()+reviewCommand.getReviewScore()+reviewNum);
		vo.setGoodsNum(reviewCommand.getGoodsNum());
		vo.setPurchaseNum(reviewCommand.getPurchaseNum());
		vo.setReviewContent(reviewCommand.getReviewContent());
		vo.setReviewNum(reviewNum);
		vo.setReviewScore(reviewCommand.getReviewScore());
		reviewMapper.reviewRegi(vo);
		
	}

}
