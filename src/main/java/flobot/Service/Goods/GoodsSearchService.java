package flobot.Service.Goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import flobot.Mapper.SearchMapper;
import flobot.domain.GoodsVO;
import flobot.domain.PageVO;

@Service
public class GoodsSearchService {
	@Autowired
	SearchMapper searchMapper;

	public void execute(String keyword, Model model, int page) {
		int limit = 2;
		int limitPage = 10; 
		int stratRow;
		int endRow;
		double count = searchMapper.goodsCount(keyword);
		int maxPage = (int)((double)count / limit + 1);
		int startPage =  (int)((double) page / limitPage + 0.95 -1 ) * limitPage + 1;  
		int endPage = startPage + limitPage - 1; 
		stratRow = (page - 1) *  limit + 1;
		endRow = stratRow + limit - 1;
		PageVO vo = new PageVO();
		vo.setEndRow(endRow);
		vo.setKeyword(keyword);
		vo.setStartRow(stratRow);
		
		List<GoodsVO> list = searchMapper.goodsSearch(vo);
		
		if(maxPage < endPage) endPage = maxPage;
		
		model.addAttribute("list", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("stratRow", stratRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);
	}
}
