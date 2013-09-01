package homework;

import java.util.ArrayList;
import java.util.HashMap;

public class Station {

	public static int employeeIndex = 0;							// employeeList의 인덱스
	Queue stationLobbyQueue = new Queue();							// 처음 Station에 들어온 Customer들이 모여있는 Lobby
	int numberOfEmployee = 3;										// Station이 고용하고 있는 Employee의 수
	ArrayList<Employee> employeeList = new ArrayList<Employee>();	// Employee를 관리하는 ArrayList
	ShortestTimeMatrix timeTable = new ShortestTimeMatrix();		// 각 도시간 최소 이동시간을 저장해두는 timeTable
	HashMap<String, Integer> city = new HashMap<String, Integer>();	// 도시의 String name와 timeTable의 인덱스를 묶어서 저장해두는 해시맵
	boolean flag = true;											// Station이 열려있는지를 표시. true면 영업 중인 것이다.
	Strategy strategy;												// Lobby에 있는 Customer들을 Employee에게 보낼 때 쓸 전략을 저장해둔다.
	
	// Station은 싱글톤 패턴으로 구현했다.
	private static Station station = new Station();
	
	public static Station getInstance(){
		return station;
	}
	
	// Station이 생성될 때 Employee를 고용하고 timeTable과 city를 만들어둔다.
	private Station(){
		for(int i=0; i < numberOfEmployee; i++)
			employeeList.add(new Employee());
		
		timeTable.setShortestTimeTable();
		setCityInfo();
	}
	
	private void setCityInfo() {
		city.put("Seoul", 0);
		city.put("Chuncheon", 1);
		city.put("Wonju", 2);
		city.put("Asan", 3);
		city.put("Kyungju", 4);
		city.put("Deajeon", 5);
		city.put("Gwangju", 6);
	}

	// Home에 있는 Customer들의 역 도착 시간(stationArrivalTime)을 확인해 stationLobbyQueue로 불러오는 메서드
	public void enQueueCustomerToLobby() {

		if(Home.getInstance().customersAtHome.isEmpty()) return;
		
		for(int i=0; i < Home.getInstance().customersAtHome.getNumberOfCustomer(); i++){
			
			if(Home.getInstance().customersAtHome.getCustomer(i).getStationArrivalTime() == TicketingMain.CURRENT_TIME) {
				stationLobbyQueue.enQueue(Home.getInstance().customersAtHome.deQueue());
				i--;
			}

			// 이 문제에서는 고객들의 역 도착 시간이 정렬되어 있으므로 현재 시간보다 역 도착 시간이 나중이면 loop를 멈춰도 된다.
			else if(Home.getInstance().customersAtHome.getCustomer(i).getStationArrivalTime() > TicketingMain.CURRENT_TIME)
				break;
		}
	}
	
	// stationLobbyQueue에 있는 Customer들을 Strategy에 따라 Employee의 customerQueue로 보내준다.
	public void enQueueCustomerInLobbyToTicketingBox(){
		
		if(Station.getInstance().stationLobbyQueue.isEmpty()) return;
		
		for(int i=0; i<Station.getInstance().stationLobbyQueue.getNumberOfCustomer(); i++){
			// Lobby에서 Employee에게 보내지는 Customer에게 티켓팅 시간을 알려준다.
			Station.getInstance().stationLobbyQueue.getCustomer(i).setWaitingTime(employeeList.get(strategy.chooseEmployeeIndex()).getTotalWaitingTime());

			// strategy.chooseEmployeeIndex()를 통해 어느 Employee에게 Customer를 보낼지 결정한다.ㄴ
			employeeList.get(strategy.chooseEmployeeIndex()).customerQueue.enQueue(Station.getInstance().stationLobbyQueue.deQueue());
			i--;
		}
	}

	// TicketingMain에서 호출되어 티켓팅 전략을 결정하게 한다.
	public void chooseStrategy(int strategyNumber) {
		if(strategyNumber==1)
			strategy = new RoundRobin();
		else if(strategyNumber==2)
			strategy = new LeastQueueWaiting();
		else
			System.out.println("제대로된 값을 입력하세요. 전략을 선택하지 않으면 진행할 수 없습니다.");
	}

}
