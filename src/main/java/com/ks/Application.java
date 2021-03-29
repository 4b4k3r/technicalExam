package com.ks;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exam :)
 */
public class Application
{
    /**
     * How many centuries have n years ?
     *
     * Example
     * Year 1 belongs to the 1st century
     * Year 99 belongs to the 1st century
     * Year 101 belongs to the 2nd century
     * Year 2020 belongs to the 21st century
     *
     * @param years years...
     * @return century on the years
     */
    public static int yearsToCenturies(int years)
    {
        return (years / 100) + 1;
    }

    /**
     * This method search a text equality inside of another text like SCRAMBLE !!!
     *
     * You don't need instructions, right?
     *
     * in: hdsasdellsdasooawwadwdwawrl ,  helloworld
     *     hd    ell    oo w        rl -> hdelloowrl
     * Yes, it doesn't look the same, but ordered you should get the same word, you know, like scramble ...
     *
     **** You must have the same number of letters if you use them more than once ***
     *
     * @param str1 unknown text
     * @param str2 searched word
     * @return if all same words and same quantity from second string exists in first string
     */
    public static boolean scramble(String str1, String str2)
    {
        HashMap<String, Integer> values = new HashMap<>();
        Stream.of(str1.split("")).forEach(a -> values.merge(a, 1, Integer::sum));
        Stream.of(str2.split("")).forEach(a -> values.merge(a, -1, Integer::sum));
        return values.values().stream().noneMatch(v -> v < 0);
    }

    /**
     * Find a middle char(s) in a text
     *
     * Example
     *
     * in: yes (hint: odd)
     * out: e
     *
     * in: word (hint: pair)
     * out: or
     *
     * *** Always wait for a single word ***
     *
     * @param word is the resource
     * @return a middle char(s)
     */
    public static String getMiddle(String word)
    {
        return word.length() % 2 > 0 ?
                String.valueOf(word.charAt((word.length() / 2))) :
                word.substring((int) Math.ceil(word.length() / 2d) - 1, (int) Math.ceil(word.length() / 2d) + 1);
    }

    /**
     * This method is used to find how many letters are repeated
     *
     * Example
     *
     * in: AcCoMmOdatIOn
     * out: 4
     *
     * AcCoMmOdatIOn -> have (a:2, c:2, o:3, m:2, d:1, t:1, i:1, n:1)
     *               -> just more than one (a:2, c:2, o:3, m:2)
     *               -> sum them             ^    ^    ^    ^  -> 4
     *
     * *** As you can see, case sensitive is irrelevant, spaces don't count ***
     *
     * @param text is the resource to search
     * @return how many words are repeated
     */
    public static int duplicateCount(String text)
    {
        HashMap<String, Integer> repeated = new HashMap<>();
        Stream.of(text.toLowerCase().split("")).forEach(val -> repeated.merge(val, 1, Integer::sum));
        return (int) repeated.values().stream().filter(v -> v > 1).count();
    }

    /**
     * This method get how many times a vocals are in a text
     *
     * Example
     *
     * in : "vowel counting is not an everyday thing"
     *        ^ ^   ^^  ^   ^   ^  ^  ^ ^   ^    ^   -> 12
     *
     * ***Always wait lowercase***
     *
     * @param str is a text to search
     * @return a number of how many times a vocals are in a text
     */
    public static int getCount(String str)
    {
        return Stream.of(str.split(""))
                .map(v -> v.matches("[aeiou]") ? 1 : 0)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    /**
     * This method solve a quantity of multiply all separated decimal numbers on a number recursively
     *
     * in : 236
     * out: 8
     *
     * how ?
     *
     * 236 -> (2 * 3 * 6) = [36] -> (3 * 6) = [18] (1 * 8) = [8]
     *
     * @param n a number to find a digital root
     * @return how many times a decimal values in a number can be multiplied recursively
     */
    public static int persistence(long n)
    {
        String val = String.valueOf(n);

        while (val.length() > 1)
        {
            val = String.valueOf(Stream.of(val.split(""))
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(-1));
        }

        return Integer.parseInt(val);
    }

    /**
     * This value sum all separated decimal numbers on a number recursively
     *
     * in : 123456
     * out : 3
     *
     * how ?
     *
     * 123456 -> (1 + 2 + 3 + 4 + 5 + 6) = [21] -> (2 + 1) = [3]
     *
     * @param n a number to find a digital root
     * @return digital root from a number
     */
    public static int digital_root(int n)
    {
        String val = String.valueOf(n);

        while (val.length() > 1)
        {
            val = String.valueOf(Stream.of(val.split(""))
                    .map(Integer::parseInt)
                    .reduce(Integer::sum)
                    .orElse(-1));
        }

        return Integer.parseInt(val);
    }


    /**
     * This method return a char pair in array
     *
     * Example
     * in: "Give me some pairs please"
     * out: [Gi, ve,  m, e , so, me,  p, ai, rs,  p, le, as, e_]
     *
     * ***Spaces also count***
     *
     * @param s a text to evaluate
     * @return an array with pair of chars
     */
    public static String[] solution(String s)
    {
        return (s + (s.length() % 2 > 0 ? "_" : "")).split("(?<=\\G..)");
    }

    /**
     * This method convert a long in a valid ip
     *
     * Example
     *
     * in: 2147483467L -> out : 127.255.255.75
     *
     * how ?
     * 1.- Convert a long in binary (you will always get 32 bits or less, if there is less just apply the pad to the left with zero)
     * 2.- Get 4 bytes (4 groups of 8 bits will give you 4 bytes)
     * 3.- Converts each byte to a decimal number
     * 4.- Apply the ip format to your 4 decimal numbers
     *
     * @param ip is a 32 bits number maximum
     * @return an IP
     */
    public static String longToIP(long ip)
    {
        return Stream.of(String.format("%32s", Long.toBinaryString(ip)).replace(" ", "0")
                .split("(?<=\\G........)"))
                .map(v -> String.valueOf(Integer.parseInt(v, 2)))
                .collect(Collectors.joining("."));
    }

    /**
     * This method find a shortest word in a text
     *
     * Example
     * in : "When I was young I had a lot of toys" -> out : 1
     *            ^           ^     ^              -> can be any of them
     *
     * in : "The worst part of school was solving problems like this" -> out : 2
     *                      ^^
     *
     * @param s a text to find the shortest word
     * @return a shortest word size in a text
     */
    public static int findShort(String s)
    {
        return Stream.of(s.split(" "))
                .map(String::length)
                .sorted()
                .findFirst()
                .orElse(-1);
    }

    /**
     * Find a first number repeated in the array
     *
     * Example
     * in : {1,2,3,8,5,2} -> out : 2
     * in : {5,9,6,7,5,9,9} -> out : 5
     * in : {5,1,6,7,8,9,9} -> out : 9
     *
     * @param values is an int array
     * @return a first number repeated in the array or else return -1
     */
    public static Integer repeated(int[] values)
    {
        HashMap<Integer, Integer> valuesRepeated = new HashMap<>();

        for (int value : values)
        {
            if (valuesRepeated.merge(value, 1, Integer::sum) > 1)
                return value;
        }

        return -1;
    }
}
