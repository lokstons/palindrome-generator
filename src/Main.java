import java.util.*;

public class Main {
    private enum TypeOfNumber {EVEN , ODD}

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la cadena de caracteres: ");
        String input = scan.nextLine();
        longestPalindrome(input);
    }

    private static void longestPalindrome(String input){
        String palindrome = "";
        Random random = new Random();
        Map<Character, Integer> originalChars = getDiccionary(input.toCharArray());
        Map<Character, Integer> chars = getDiccionary(input.toCharArray());

        List<Character> evenChars = getEvenOrOddList(chars, TypeOfNumber.EVEN);
        List<Character> oddChars = getEvenOrOddList(chars, TypeOfNumber.ODD);

        boolean again = true;
        while(again){
            palindrome =  palindrome + getRandomCharacter(chars, originalChars, evenChars);
            again = difference(chars, originalChars, evenChars);
        }
        char[] prefix = palindrome.toCharArray();
        palindrome = palindrome + oddChars.get(random.nextInt(oddChars.size()));
        palindrome = palindrome + completePalindrome(prefix);
        System.out.println("Palindrome: " + palindrome);
        System.out.println("Palindrome's length: " + palindrome.toCharArray().length);
    }

    private static String completePalindrome(char[] prefix){
        String subfix = "";
        for (int i = prefix.length - 1; i >= 0; i--) {
            subfix = subfix + prefix[i];
        }
        return subfix;
    }

    private static boolean difference(Map<Character, Integer> chars, Map<Character, Integer> originalChars, List<Character> characters){
        boolean response = false;
        for (Character c : characters) {
            int original = originalChars.get(c);
            int variable = chars.get(c);
            response = response || (variable > (original / 2));
        }
        return response;
    }

    private static String getRandomCharacter(Map<Character, Integer> chars, Map<Character, Integer> originalChars, List<Character> characters){
        Random random = new Random();
        if(characters.size() > 0) {
            char randomChar = characters.get(random.nextInt(characters.size()));
            if (chars.get(randomChar) > (originalChars.get(randomChar) / 2)) {
                chars.put(randomChar, chars.get(randomChar) - 1);
                return String.valueOf(randomChar);
            }
        }
        return "";
    }

    private static Map<Character, Integer> getDiccionary(char[] input){
        Map<Character, Integer> chars = new HashMap<>();
        for (char c: input) {
            if(chars.get(c) != null){
                chars.put(c, chars.get(c) + 1);
            } else {
                chars.put(c, 1);
            }
        }
        return chars;
    }

    private static List<Character> getEvenOrOddList(Map<Character, Integer> chars, TypeOfNumber type){
        List<Character> characters = new ArrayList<>();
        if(type == TypeOfNumber.EVEN){
            for (char c : chars.keySet()) {
                int result = chars.get(c) % 2;
                if(result == 0){
                    characters.add(c);
                }
            }
        } else if(type == TypeOfNumber.ODD){
            for (char c : chars.keySet()) {
                int result = chars.get(c) % 2;
                if(result == 1){
                    characters.add(c);
                }
            }
        }
        return characters;
    }
}