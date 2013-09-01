package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerInfoHandler {
	
	// ���Ͽ��� �� ������ �о�� �� ������ �� �پ� �����س��� ArrayList<String>
	ArrayList<String> customerInfo = new ArrayList<String>();

	// ���ڷ� ���� filePath�� �ִ� ���Ͽ��� �� ������ �� �پ� �о�� customerInfo�� �־��ִ� �޼���
	public void loadCustomerInfo(String filePath) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		
		String str;
		while((str = br.readLine()) != null){
			customerInfo.add(str);
		}
		
		br.close();
	}
	
	// cutomerInfo�� ��ȸ�ϸ鼭 �� ������ parsing�� Customer��ü�� ������ִ� �޼���
	// ������ Customer�� �̱��� �������� �����Ǿ� �ִ� Home�� customersAtHome�̶�� Queue�� �־��ش�.
	public void createCustomer(){

		for(int i=1; i < customerInfo.size(); i++){
			// customerInfo�� ��ȸ�ϸ鼭 �� ��¥�� String�� �߶� �� ������ �������ش�.
			String[] customerInfoList = customerInfo.get(i).split("	");
			int id = Integer.parseInt(customerInfoList[0]);
			String name = customerInfoList[1];
			int stationArrivalTime = Integer.parseInt(customerInfoList[2]);
			int ticketingTime = Integer.parseInt(customerInfoList[3]);
			String departureStation = customerInfoList[4];
			String arrivalStation = customerInfoList[5];
			
			// Customer�� ������ Home�� �־��ش�.
			Customer customer = new Customer(id, name, stationArrivalTime, ticketingTime, departureStation, arrivalStation);
			Home.getInstance().setCustomers(customer);
		}
	}
	
	// TicketingMain�� �������� ȣ��Ǿ� ���� ����� result.txt���Ͽ� ��½�Ű�� �޼���
	public void printResult() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt"));
		
		// �������� ������ �°����� Train�� destinationQueue�� ���ִ�.
		while(!Train.getInstance().destinationQueue.isEmpty()){
			bw.write(Train.getInstance().destinationQueue.deQueue().toString() + System.getProperty("line.separator"));
			bw.flush();
		}
		
		bw.close();
	}
}
