package flobot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kenai.jffi.Array;

import flobot.Mapper.MarketMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.StatVO;
import jakarta.servlet.http.HttpSession;

@Service
public class StatService {
	@Autowired
	MarketMapper marketMapper;
	public void execute(Model model, HttpSession session) {
		
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		
		List<StatVO> list = new ArrayList<StatVO>();
		Object [][] i ;
		if(authInfo != null) {
			list = marketMapper.statList(authInfo.getUserNum());
			for(StatVO vo : list) {
				System.out.println(vo);
			}
		}
		
		model.addAttribute("stat", list);
		
		
		
		
	}

}
