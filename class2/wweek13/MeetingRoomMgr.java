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
	
	HashSet<aRequest> requestSet;
	double closingTime;
	
	public MeetingRoomMgr(double [][] in, double c) {
		requestSet = new HashSet<aRequest>();
		int index = 0;
		for (int i=0; i<in.length;i++) {
			requestSet.add(new aRequest(index, in[i][0], in[i][1]));
			index++;
		}
		closingTime= c;
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
			double tempBest=closingTime;
			double tempTimeLine=closingTime;
			int tempRequestId=-1;
			for(aRequest r : requestSet) {
				if (r.start>=timeLine && r.start<tempBest
						&& r.finish<=closingTime) {
					tempBest = r.start ;
					tempRequestId = r.reqId ;
					tempTimeLine = r.finish ;
				}
			}
			if (tempRequestId>=0) {
				timeLine = tempTimeLine ;
				System.out.print("  "+tempRequestId);
			}
		}
	}

	public void finishTimeFirst() {
		double timeLine =0;
		System.out.print("\nFinish-Time First : ");

		while(timeLine<closingTime) {
			double tempBest=closingTime;
			double tempTimeLine=closingTime;
			int tempRequestId=-1;
			for(aRequest r : requestSet) {
				if (r.start>=timeLine && r.finish<=tempBest
						&& r.finish<=closingTime) {
					tempBest = r.finish ;
					tempRequestId = r.reqId ;
					tempTimeLine = r.finish ;
				}
			}
			if (tempRequestId>=0) {
				timeLine = tempTimeLine ;
				System.out.print("  "+tempRequestId);
			}
		}
	}

	public static void main(String[] args) {
		double [][] input = { {0,7}, {0.5,2.5},{1,3.5},
				{3,4}, {3.5,7}, {4,8},{5,7},{7,10}, 
				{9,10.5},{10, 12}};
		
		MeetingRoomMgr me = new MeetingRoomMgr(input, 12);
		me.showAllReq();
		
		me.startTimeFirst();
		me.finishTimeFirst();

	}

}
