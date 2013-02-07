package com.danmidwood.danimals;


import java.util.BitSet;

public class BitStringParser {

    public long value(BitSet bitString) {
        long total = 0;
        for (int i = 0; i < bitString.size(); i++) {
            int power = bitString.size() - i;
            total = (long) (total + Math.pow(2, power));
        }
        return total;


    }
}
