package general;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	String resultS = "";
	List<String> resultString = new ArrayList<>();

	public static void main(String[] args) {
		
		
		String s = "Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00";
		System.out.println(new Solution().solution(s));
	}
	
/*	public int solution(int N) {
		if(N > 100_000_000) {
			return -1;
		} else {
			String num = "" + N;
			List<Integer> number = new ArrayList<>();
			for(int i=0;i<num.length();i++) {
				number.add(Integer.valueOf("" + num.charAt(i)));
			}
			number.stream().sorted(Comparator.reverseOrder()).forEach(i -> {resultS = resultS + i;});
			return Integer.valueOf(resultS);
		}	
	}*/
	
	public int solution(String S) {
		String lines[] = S.split("\\r?\\n");
		List<String> mon = new ArrayList<>();
		List<String> tue = new ArrayList<>();
		List<String> wed = new ArrayList<>();
		List<String> thu = new ArrayList<>();
		List<String> fri = new ArrayList<>();
		List<String> sat = new ArrayList<>();
		List<String> sun = new ArrayList<>();
		long finalResult  = 0;
		for(int i=0;i<lines.length;i++) {
			if(lines[i].startsWith("Mon")) {
				mon.add(lines[i].replaceAll("Mon ", ""));
			}
			if(lines[i].startsWith("Tue")) {
				tue.add(lines[i].replaceAll("Tue ", ""));
			}
			if(lines[i].startsWith("Wed")) {
				wed.add(lines[i].replaceAll("Wed ", ""));
			}
			if(lines[i].startsWith("Thu")) {
				thu.add(lines[i].replaceAll("Thu ", ""));
			}
			if(lines[i].startsWith("Fri")) {
				fri.add(lines[i].replaceAll("Fri ", ""));
			}
			if(lines[i].startsWith("Sat")) {
				sat.add(lines[i].replaceAll("Sat ", ""));
			}
			if(lines[i].startsWith("Sun")) {
				sun.add(lines[i].replaceAll("Sun ", ""));
			}
		}
		Stream<String []> result = Stream.of(mon, tue, wed, thu, fri, sat, sun).flatMap(s -> s.stream()).map(s-> s.split("-"));
		result.forEach(sArray -> {for(int i=0; i<sArray.length;i++) {
			resultString.add(sArray[i]);
		}}); 
		
		resultString.forEach(System.out::println);

		Stream<LocalTime> localTimeStream = resultString.stream().map(s -> {
			if(s.equals("24:00")) {
				s = "23:59";
			}
			return LocalTime.parse(s);});
		
		List<LocalTime> localTimeList = localTimeStream.collect(Collectors.toList());
		localTimeList.remove(0);
		for(int i=0; i<localTimeList.size()-1 ; i++) {
			if(ChronoUnit.MINUTES.between(localTimeList.get(i), localTimeList.get(i+1)) > finalResult) {
				finalResult = ChronoUnit.MINUTES.between(localTimeList.get(i), localTimeList.get(i+1));
			}
		}
		
		return (int)finalResult;
	}
	

}
