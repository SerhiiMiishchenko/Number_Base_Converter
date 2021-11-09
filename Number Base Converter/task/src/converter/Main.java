package converter;

import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String prompt;
        String sourceBase;
        String targetBase;
        String number = "";

        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            prompt = sc.next();
            if (!prompt.equals("/exit")) {
                sourceBase = prompt;
                targetBase = sc.next();
                while (true) {
                    System.out.printf("Enter number in base %s to convert to base %s (To go back type /back)\n", sourceBase, targetBase);
                    number = sc.next();
                    String[] numbers = number.split("\\.");
                    if (!number.equals("/back") && numbers.length == 1) {
                        System.out.println("Conversion result: " + convert(sourceBase, targetBase, number));
                    } else if (!number.equals("/back") && numbers.length == 2) {
                        String intPart = convert(sourceBase, targetBase, numbers[0]);
                        String fractToDecimal = convertFrToDecimal(sourceBase, numbers[1]);
                        String fractPart = convertFrToTargetBase(targetBase, fractToDecimal);
                        System.out.println("Conversion result: " + intPart + "." + fractPart);
                    }
                    else break;
                }
            } else break;
        }
    }

    public static String convert(String sourceBase, String targetBase, String number) {
        BigInteger bi = new BigInteger(number, Integer.parseInt(sourceBase));
        BigInteger decimalBi = new BigInteger(bi.toString());
        return decimalBi.toString(Integer.parseInt(targetBase));
    }
    
    public static String convertFrToDecimal(String sourceBase, String number) {
         int base = Integer.parseInt(sourceBase);
         char[] chars = number.toCharArray();
         BigDecimal bd = BigDecimal.ZERO;
         
         for (int i = 0; i < chars.length; i++) {
             bd = bd.add(BigDecimal.valueOf(Character.getNumericValue(chars[i]) * (1 / Math.pow(base, i + 1))));
         }
         return bd.toString();
     }
     
     public static String convertFrToTargetBase(String targetBase, String number) {
         int base = Integer.parseInt(targetBase);
         BigDecimal bdFr = new BigDecimal(number);
         BigDecimal multi = bdFr.multiply(BigDecimal.valueOf(base).pow(5));
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < 5; i++) {
             //this is change 64 line
             int remainder = multi.remainder(BigDecimal.valueOf(base)).intValue();
             char ch = Character.forDigit(remainder, base);
             sb.append(ch);
             multi = multi.divide(BigDecimal.valueOf(base));
         }
         return sb.reverse().toString();
     }

}
