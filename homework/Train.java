package homework;

public class Train {
	Queue passengerQueue = new Queue();		// Customer�� Train�� Ÿ�� passenger�� �ȴ�. 
	Queue destinationQueue = new Queue();	// �������� ������ Customer���� �����صд�. (���ʿ� Train �ۿ� �����صη��� ������ �� Ŭ������ ����� �Ⱦ..)
	
	// �̱��� �������� �����ߴ�.
	private static Train train = new Train();
	
	private Train(){}
	
	public static Train getInstance(){
		return train;
	}
	
	// ������ 5�� �������� ����Ѵ�.
	// passengerQueue�� �ִ� Customer���� detinationQueue�� �Űܴ�� ���ÿ� Customer�� ������ ���� �ð�(destinationArrivalTime)�� �������ش�.
	public void goTrain(){
		if(TicketingMain.CURRENT_TIME % 5 == 0){
			for(int i=0; i<Train.getInstance().passengerQueue.getNumberOfCustomer(); i++){
				Customer passenger = passengerQueue.deQueue();
				i--;
				passenger.setDestinationArrivalTime(passenger.getTravelTime() + TicketingMain.CURRENT_TIME);
				destinationQueue.enQueue(passenger);
			}
		}
		
		// �� ���������� �� ���� 50���̹Ƿ� �������� ������ Customer�� ���� 50�̸� Station�� ������ �������� �صξ���.
		if(destinationQueue.getNumberOfCustomer() >= 50){
			Station.getInstance().flag = false;
		}
			
	}
}