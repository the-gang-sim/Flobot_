package flobot.Service.Goods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import flobot.Command.FileDelCommand;
import flobot.Command.GoodsCommand;
import flobot.Mapper.GoodsMapper;
import flobot.domain.AuthInfoVO;
import flobot.domain.GoodsVO;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, Model model, HttpSession session) {
		AuthInfoVO authInfo = (AuthInfoVO) session.getAttribute("authInfo");
		GoodsVO vo = new GoodsVO();
		vo.setGoodsNum(goodsCommand.getGoodsNum());
		vo.setDeliveryCost(goodsCommand.getDeliveryCost());
		vo.setGoodsContent(goodsCommand.getGoodsContent());
		vo.setGoodsKind(goodsCommand.getGoodsKind());
		vo.setGoodsName(goodsCommand.getGoodsName());
		vo.setManufacturer(goodsCommand.getManufacturer());
		vo.setMarketNum(authInfo.getUserNum());
		
		
		
		GoodsVO vo1 = goodsMapper.goodsInfo(vo.getGoodsNum());
		vo.setGoodsImage(vo1.getGoodsImage());
		vo.setGoodsImageOrg(vo1.getGoodsImageOrg());
		vo.setGoodsMain(vo1.getGoodsMain());
		vo.setGoodsMainOrg(vo1.getGoodsMainOrg());
		// 파일 경로 설정
		String filedir = "/view/goods/image";
		String filepath = session.getServletContext().getRealPath(filedir);
		
		List<FileDelCommand> list = (List<FileDelCommand>)session.getAttribute("fileList");
		if(!goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
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
		}else {
			if(list != null) {
				for(FileDelCommand command : list) {
					if(vo1.getGoodsMain().equals(command.getHtmlName())) {
						model.addAttribute("goodsCommand", vo1);
						session.removeAttribute("fileList");
						return;
					}
				}
			}
		}
		
		List<String> goodsImages = new ArrayList<String>();
		List<String> goodsOrgImages= new ArrayList<String>();
		if(vo1.getGoodsImage() != null) {
			String [] images = vo1.getGoodsImage().split("-");
			String [] orgImages = vo1.getGoodsImageOrg().split("-");
			for(String img : images) {
				goodsImages.add(img);
			}
			for(String img : orgImages) {
				goodsOrgImages.add(img);
			}

			if(list != null) {
				for(FileDelCommand command : list) {
					for(String str : goodsImages) {
						if(str.equals(command.getHtmlName())){
							goodsImages.remove(command.getHtmlName());
							goodsOrgImages.remove(command.getHtmlName());
							break;
						}
					}
				}
			}
		}
		//// 이미지 파일 추가
		String originalTotal = ""; 
		String storeTotal = "";
		if(!goodsCommand.getGoodsImage()[0].getOriginalFilename().isEmpty()) {
			for( MultipartFile mf1  :  goodsCommand.getGoodsImage()) {
				String originalFile = mf1.getOriginalFilename(); 
				String extension = originalFile.substring(originalFile.lastIndexOf(".")) ; 
				String storeName = UUID.randomUUID().toString().replace("-", "");
				String storeFileName = storeName +  extension;
				
				File file = new File(filepath + "/" + storeFileName);
				try {
					mf1.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile +"-"; 
				storeTotal += storeFileName +"-";
			}
		}
		if(vo1.getGoodsImage() != null) {
			for(String img : goodsImages) {
				storeTotal += img + "-";
			}
			for(String img : goodsOrgImages) {
				originalTotal += img + "-";
			}
		}
		vo.setGoodsImageOrg(originalTotal);
		vo.setGoodsImage(storeTotal);
		int i = goodsMapper.goodsUpdate(vo);
		/// session에 있는 파일을 삭제
		if(i > 0) {
			if(list != null) {
				for(FileDelCommand command : list) {
					File file = new File(filepath +"/"+ command.getHtmlName());
					if(file.exists()) file.delete();
				}
			}
		}
		session.removeAttribute("fileList");
	}
	
}
