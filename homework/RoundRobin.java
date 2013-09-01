package homework;

public class RoundRobin implements Strategy{
	
	// employeeList의 인덱스를 하나씩 증가시키면서 employee의 수(3)와 나머지 연산을 한다.
	// 결과를 Station이 가지고 있는 employeeList의 인덱스로 리턴해준다.
	@Override
	public int chooseEmployeeIndex() {
		Station.employeeIndex++;
		return Station.employeeIndex % 3;
	}

}
