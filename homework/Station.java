package homework;

import java.util.ArrayList;
import java.util.HashMap;

public class Station {

	public static int employeeIndex = 0;							// employeeList�� �ε���
	Queue stationLobbyQueue = new Queue();							// ó�� Station�� ���� Customer���� ���ִ� Lobby
	int numberOfEmployee = 3;										// Station�� ����ϰ� �ִ� Employee�� ��
	ArrayList<Employee> employeeList = new ArrayList<Employee>();	// Employee�� �����ϴ� ArrayList
	ShortestTimeMatrix timeTable = new ShortestTimeMatrix();		// �� ���ð� �ּ� �̵��ð��� �����صδ� timeTable
	HashMap<String, Integer> city = new HashMap<String, Integer>();	// ������ String name�� timeTable�� �ε����� ��� �����صδ� �ؽø�
	boolean flag = true;											// Station�� �����ִ����� ǥ��. true�� ���� ���� ���̴�.
	Strategy strategy;												// Lobby�� �ִ� Customer���� Employee���� ���� �� �� ������ �����صд�.
	
	// Station�� �̱��� �������� �����ߴ�.
	private static Station station = new Station();
	
	public static Station getInstance(){
		return station;
	}
	
	// Station�� ������ �� Employee�� ����ϰ� timeTable�� city�� �����д�.
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

	// Home�� �ִ� Customer���� �� ���� �ð�(stationArrivalTime)�� Ȯ���� stationLobbyQueue�� �ҷ����� �޼���
	public void enQueueCustomerToLobby() {

		if(Home.getInstance().customersAtHome.isEmpty()) return;
		
		for(int i=0; i < Home.getInstance().customersAtHome.getNumberOfCustomer(); i++){
			
			if(Home.getInstance().customersAtHome.getCustomer(i).getStationArrivalTime() == TicketingMain.CURRENT_TIME) {
				stationLobbyQueue.enQueue(Home.getInstance().customersAtHome.deQueue());
				i--;
			}

			// �� ���������� ������ �� ���� �ð��� ���ĵǾ� �����Ƿ� ���� �ð����� �� ���� �ð��� �����̸� loop�� ���絵 �ȴ�.
			else if(Home.getInstance().customersAtHome.getCustomer(i).getStationArrivalTime() > TicketingMain.CURRENT_TIME)
				break;
		}
	}
	
	// stationLobbyQueue�� �ִ� Customer���� Strategy�� ���� Employee�� customerQueue�� �����ش�.
	public void enQueueCustomerInLobbyToTicketingBox(){
		
		if(Station.getInstance().stationLobbyQueue.isEmpty()) return;
		
		for(int i=0; i<Station.getInstance().stationLobbyQueue.getNumberOfCustomer(); i++){
			// Lobby���� Employee���� �������� Customer���� Ƽ���� �ð��� �˷��ش�.
			Station.getInstance().stationLobbyQueue.getCustomer(i).setWaitingTime(employeeList.get(strategy.chooseEmployeeIndex()).getTotalWaitingTime());

			// strategy.chooseEmployeeIndex()�� ���� ��� Employee���� Customer�� ������ �����Ѵ�.��
			employeeList.get(strategy.chooseEmployeeIndex()).customerQueue.enQueue(Station.getInstance().stationLobbyQueue.deQueue());
			i--;
		}
	}

	// TicketingMain���� ȣ��Ǿ� Ƽ���� ������ �����ϰ� �Ѵ�.
	public void chooseStrategy(int strategyNumber) {
		if(strategyNumber==1)
			strategy = new RoundRobin();
		else if(strategyNumber==2)
			strategy = new LeastQueueWaiting();
		else
			System.out.println("����ε� ���� �Է��ϼ���. ������ �������� ������ ������ �� �����ϴ�.");
	}

}
