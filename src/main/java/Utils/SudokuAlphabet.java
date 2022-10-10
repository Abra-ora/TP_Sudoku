package utils;

import java.util.HashMap;
import java.util.Map;

public class SudokuAlphabet {
    Map<String, Integer> alphabet = new HashMap<>();

    public SudokuAlphabet() {
        alphabet.put("A", 10);
        alphabet.put("B", 11);
        alphabet.put("C", 12);
        alphabet.put("D", 13);
        alphabet.put("E", 14);
        alphabet.put("F", 15);
        alphabet.put("G", 16);
    }

    public Map<String, Integer> getAlphabet() {
        return alphabet;
    }

    public Integer getValue(String key) {
        return alphabet.get(key);
    }

    public String getKey(Integer value) {
        String result = null;
        for (Map.Entry<String, Integer> entry : alphabet.entrySet()) {
            if (entry.getValue().equals(value)) {
                result = entry.getKey();
            }
        }
        return result;
    }
}

