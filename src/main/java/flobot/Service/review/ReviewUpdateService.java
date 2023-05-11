package flobot.Service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Command.ReviewCommand;
import flobot.Mapper.ReviewMapper;
import flobot.domain.ReviewVO;

@Service
public class ReviewUpdateService {
	@Autowired
	ReviewMapper reviewMapper;

	public void execute(ReviewCommand reviewCommand) {

		ReviewVO vo = new ReviewVO();
		vo.setReviewContent(reviewCommand.getReviewContent());
		vo.setReviewNum(reviewCommand.getReviewNum());
		vo.setReviewScore(reviewCommand.getReviewScore());
		reviewMapper.reviewUpdate(vo);
		
	}

}
