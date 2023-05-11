package flobot.Service.Goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import flobot.Command.FileDelCommand;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsFileDelService {
	public String execute(FileDelCommand fileDelCommand, HttpSession session) {
		String num = "0";
		Boolean newFile = true;
		List<FileDelCommand> list = (List<FileDelCommand>) session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileDelCommand>();
		}
		for(int i = 0 ; i < list.size() ; i++) {
			if(list.get(i).getHtmlName().equals(fileDelCommand.getHtmlName())) {
				list.remove(i);
				newFile = false;
				num = "0";
			}
		}
		if(newFile) {
			list.add(fileDelCommand);
			num = "1";
		}
		session.setAttribute("fileList", list);
		System.out.println(list);
		return num;
		
	}

}
