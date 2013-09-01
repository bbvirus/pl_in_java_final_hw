package homework;

import java.io.IOException;
import java.util.Scanner;

// �� ���α׷��� ���� Ŭ�����̴�.
public class TicketingMain {
	
	public static int CURRENT_TIME = 0;									// ���α׷� ������ ������ �Ǵ� ���� �ð�
	final static String CUSTOMER_INFO_FILE_NAME = "customerInfo.txt";	// �� ������ �ҷ��� ���� ���
	
	public static void main(String[] args) throws IOException {
		CustomerInfoHandler customerInfoHandler = new CustomerInfoHandler();	// ������ ����Ǵ� ������ �Ʒ��� ����.
		customerInfoHandler.loadCustomerInfo(CUSTOMER_INFO_FILE_NAME);			// ������ �о �� ������ �ҷ�����
		customerInfoHandler.createCustomer();									// �ҷ��� ������ ������ ������ Home�� ������
		
		System.out.println("Ticketing Main Start~!");
		
		System.out.println("������ �����ϼ���. (1 or 2)");								// Ƽ���� ������ �����ϸ� ������ �Ϸ�.
		System.out.println("1. Round Robin");
		System.out.println("2. Least Queue Waiting");

		Scanner scanner = new Scanner(System.in);
		int inputStrategy = Integer.parseInt(scanner.next());
		Station.getInstance().chooseStrategy(inputStrategy);
		
		while(Station.getInstance().flag){
			Station.getInstance().enQueueCustomerToLobby();						// Home�� �ִ� ������ Station�� Lobby�� �̵���Ű��
			Station.getInstance().enQueueCustomerInLobbyToTicketingBox();		// Lobby�� ������ ������ ���� Employee���� �����ְ�
			for(int i=0; i < Station.getInstance().numberOfEmployee; i++)		// Ƽ������ �ؼ� ������ ������ ž�½�Ű��
				Station.getInstance().employeeList.get(i).ticketing();			// ������ ��߽�Ű�� ���� �ݺ��Ѵ�.
			Train.getInstance().goTrain();

			CURRENT_TIME++;
		}
		
		customerInfoHandler.printResult();										// ���������� ����� ����ϸ� ���α׷� ����.
		System.out.println("Ticketing Main End~!");
		
	}
}