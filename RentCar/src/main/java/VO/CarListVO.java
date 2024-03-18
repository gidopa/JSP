package VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 조회한 차량 한대의 정보를 저장할 용도 또는 차량 한대의 정보를 DB에 추가할 용도
public class CarListVO {
	private int carNo;
	private String carName;
	private String carCompany;
	private int carPrice;
	private int carUsePeople;
	private String carInfo;
	private String carImg;
	private String carCategory;
	
	public CarListVO() {}

	public CarListVO(int carNo, String carName, String carCompany, int carPrice, int carUsePeople, String carInfo,
			String carImg, String carCategory) {
		this.carNo = carNo;
		this.carName = carName;
		this.carCompany = carCompany;
		this.carPrice = carPrice;
		this.carUsePeople = carUsePeople;
		this.carInfo = carInfo;
		this.carImg = carImg;
		this.carCategory = carCategory;
	}
	
}
