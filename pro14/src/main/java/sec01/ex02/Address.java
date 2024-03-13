package sec01.ex02;

import lombok.Getter;
import lombok.Setter;

// 회원의 거주 도시와 우편번호를 저장

@Getter
@Setter
public class Address {
	private String city;
	private String zipcode;
	
	public Address() {
	}
	
}
