package homework;

public class Employee {
	
	// ������ �տ� ���ִ� ���� �ǹ��ϴ� Queue
	Queue customerQueue = new Queue();
	
	// �� �������� �ڽ��� customerQueue�� ����ִ� Customer���� Train�� passengerQueue�� �����ش�.
	public void ticketing(){
		
		if(customerQueue.isEmpty()) return;
		
		for(int i=0; i < customerQueue.getNumberOfCustomer(); i++){
			// "���� �� �����ð� + ���� ����� �ð� + ���� Ƽ���� �ҿ�ð�"�� ���������ؼ� Train���� ������.
			if(customerQueue.getCustomer(i).getStationArrivalTime() 
					+ customerQueue.getCustomer(i).getWaitingTime()
					+ customerQueue.getCustomer(i).getTicketingTime()
					<= TicketingMain.CURRENT_TIME){

				// setTravleTime�� ���� Train���� ���� �� ������ �� �� Ż ������ Customer���� �˷��ش�.
				customerQueue.getCustomer(i).setTravelTime(getShortestPathTime(customerQueue.getCustomer(i)));
				Customer cus = customerQueue.deQueue();
				Train.getInstance().passengerQueue.enQueue(cus);
				i--;
			}
		}
	}
	
	/*
	 * ������ �ڽ��� customerQueue�� �ִ�(���� ���� �������) ��� Customer�� Ƽ���� �ҿ�ð��� ���ؼ� 
	 * ���� ���� ���� �� ����� �� ���� ��ٷ��� �ϴ��� �� �� �ְ� ���ش�.
	 * ("�� �տ� �ִ� ���� Ƽ���� �ð��� �� �� = �� ���ð�" �̱� �����̴�.)
	 */
	public int getTotalWaitingTime() {
		int result = 0;
		for(int i=0; i < customerQueue.getNumberOfCustomer(); i++){
			result = result + customerQueue.getCustomer(i).getTicketingTime();
		}
		return result;
	}
	
	
	// Station Ŭ������ �ִ� timeTable(���ú� �ּ� �̵��ð�)�� Ȱ���ؼ� �� ���� �����, �������� ���� �ҿ�ð��� �˷��ִ� �޼���
	private int getShortestPathTime(Customer customer) {
		String departureStation = customer.getDepartureStation();
		String arrivalStation = customer.getArrivalStation();
		
		int departureStationID = Station.getInstance().city.get(departureStation);
		int arrivalStationID = Station.getInstance().city.get(arrivalStation);

		return Station.getInstance().timeTable.getShortestTime(departureStationID, arrivalStationID);
	}
	
}
