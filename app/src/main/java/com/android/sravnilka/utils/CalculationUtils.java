package com.android.sravnilka.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dka on 16.11.2014.
 */
public class CalculationUtils {
    public static Map<String, Integer> calculateResult(Set<String> items,
                                                               Map<String, Set<String>> mapping,
                                                               Map<String, Integer> importanceScale){
        int maxIndex = -1;
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String item : items){
            result.put(item, 0);
        }

        for(Map.Entry<String, Integer> scale : importanceScale.entrySet()){
            String key = scale.getKey();
            int value = scale.getValue();

            if(value > maxIndex){
                maxIndex = value;
            }

            Set<String> grid = mapping.get(key);
            for(String item : grid){
                result.put(item, result.get(item) + value);
            }
        }

        int maxValue = maxIndex * (maxIndex + 1) / 2;
        for(Map.Entry<String, Integer> res : result.entrySet()){
            result.put(res.getKey(), Math.round(res.getValue() * 100 / maxValue));
        }
        return sortMapByValuesD(result);
    }

    private static LinkedHashMap sortMapByValuesD(Map<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList(passedMap.keySet());
        List<Integer> mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String)key, (Integer)val);
                    break;
                }

            }

        }
        return sortedMap;
    }
}
