package com.android.sravnilka;

import java.util.Map;
import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public interface IFlowController {
    void onItemSetReady(Set<String> items);
    void onParamSetReady(Set<String> params);
    void onCompareActionDone(Map<String, Set<String>> data);
    void onImportanceScaleReady(Map<String, Integer> importanceScale);
}
