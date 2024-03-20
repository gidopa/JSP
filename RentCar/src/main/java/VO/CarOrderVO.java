package VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOrderVO  {
	
	private int orderid;
	private int id;
	private int carno;
	private int carqty;
	private int carreserveday;
	private String carbegindate;
	private int carins;
	private int carwifi;
	private int carseat;
	private int carnavi;
	
	private String memberphone;
	private String memberpass;
	
	public CarOrderVO() {
	}

	public CarOrderVO(int orderid, int carno, int carqty, int carreserveday, String carbegindate, int carins,
			int carwifi, int carseat, int carnavi, String memberphone, String memberpass) {
		this.orderid = orderid;
		this.carno = carno;
		this.carqty = carqty;
		this.carreserveday = carreserveday;
		this.carbegindate = carbegindate;
		this.carins = carins;
		this.carwifi = carwifi;
		this.carseat = carseat;
		this.carnavi = carnavi;
		this.memberphone = memberphone;
		this.memberpass = memberpass;
	}
	
	
	
	
	
	
}
