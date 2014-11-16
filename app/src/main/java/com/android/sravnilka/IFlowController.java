package com.android.sravnilka;

import java.util.Map;
import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public interface IFlowController {
    boolean onItemSetReady(Set<String> items);
    boolean onParamSetReady(Set<String> params);
    void onCompareActionDone(Map<String, Set<String>> data);
    void onImportanceScaleReady(Map<String, Integer> importanceScale);
    void onReload();
}
