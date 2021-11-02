package general;

public class NumberToWord {
	public static void main(String args[]) {
		NumberToWord n = new NumberToWord();
		System.out.println(n.numberToWords(0));
		System.out.println(n.numberToWords(3));
		System.out.println(n.numberToWords(16));
		System.out.println(n.numberToWords(604));
		System.out.println(n.numberToWords(113321));
		System.out.println(n.numberToWords(-111104));
		System.out.println(n.numberToWords(111104));
		System.out.println(n.numberToWords(-2147483648));
		System.out.println(n.numberToWords(2147483647));
		
	}
	
	public String numberToWords(int num) {
		boolean isNegative = false;
		if(num==0) return "Zero";
		if(num<0) isNegative = true;
		
		int billions = Math.abs(num/1000000000);
		num %= 1000000000;
		int millions = Math.abs(num/1000000);
		num %= 1000000;
		int thousands = Math.abs(num/1000);
		num %= 1000;
		int hundreds = Math.abs(num);
		StringBuilder s = new StringBuilder();
		if(billions>0) s.append(helper(billions)+" Billion "); 
		if(millions>0) s.append(helper(millions)+" Million ");
		if(thousands>0) s.append(helper(thousands)+" Thousand ");
		if(hundreds>0) s.append(helper(hundreds));

		if(isNegative) {
			return "Negative " + s.toString().trim();
		} else {
			return s.toString().trim();
		}
		
	}

	public String helper(int n) {
		String[] tillNineteen = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
		String[] tens = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
		int hundred = n/100;
		n = n%100;

		StringBuilder s = new StringBuilder();
		if(hundred!=0) {
			s.append(tillNineteen[hundred]+" Hundred ");
		}
		if(n<20) {
			s.append(tillNineteen[n]);
		}
		else {
			int ten = n/10;
			n = n%10;
			s.append(tens[ten]+" "+tillNineteen[n]);
		}
		return s.toString().trim();
	}
}
