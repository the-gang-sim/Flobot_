package flobot.Service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.InquireMapper;
import flobot.domain.InquireVO;

@Service
public class MarketInquireDetailService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String inquireNum, Model model) {
		InquireVO vo = new  InquireVO();
		vo.setInquireNum(inquireNum);
		List<InquireVO> list = inquireMapper.MarketInquireList(vo);
		
		System.out.println(list + "=================================================================");
		System.out.println(vo + "=================================================================");
		model.addAttribute("list", list);		
	}

}
