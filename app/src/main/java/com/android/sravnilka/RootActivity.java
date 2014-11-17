package com.android.sravnilka;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.sravnilka.ui.fragments.ComparatorFragment;
import com.android.sravnilka.ui.fragments.ItemsFactoryFragment;
import com.android.sravnilka.ui.fragments.ParamsFactoryFragment;
import com.android.sravnilka.ui.fragments.ResultsFragment;
import com.android.sravnilka.ui.fragments.SorterFragment;
import com.android.sravnilka.utils.CalculationUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class RootActivity extends Activity implements IFlowController {
    private Set<String> mItemSet;
    private Set<String> mParamSet;
    private Map<String, Set<String>> mMapping;
    private Map<String, Integer> mImportanceScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_root);

        mItemSet = new TreeSet<String>();
        mParamSet = new TreeSet<String>();
        mMapping = new LinkedHashMap<String, Set<String>>();
        mImportanceScale = new LinkedHashMap<String, Integer>();

        ImageView view = (ImageView)findViewById(android.R.id.home);
//        view.setPadding((int)getResources().getDimension(R.dimen.homeAsUpIndicator_padding), 0,
//                (int)getResources().getDimension(R.dimen.homeAsUpIndicator_padding), 0);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ItemsFactoryFragment())
                    .commit();
        }
    }

    /**************************************************************
     * Menu
     **************************************************************/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**************************************************************
     * IFlowController implementation
     **************************************************************/
    @Override
    public boolean onItemSetReady(Set<String> items) {
        if(items.isEmpty()){
            Toast.makeText(this, R.string.check_notification, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mItemSet = items;
            openNewFragment(new ParamsFactoryFragment());
            return true;
        }
    }

    @Override
    public boolean onParamSetReady(Set<String> params) {
        if(params.isEmpty()){
            Toast.makeText(this, R.string.check_notification, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mParamSet = params;
            openNewFragment(ComparatorFragment.newInstance(mItemSet, mParamSet));
            return true;
        }
    }

    @Override
    public void onCompareActionDone(Map<String, Set<String>> data) {
        mMapping = data;
        openNewFragment(SorterFragment.newInstance(mParamSet));
    }

    @Override
    public void onImportanceScaleReady(Map<String, Integer> importanceScale) {
        mImportanceScale = importanceScale;
        openNewFragment(ResultsFragment.newInstance(CalculationUtils.calculateResult(mItemSet, mMapping, mImportanceScale)));
    }

    @Override
    public void onReload() {
        clearData();

        FragmentManager fm = getFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left);
        ft.replace(R.id.container, new ItemsFactoryFragment()).commit();
    }

    /**************************************************************
     * Helper methods
     **************************************************************/
    private void openNewFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                R.animator.slide_in_from_left, R.animator.slide_out_to_right);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void clearData(){
        mItemSet.clear();
        mParamSet.clear();
        mMapping.clear();
        mImportanceScale.clear();
    }

}
