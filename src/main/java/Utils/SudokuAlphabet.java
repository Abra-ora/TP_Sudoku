package Utils;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualLinkedHashBidiMap;

import java.util.HashMap;

public class SudokuAlphabet {
//    static HashMap<String, Integer> alphabet = new HashMap<>();
    BidiMap<String, Integer> alphabet = new DualLinkedHashBidiMap<>();
    public SudokuAlphabet(){
        alphabet.put("A", 10);
        alphabet.put("B", 11);
        alphabet.put("C", 12);
        alphabet.put("D", 13);
        alphabet.put("E", 14);
        alphabet.put("F", 15);
        alphabet.put("G", 16);
    }
    public BidiMap<String, Integer> getAlphabet(){
        return alphabet;
    }

    public Integer getValue(String key){
        return alphabet.get(key);
    }

    public String getKey(Integer value){
        return alphabet.getKey(value);
    }

}
