package sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import base.action.BaseAction;
import util.JsonData;

public class VuexAction extends BaseAction {

	private static final long serialVersionUID = 1930164436104742587L;
	
	private String resturantName;//餐馆名
	
	public VuexAction() {
		super();
	}

	public String getResturantName() {
		return resturantName;
	}

	public void setResturantName(String resturantName) {
		this.resturantName = resturantName;
	}
	
	public String edit() {
		java.util.Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss.SSS");
		String s = sdf.format(d);
		
		try {
			System.out.println("模拟异步情况，休眠六秒，不超过10秒，axios超过时间设置的是10秒");
			Thread.sleep(6000);
			System.out.println("睡醒了，继续....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonData jsonData = new JsonData();
		jsonData.setResult(this.resturantName + "-" + s);
		this.writeJson(jsonData);
		
		return null;
	}
	
	
	
	
}
