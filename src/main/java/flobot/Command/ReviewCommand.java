package flobot.Command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReviewCommand {
	String purchaseNum;
	String goodsNum;
	String reviewNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewDate;
	String reviewContent;
	Integer reviewScore;
	
	String memberNum;
}