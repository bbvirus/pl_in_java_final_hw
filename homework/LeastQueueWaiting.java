package homework;

import java.util.ArrayList;
import java.util.Collections;

// 대기시간이 가장 적은 Employee.customerQueue에 진입하도록 하는 전략을 구사하는 클래스이다.
public class LeastQueueWaiting implements Strategy {

	/*
	 * 각 Employee.customerQueue의 대기시간을 저장하는 ArrayList이다.
	 * waitingTimeList의 인덱스 = Station이 가지고 있는 employeeList의 인덱스 이므로
	 * waitingTimeList의 최소값이 저장되어 있는 인덱스를 리턴해 Customer가 그 employee를 선택할 수 있게 해준다.
	 */
	ArrayList<Integer> waitingTimeList = new ArrayList<Integer>();
	
	@Override
	public int chooseEmployeeIndex() {
		for(int i=0; i<Station.getInstance().numberOfEmployee; i++){
			waitingTimeList.add(Station.getInstance().employeeList.get(i).getTotalWaitingTime());
		}
		
		for(int i=0; i<Station.getInstance().numberOfEmployee; i++){
			if(waitingTimeList.get(i) == Collections.min(waitingTimeList)){
				waitingTimeList.removeAll(waitingTimeList);
				return i;
			}
		}
		
		return 0;
	}
}
