package homework;

public class ShortestTimeMatrix {
	// 두 지역이 서로 인접하지 않은 경우 넣어줄 값
	int noPath = 9999;
	
	// 인접행렬. 서울, 춘천, 원주, 아산, 경주, 대전, 광주 순서.
	int adjacencyMatrix[][] = {
			{0, 16, 22, 20, noPath, 29, noPath},
			{16, 0, 28, noPath, 31, noPath, noPath},
			{22, 28, 0, noPath, 32, 23, noPath},
			{20, noPath, noPath, 0, noPath, 35, 25},
			{noPath, 31, 32, noPath, 0, 15, 18},
			{29, noPath, 23, 35, 15, 0, 12},
			{noPath, noPath, noPath, 25, 18, 12, 0}};
	
	// 인접행렬을 이용해 각 노드간 최단거리를 구하는 Floyd 알고리즘
	public void setShortestTimeTable(){

		for(int mid=0; mid < 7; mid++){
			for(int start=0; start < 7; start++){
				for(int end=0; end < 7; end++){
					if(adjacencyMatrix[start][mid] + adjacencyMatrix[mid][end] < adjacencyMatrix[start][end]){
						adjacencyMatrix[start][end] = adjacencyMatrix[start][mid] + adjacencyMatrix[mid][end];
					}
				}
			}
		}
	}
	
	// 출발지와 도착지의 인덱스를 입력하면 최단거리를 리턴해주는 메서드
	public int getShortestTime(int start, int end){
		return adjacencyMatrix[start][end];
	}
	
}
