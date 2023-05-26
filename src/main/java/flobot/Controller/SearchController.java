package flobot.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import flobot.Command.LoginCommand;
import flobot.Service.Goods.GoodsSearchService;
import flobot.Service.Search.ImageService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("search")
public class SearchController {
	@Autowired
	GoodsSearchService goodsSearchService;
	@Autowired
	PythonInterpreter intPre;
	
	@GetMapping("goodsSearch")
	public String goodsSearch(@ModelAttribute(value="keyword")String keyword,@RequestParam(value="page" , required = false, defaultValue = "1" )int page,
		Model model) {
		goodsSearchService.execute(keyword, model, page);
		return "thymeleaf/search/searchList";
	}
	
	@PostMapping("goodsSearch2")
	public String goodsSearch2(@ModelAttribute(value="keyword")String keyword,@RequestParam(value="page" , required = false, defaultValue = "1" )int page,
		Model model) {
		goodsSearchService.execute(keyword, model, page);
		return "thymeleaf/search/searchList";
	}
	
	@GetMapping("/image")
	public String imgSearch(LoginCommand loginCommand) {

		return "thymeleaf/search/imgSearch";
	}
	
	@Autowired
	ImageService imageService;
	@PostMapping("/image")
	public String imgSearch(LoginCommand loginCommand,@RequestParam(value="searchImg")MultipartFile searchImg,HttpSession session, Model model) {
		String filedir = "C:/Flobot/image";
		MultipartFile mf = searchImg; // MultipartFile로 가져온 커맨드 가져오기
		String mainOrg = mf.getOriginalFilename(); // MultipartFile String 타입으로 파일 이름 가져오기
		File file = new File(filedir + "/" + mainOrg); // 생성한 랜덤이름과 확장자로 파일 생성
		try {
			mf.transferTo(file); // 생성한 파일에 원본이미지 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result="";
		try {
			Process process  = Runtime.getRuntime().exec("powershell -Command python " + "src/main/resources/templates/thymeleaf/python/searchImg.py");
			BufferedReader reader = new BufferedReader(
			        new InputStreamReader(process.getInputStream()));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
			    sb.append(line);
			    sb.append("\n");
			}
			result = sb.toString().split("step")[1].replace("\n", "");
		}catch(Exception e) {
			e.printStackTrace();
		}
		file.delete();
		model.addAttribute("flower", result);
		String command = "schtasks /run /tn \"BrityRPA_P_TestFolbot\""; // 작업스케줄러 실행
	    Process process;
		try {
			process = Runtime.getRuntime().exec(command);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageService.execute(model, result);
		return "thymeleaf/search/flower";
	}
}
