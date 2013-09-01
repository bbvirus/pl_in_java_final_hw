package homework;


public class Home {
	
	// ���̴�. ���� �ְ��. ��� ���� Customer���� ���� �־�� ������ Station�� �ϳ��ۿ� ��� Home�� �̱��� �������� �������.
	Queue customersAtHome = new Queue();
	
	private static Home home = new Home();
	
	private Home(){}
	
	public static Home getInstance(){
		return home;
	}
	
	// Customer�� ���� �־��ִ� �޼���.
	public void setCustomers(Customer customer){
		customersAtHome.enQueue(customer);
	}
	
}