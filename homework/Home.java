package homework;


public class Home {
	
	// 집이다. 집이 최고다. 사실 집은 Customer마다 따로 있어야 하지만 Station도 하나밖에 없어서 Home도 싱글톤 패턴으로 만들었다.
	Queue customersAtHome = new Queue();
	
	private static Home home = new Home();
	
	private Home(){}
	
	public static Home getInstance(){
		return home;
	}
	
	// Customer를 집에 넣어주는 메서드.
	public void setCustomers(Customer customer){
		customersAtHome.enQueue(customer);
	}
	
}