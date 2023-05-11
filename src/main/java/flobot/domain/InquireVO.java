package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquireVO")
public class InquireVO {
	String inquireNum;
	String memberNum;
	String goodsNum;
	String inquireKind;
	String inquireSubject;
	String inquireContent;
	Date inquireRegiDate; // 문의 등록일
	String inquireAnswer; // 답변
	Date answerRegiDate; // 답변 등록일
	String marketNum;
	
	
	String memberId;
	String goodsName;
	//String marketName;

}
