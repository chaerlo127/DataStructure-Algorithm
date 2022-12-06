package class2.wweek13;

import java.util.HashSet;

public class MeetingRoomMgr {
	
	private class aRequest{
		int reqId;
		double start;
		double finish;
		
		private aRequest(int i, double s, double f) {
			reqId = i;
			start = s;
			finish = f;
		}
	}
	
	HashSet<aRequest> requestSet; // 저장 순서가 유지되지 않음. => 따로 index를 저장해주는 과정이 필요
	double closingTime;
	
	public MeetingRoomMgr(double [][] in, double c) {
		requestSet = new HashSet<aRequest>();
		int index = 0;
		for (int i=0; i<in.length;i++) {
			requestSet.add(new aRequest(index, in[i][0], in[i][1])); // index의 값 및 start 시간, 끝나는 시간을 저장해서 set에 넣음.
			index++;
		}
		closingTime= c; // 끝내는 시간은 c임. 
	}
	
	public void showAllReq() {
		for(aRequest r : requestSet) {
			System.out.println(r.reqId+" : "+r.start+" ~ "+r.finish);
		}
	}
	
	public void startTimeFirst() {
		double timeLine =0;
		System.out.print("\nStart-Time First : ");

		while(timeLine<closingTime) {
			double tempBest=closingTime; // 현재 닫는 시간이 가장 베스트
			double tempTimeLine=closingTime; // 현재 닫는 시간이 가장 베스트
			int tempRequestId=-1;
			for(aRequest r : requestSet) {
				if (r.start>=timeLine && r.start<tempBest
						&& r.finish<=closingTime) { // 시작 시간이 현재 가장 베스트인 타임 라인 시간(finish) 보다 작아야하며, 
					// 타임 베스트의 경우 가장 베스트인 start가 작아야 하고, 끝나는 시간 또한 닫는 시간보다 작아야 함. 
					tempBest = r.start ; // 시작 시간이 가장 좋은 베스트, 0
					tempRequestId = r.reqId ; // 1
					tempTimeLine = r.finish ; // timeline은 끝나는 시간인 거 같음. 7.0
				}
			}
			if (tempRequestId>=0) { // 값이 변경이 되었다면
				timeLine = tempTimeLine;
				System.out.print("  "+tempRequestId);
			}
		}
	}

	public void finishTimeFirst() {
		double timeLine =0;
		System.out.print("\nFinish-Time First : ");

		while(timeLine<closingTime) {
			double tempBest=closingTime; // 현재 닫는 시간이 가장 베스트
			double tempTimeLine=closingTime; // 현재 닫는 시간이 가장 베스트
			int tempRequestId=-1;
			for(aRequest r : requestSet) {
				if (r.start>=timeLine && r.finish<=tempBest
						&& r.finish<=closingTime) {// 시작 시간이 현재 가장 베스트인 타임 라인 시간(finish) 보다 작아야하며, 
					// 타임 베스트의 경우 가장 베스트인 finish 보다 작아야 하고, 끝나는 시간 또한 닫는 시간보다 작아야 함. 
					tempBest = r.finish ;
					tempRequestId = r.reqId ;
					tempTimeLine = r.finish ;
				}
			}
			if (tempRequestId>=0) {
				timeLine = tempTimeLine;
				System.out.print("  "+tempRequestId); // index 값을 print
			}
		}
	}

	public static void main(String[] args) {
		double [][] input = { {0,7}, {0.5,2.5},{1,3.5},
				{3,4}, {3.5,7}, {4,8},{5,7},{7,10}, 
				{9,10.5},{10, 12}};
		
		MeetingRoomMgr me = new MeetingRoomMgr(input, 12);
		me.showAllReq();
		
		me.startTimeFirst(); // 시작 시간이 가장 이른
		me.finishTimeFirst(); // 끝내는 시간이 가징 이른

	}

}
