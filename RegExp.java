import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RegExp {

    //Test whether the string is palindrome
    public static boolean ifPalindrome(String line) {
        String reversed = "";
        Boolean reverse_check;
        for (int i = 0; i < line.length(); i++) {
            reversed = line.charAt(i) + reversed;
        }
        reverse_check = line.equals(reversed);
        if (reverse_check) {
            return true;
        }
        return false;
    }
    
    //Check if a string contains antoher string
    public static boolean containsSubstring(String str, String substr) {
        // If the smaller string is longer, it can't be contained
        if (substr.length() > str.length()) {
            return false; 
        }
        
        /** To find the maximum starting index in str where substr can fit 
        *   without going beyond the bounds of str
        */
        for (int i = 0; i <= str.length() - substr.length(); i++) {
            boolean found = true;
            for (int j = 0; j < substr.length(); j++) {
                if (str.charAt(i + j) != substr.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

    //Check if contains a specific string equal to or more than n times
    public static boolean countSubstring(int n, String str,String substr) {
        int count = 0;
        if (str.length() < substr.length()) {
            return false;
        }
        for (int i = 0; i <= str.length() - substr.length(); i++) {
            boolean found = true;
            for(int j = 0; j < substr.length(); j++){
                if (str.charAt(i + j) != substr.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if(found){
                count++;
                if(count >= n){
                    return true;
                }
            }
        }
        return false;
    }

    //Check if cotains "aXbb", "aaXbbbb", etc pattern
    public static boolean containsPattern(String line){
        boolean foundA = false;
        int consecutiveBs = 0;
        for (int i = 0; i < line.length(); i++) {
            // Find the first 'a'
            if (!foundA) { 
                if (line.charAt(i) == 'a') {
                    foundA = true;
                }
            } 
            // Found 2 consecutive 'b's behind a's index
            else {
                if (line.charAt(i) == 'b') {
                    consecutiveBs++;
                    if (consecutiveBs == 2) {
                        return true;  
                    }
                }
                else {
                    consecutiveBs = 0;  
                }
            }
        }
        return false;  
    }

    public static void main(String[] args) {
        String str1 = args[1];
        String str2 = args[2];
        int s2Count = Integer.parseInt(args[3]);
        String result = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                result += (ifPalindrome(line)) ? "Y,":"N,";
                result += (containsSubstring(line, str1)) ? "Y,":"N,";
                result += (countSubstring(s2Count, line, str2)) ? "Y,":"N,";
                result += (containsPattern(line))? "Y":"N";
                System.out.println(result);
                result = "";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}