package homework;

public class RoundRobin implements Strategy{
	
	// employeeList�� �ε����� �ϳ��� ������Ű�鼭 employee�� ��(3)�� ������ ������ �Ѵ�.
	// ����� Station�� ������ �ִ� employeeList�� �ε����� �������ش�.
	@Override
	public int chooseEmployeeIndex() {
		Station.employeeIndex++;
		return Station.employeeIndex % 3;
	}

}
