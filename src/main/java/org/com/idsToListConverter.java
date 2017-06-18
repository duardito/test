package org.com;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class idsToListConverter  implements Converter<String, List<Integer>> {


    @Override
    public List<Integer> convert(String s) {
        StringTokenizer st2 = new StringTokenizer(s, ".");
        List<Integer> lista = new ArrayList<Integer>(0);
        while (st2.hasMoreElements()){
            Integer value = (Integer)st2.nextElement();
            lista.add(value);
        }
        return lista;
    }
}
