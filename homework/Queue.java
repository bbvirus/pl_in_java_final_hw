package homework;

import java.util.ArrayList;
import java.util.List;

// Home, Station, Employee, Train, LeastQueueWaiting Ŭ�������� ����ϴ� Queue�� �����ξ���.
public class Queue {
	private ArrayList<Customer> customerQueue = new ArrayList<Customer>();
	
	// ť�� �ֱ�
	public void enQueue(Customer customer){
		customerQueue.add(customer);
	}
	
	// ť���� ����
	public Customer deQueue(){
		return customerQueue.remove(0);
	}
	
	// Ư�� �ε����� ���� �����ϱ�
	public Customer getCustomer(int index){
		return customerQueue.get(index);
	}
	
	// ť�� ����ִ��� Ȯ���ϱ�
	public boolean isEmpty(){
		if(customerQueue.size()==0){
			return true;
		}
		else return false;
	}
	
	// ť�� ������ �˷��ֱ�
	public int getNumberOfCustomer(){
		return this.customerQueue.size();
	}
	
	//test��
	List<Customer> getList() {
		return customerQueue;
	}
}
