package homework;

public class Employee {
	
	// 종업원 앞에 서있는 줄을 의미하는 Queue
	Queue customerQueue = new Queue();
	
	// 각 직원들이 자신의 customerQueue에 들어있는 Customer들을 Train의 passengerQueue로 보내준다.
	public void ticketing(){
		
		if(customerQueue.isEmpty()) return;
		
		for(int i=0; i < customerQueue.getNumberOfCustomer(); i++){
			// "고객의 역 도착시간 + 고객이 대기한 시간 + 고객의 티켓팅 소요시간"을 기준으로해서 Train으로 보낸다.
			if(customerQueue.getCustomer(i).getStationArrivalTime() 
					+ customerQueue.getCustomer(i).getWaitingTime()
					+ customerQueue.getCustomer(i).getTicketingTime()
					<= TicketingMain.CURRENT_TIME){

				// setTravleTime을 통해 Train으로 보낼 때 기차를 몇 분 탈 것인지 Customer에게 알려준다.
				customerQueue.getCustomer(i).setTravelTime(getShortestPathTime(customerQueue.getCustomer(i)));
				Customer cus = customerQueue.deQueue();
				Train.getInstance().passengerQueue.enQueue(cus);
				i--;
			}
		}
	}
	
	/*
	 * 직원이 자신의 customerQueue에 있는(줄을 서서 대기중인) 모든 Customer의 티켓팅 소요시간을 더해서 
	 * 새로 줄을 서게 될 사람이 몇 분을 기다려야 하는지 알 수 있게 해준다.
	 * ("내 앞에 있는 고객들 티켓팅 시간의 총 합 = 내 대기시간" 이기 때문이다.)
	 */
	public int getTotalWaitingTime() {
		int result = 0;
		for(int i=0; i < customerQueue.getNumberOfCustomer(); i++){
			result = result + customerQueue.getCustomer(i).getTicketingTime();
		}
		return result;
	}
	
	
	// Station 클래스에 있는 timeTable(도시별 최소 이동시간)을 활용해서 각 고객의 출발지, 도착지에 따른 소요시간을 알려주는 메서드
	private int getShortestPathTime(Customer customer) {
		String departureStation = customer.getDepartureStation();
		String arrivalStation = customer.getArrivalStation();
		
		int departureStationID = Station.getInstance().city.get(departureStation);
		int arrivalStationID = Station.getInstance().city.get(arrivalStation);

		return Station.getInstance().timeTable.getShortestTime(departureStationID, arrivalStationID);
	}
	
}
