package flobot.Service.Search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.SearchMapper;
import flobot.domain.FlowerVO;

@Service
public class ImageService {
	@Autowired
	SearchMapper searchMapper;
	
	public void execute(Model model, String result) {
		//System.out.println("["+result+"]");
		if(result.equals("roses")) {
			result = "장미";
		}
		else if(result.equals("daisy")) {
			result = "국화";
		}
		else if(result.equals("dandelion")) {
			result = "민들래";
		}
		else if(result.equals("sunflowers")) {
			result = "해바라기";
		}
		else if(result.equals("tulips")) {
			result = "튤립";
		}
		
		//System.out.println(result + "==============================================================");
		List<FlowerVO> list = searchMapper.imageSearch(result);
		
		model.addAttribute("list", list);
		
	}

}
