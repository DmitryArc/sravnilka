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
    private final static float WEIGHT = 10f;

    public static Map<String, Integer> calculateResult(Set<String> items,
                                                               Map<String, Set<String>> mapping,
                                                               Map<String, Integer> importanceScale){
        int maxIndex = -1;
        Map<String, Float> result = new HashMap<String, Float>();
        Map<String, Integer> intresult = new HashMap<String, Integer>();
        for (String item : items){
            result.put(item, 0f);
        }

        final float step = WEIGHT / (float)(importanceScale.size() + 1);

        for(Map.Entry<String, Integer> scale : importanceScale.entrySet()){
            String key = scale.getKey();
            int value = scale.getValue();

            if(value > maxIndex){
                maxIndex = value;
            }

            Set<String> grid = mapping.get(key);
            for(String item : grid){
                float val = WEIGHT - ( step * (float)(importanceScale.size() - value));
                result.put(item, result.get(item) + val);
            }
        }

        float s = (float)importanceScale.size();
        float maxValue = s * (WEIGHT + (WEIGHT - step * (s - 1))) / (float) 2; //maxIndex * (maxIndex + 1) / 2;
        for(Map.Entry<String, Float> res : result.entrySet()){
            intresult.put(res.getKey(), Math.round(res.getValue() * 100 / maxValue));
        }
        return sortMapByValuesD(intresult);
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
                    sortedMap.put(key, val);
                    break;
                }

            }

        }
        return sortedMap;
    }
}
