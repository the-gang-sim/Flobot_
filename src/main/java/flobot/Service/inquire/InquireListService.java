package flobot.Service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.InquireMapper;
import flobot.domain.InquireVO;

@Service
public class InquireListService {
	@Autowired
	InquireMapper inquiremapper;
	public void execute(String goodsNum, Model model) {
		InquireVO vo = new InquireVO();
		vo.setGoodsNum(goodsNum);
		List<InquireVO> list = inquiremapper.inquireList(vo);
		model.addAttribute("list", list);
	}

}
