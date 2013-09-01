package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerInfoHandler {
	
	// 파일에서 줄 단위로 읽어온 고객 정보를 한 줄씩 저장해놓는 ArrayList<String>
	ArrayList<String> customerInfo = new ArrayList<String>();

	// 인자로 받은 filePath에 있는 파일에서 고객 정보를 한 줄씩 읽어와 customerInfo에 넣어주는 메서드
	public void loadCustomerInfo(String filePath) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		
		String str;
		while((str = br.readLine()) != null){
			customerInfo.add(str);
		}
		
		br.close();
	}
	
	// cutomerInfo를 순회하면서 각 라인을 parsing해 Customer객체를 만들어주는 메서드
	// 생성한 Customer는 싱글톤 패턴으로 구현되어 있는 Home의 customersAtHome이라는 Queue에 넣어준다.
	public void createCustomer(){

		for(int i=1; i < customerInfo.size(); i++){
			// customerInfo를 순회하면서 한 줄짜리 String을 잘라 고객 정보로 가공해준다.
			String[] customerInfoList = customerInfo.get(i).split("	");
			int id = Integer.parseInt(customerInfoList[0]);
			String name = customerInfoList[1];
			int stationArrivalTime = Integer.parseInt(customerInfoList[2]);
			int ticketingTime = Integer.parseInt(customerInfoList[3]);
			String departureStation = customerInfoList[4];
			String arrivalStation = customerInfoList[5];
			
			// Customer를 생성해 Home에 넣어준다.
			Customer customer = new Customer(id, name, stationArrivalTime, ticketingTime, departureStation, arrivalStation);
			Home.getInstance().setCustomers(customer);
		}
	}
	
	// TicketingMain의 마지막에 호출되어 최종 결과를 result.txt파일에 출력시키는 메서드
	public void printResult() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt"));
		
		// 목적지에 도착한 승객들은 Train의 destinationQueue에 모여있다.
		while(!Train.getInstance().destinationQueue.isEmpty()){
			bw.write(Train.getInstance().destinationQueue.deQueue().toString() + System.getProperty("line.separator"));
			bw.flush();
		}
		
		bw.close();
	}
}
