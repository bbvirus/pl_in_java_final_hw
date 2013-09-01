package homework;

import java.util.ArrayList;
import java.util.List;

// Home, Station, Employee, Train, LeastQueueWaiting 클래스에서 사용하는 Queue를 만들어두었다.
public class Queue {
	private ArrayList<Customer> customerQueue = new ArrayList<Customer>();
	
	// 큐에 넣기
	public void enQueue(Customer customer){
		customerQueue.add(customer);
	}
	
	// 큐에서 빼기
	public Customer deQueue(){
		return customerQueue.remove(0);
	}
	
	// 특정 인덱스의 값에 접근하기
	public Customer getCustomer(int index){
		return customerQueue.get(index);
	}
	
	// 큐가 비어있는지 확인하기
	public boolean isEmpty(){
		if(customerQueue.size()==0){
			return true;
		}
		else return false;
	}
	
	// 큐의 사이즈 알려주기
	public int getNumberOfCustomer(){
		return this.customerQueue.size();
	}
	
	//test용
	List<Customer> getList() {
		return customerQueue;
	}
}
