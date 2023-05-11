package flobot.Service.Goods;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.GoodsMapper;
import flobot.domain.GoodsVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(String goodsNum, HttpSession session) {
		GoodsVO vo = goodsMapper.goodsInfo(goodsNum);
		int i = goodsMapper.goodsDelete(goodsNum);
		if (i > 0) {
			String fileDir = "/view/goods/image";
			String filePath = session.getServletContext().getRealPath(fileDir);
			String goodsMain = vo.getGoodsMain();
			File file = new File(filePath + "/" + goodsMain);
			if (file.exists()) file.delete();

			if (vo.getGoodsImage() != null) {
				String[] fileNames = vo.getGoodsImage().split("-");
				for (String fileName : fileNames) {
					file = new File(filePath + "/" + fileName);
					if (file.exists())
						file.delete();
				}
			}
		}
	}

}
