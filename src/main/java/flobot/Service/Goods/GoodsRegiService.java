package flobot.Service.Goods;

import java.io.File;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import flobot.Command.GoodsCommand;
import flobot.Mapper.GoodsMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.GoodsVO;
import flobot.domain.PrpVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsRegiService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(GoodsCommand goodsCommand, Model model, HttpSession session, BindingResult result) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		GoodsVO vo = new GoodsVO();
		String goodsNum = goodsMapper.goodsCreateNum();
		vo.setGoodsNum(goodsNum);
		vo.setDeliveryCost(goodsCommand.getDeliveryCost());
		vo.setGoodsContent(goodsCommand.getGoodsContent());
		vo.setGoodsKind(goodsCommand.getGoodsKind());
		vo.setGoodsName(goodsCommand.getGoodsName());
		vo.setManufacturer(goodsCommand.getManufacturer());
		vo.setMarketNum(authInfo.getUserNum());
		
		// 파일 경로 설정
		String filedir = "/view/goods/image";
		String filepath = session.getServletContext().getRealPath(filedir);
		
		MultipartFile mf = goodsCommand.getGoodsMain(); // MultipartFile로 가져온 커맨드 가져오기
		String mainOrg = mf.getOriginalFilename(); // MultipartFile String 타입으로 파일 이름 가져오기
		String extension = mainOrg.substring(mainOrg.lastIndexOf(".")); // 파일 확장자 가져오기
		String randomName = UUID.randomUUID().toString().replace("-", ""); // 랜덤으로 이름 짓는데 파일이름에 -는 들어가면 안되서 없애기
		String htmlFile = randomName + extension;
				
		File file = new File(filepath + "/" + htmlFile); // 생성한 랜덤이름과 확장자로 파일 생성
		
		try {
			mf.transferTo(file); // 생성한 파일에 원본이미지 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setGoodsMain(htmlFile); // 생성한 파일이름 VO에 저장
		vo.setGoodsMainOrg(mainOrg); // 원본 파일이름 VO에 저장
		
		if(!goodsCommand.getGoodsImage()[0].getOriginalFilename().isEmpty()) {
			String orgTotal = ""; 
			String htmlTotal = "";
			for( MultipartFile mf1  :  goodsCommand.getGoodsImage()) { // 파일이 여러개있으면 반복으로 가져오기
				mainOrg = mf1.getOriginalFilename(); // MultipartFile String 타입으로 파일 이름 가져오기
				extension = mainOrg.substring(mainOrg.lastIndexOf(".")); // 파일 확장자 가져오기
				randomName = UUID.randomUUID().toString().replace("-", ""); // 랜덤으로 이름 짓는데 파일이름에 -는 들어가면 안되서 없애기
				htmlFile = randomName + extension;
				
				file = new File(filepath + "/" + htmlFile); // 생성한 랜덤이름과 확장자로 파일 생성
				try {
					mf1.transferTo(file); // 생성한 파일에 원본이미지 저장
				} catch (Exception e) {
					e.printStackTrace();
				} 
				orgTotal += mainOrg +"-";  //가져온 파일을 파일이름에 안쓰는 -를 구분자로 한개이름으로 저장
				htmlTotal += htmlFile +"-";
			}
			vo.setGoodsImage(htmlTotal); // 생성한 파일이름 VO에 저장
			vo.setGoodsImageOrg(orgTotal); // 원본 파일이름 VO에 저장
			
		}
		goodsMapper.goodsRegi(vo);
		PrpVO prp = new PrpVO();
		prp.setGoodsNum(goodsNum);
		prp.setBrilliance(Math.round(Math.random()*10));
		prp.setDeliCharge(Math.round(Math.random()));
		prp.setRootYn(Math.round(Math.random()));
		if(goodsCommand.getGoodsKind().equals("flower")) {
			prp.setFlower(1);
		}else if(goodsCommand.getGoodsKind().equals("seed")) {
			prp.setSeed(1);
		}else if(goodsCommand.getGoodsKind().equals("herb")) {
			prp.setHerb(1);
		}else if(goodsCommand.getGoodsKind().equals("tree")) {
			prp.setTree(1);
		}else {
			prp.setFleshy(1);
		}
		goodsMapper.goodsPrpRegi(prp);
		model.addAttribute("goodsCommand",vo);
	}

}
