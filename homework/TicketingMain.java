package homework;

import java.io.IOException;
import java.util.Scanner;

// 이 프로그램의 메인 클래스이다.
public class TicketingMain {
	
	public static int CURRENT_TIME = 0;									// 프로그램 진행의 기준이 되는 현재 시간
	final static String CUSTOMER_INFO_FILE_NAME = "customerInfo.txt";	// 고객 정보를 불러올 파일 경로
	
	public static void main(String[] args) throws IOException {
		CustomerInfoHandler customerInfoHandler = new CustomerInfoHandler();	// 메인이 진행되는 순서는 아래와 같다.
		customerInfoHandler.loadCustomerInfo(CUSTOMER_INFO_FILE_NAME);			// 파일을 읽어서 고객 정보를 불러오고
		customerInfoHandler.createCustomer();									// 불러온 정보로 고객들을 생성해 Home에 보내고
		
		System.out.println("Ticketing Main Start~!");
		
		System.out.println("전략을 선택하세요. (1 or 2)");								// 티켓팅 전략을 설정하면 세팅이 완료.
		System.out.println("1. Round Robin");
		System.out.println("2. Least Queue Waiting");

		Scanner scanner = new Scanner(System.in);
		int inputStrategy = Integer.parseInt(scanner.next());
		Station.getInstance().chooseStrategy(inputStrategy);
		
		while(Station.getInstance().flag){
			Station.getInstance().enQueueCustomerToLobby();						// Home에 있는 고객들을 Station의 Lobby로 이동시키고
			Station.getInstance().enQueueCustomerInLobbyToTicketingBox();		// Lobby에 고객들을 전략에 따라 Employee에게 보내주고
			for(int i=0; i < Station.getInstance().numberOfEmployee; i++)		// 티켓팅을 해서 고객들을 기차에 탑승시키고
				Station.getInstance().employeeList.get(i).ticketing();			// 기차를 출발시키는 일을 반복한다.
			Train.getInstance().goTrain();

			CURRENT_TIME++;
		}
		
		customerInfoHandler.printResult();										// 마지막으로 결과를 출력하면 프로그램 종료.
		System.out.println("Ticketing Main End~!");
		
	}
}