package flobot.Command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberCommand {
	// 멤버필드는 html에 있는 tag의 이름과 같아야 한다.
		String memberNum;
		@NotBlank(message = "아이디를 입력해주세요. ")
		@Size(min= 8, max = 12)
		String memberId;
		@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
				 message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
		String memberPw;
		@NotEmpty(message = "비밀번호확인 입력하여 주세요.")
		String memberPwCon;
		@NotBlank(message = "이름을 입력하여 주세요.")
		String memberName;
		@NotBlank(message = "주소를 입력하여 주세요.")
		String memberAddr;
		String memberAddr2;
		String memberPost;
		@NotBlank(message = "성별을 선택하여 주세요.")
		String memberGender;
		@NotBlank(message = "연락처을 입력하여 주세요.")
		String memberPhone;
		@Email(message = "형식에 맞지 않습니다.")
		@NotEmpty(message = "이메일을 입력하여 주세요.")
		String memberEmail;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "날짜를 입력하여 주세요.")
		Date memberBirth;	
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		Date memberRegiDate;
		String memberMarried;
	
	public boolean isMemberPwEqualsMemberPwCon() {
		return memberPw.equals(memberPwCon);
	}

}
