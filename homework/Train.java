package homework;

public class Train {
	Queue passengerQueue = new Queue();		// Customer가 Train에 타면 passenger가 된다. 
	Queue destinationQueue = new Queue();	// 목적지에 도착한 Customer들을 저장해둔다. (애초엔 Train 밖에 마련해두려고 했지만 또 클래스를 만들기 싫어서..)
	
	// 싱글톤 패턴으로 구현했다.
	private static Train train = new Train();
	
	private Train(){}
	
	public static Train getInstance(){
		return train;
	}
	
	// 기차가 5분 간격으로 출발한다.
	// passengerQueue에 있는 Customer들을 detinationQueue로 옮겨담는 동시에 Customer의 목적지 도착 시간(destinationArrivalTime)을 설정해준다.
	public void goTrain(){
		if(TicketingMain.CURRENT_TIME % 5 == 0){
			for(int i=0; i<Train.getInstance().passengerQueue.getNumberOfCustomer(); i++){
				Customer passenger = passengerQueue.deQueue();
				i--;
				passenger.setDestinationArrivalTime(passenger.getTravelTime() + TicketingMain.CURRENT_TIME);
				destinationQueue.enQueue(passenger);
			}
		}
		
		// 이 문제에서는 고객 수가 50명이므로 목적지에 도착한 Customer의 수가 50이면 Station이 영업을 끝내도록 해두었다.
		if(destinationQueue.getNumberOfCustomer() >= 50){
			Station.getInstance().flag = false;
		}
			
	}
}