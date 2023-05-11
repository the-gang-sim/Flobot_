package flobot.Service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.ReviewMapper;

@Service
public class ReviewScoreService {
	@Autowired
	ReviewMapper reviewMapper;

	public void execute(String goodsNum, Model model) {
		int count = reviewMapper.goodsCount(goodsNum);
		if(count==0) {
			model.addAttribute("score", "등록된 리뷰가 없습니다.");
		}else {
			int score = reviewMapper.reviewScore(goodsNum);
			model.addAttribute("score", score);
		}
	}

}
