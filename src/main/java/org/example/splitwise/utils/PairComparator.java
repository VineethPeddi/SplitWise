package org.example.splitwise.utils;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair mn, Pair mx) {
        return mn.getAmount()-mx.getAmount();
    }
}
