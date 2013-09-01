package homework;

public class Customer {

	private int ID;							// 고객 아이디
	private String name;					// 고객 이름
	private int stationArrivalTime;			// 역에 도착하는 시간
	private int ticketingTime;				// 티켓팅 소요시간
	private String departureStation;		// 출발역
	private String arrivalStation;			// 도착역
	private int waitingTime = 0;			// 대기시간
	private int travelTime = 0;				// 목적지까지 가는 데 걸리는 시간
	private int destinationArrivalTime = 0;	// 최종 도착시간
	
	// 이하 각 필드의 getter, setter들
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}

	public int getStationArrivalTime() {
		return stationArrivalTime;
	}

	public int getTicketingTime() {
		return ticketingTime;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public int getDestinationArrivalTime() {
		return destinationArrivalTime;
	}

	public void setDestinationArrivalTime(int destinationArrivalTime) {
		this.destinationArrivalTime = destinationArrivalTime;
	}
	
	// 생성자 : 파일에서 읽어 온 고객 정보 String을 parsing해서 정보를 넣어준다.
	Customer(int ID, String name, int stationArrivalTime, int ticketingTime, String departureStation, String arrivalStation){
		this.ID = ID;
		this.name = name;
		this.stationArrivalTime = stationArrivalTime;
		this.ticketingTime = ticketingTime;
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
	}
	
	// Customer을 출력하면 나오는 결과
	// 예) 1번 고객ㄱ1001님께서는 0분에 역에 도착해서 0분동안 기다렸다가 2분동안 표를 끊은 Seoul역에서 출발해 16분 후 목적지인 Chuncheon역에 도착하셨습니다. 도착시간은 21분입니다.
	public String toString(){
		return ID+"번 "
				+ name+"님께서는 "
				+ stationArrivalTime+"분에 역에 도착해서 "
				+ waitingTime+"분동안 기다렸다가 "
				+ ticketingTime+"분동안 표를 끊은 다음 "
				+ departureStation+"역에서 출발해 "
				+ travelTime+"분 후 목적지인 "
				+ arrivalStation+"역에 도착하셨습니다. "
				+ "도착시각은 "+destinationArrivalTime+"분 입니다.";
	}
}
