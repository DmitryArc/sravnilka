package com.android.sravnilka;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.android.sravnilka.ui.adapter.DragDropAdapter;
import com.android.sravnilka.ui.adapter.DragDropAdapter;
import com.android.sravnilka.ui.fragments.ComparatorFragment;
import com.android.sravnilka.ui.fragments.ItemsFactoryFragment;
//import com.android.sravnilka.ui.listeners.DragDropItemMovedListener;
//import com.android.sravnilka.ui.listeners.DragDropLongClickListener;
import com.android.sravnilka.ui.fragments.ParamsFactoryFragment;
import com.android.sravnilka.ui.fragments.ResultsFragment;
import com.android.sravnilka.ui.fragments.SorterFragment;
import com.android.sravnilka.ui.listeners.DragDropItemMovedListener;
import com.android.sravnilka.ui.listeners.DragDropLongClickListener;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.util.Map;
import java.util.Set;


public class RootActivity extends Activity implements IFlowController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_root);
        getActionBar().setDisplayHomeAsUpEnabled(true);

//        if(savedInstanceState == null){
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new ItemsFactoryFragment())
//                    .commit();
//        }

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**************************************************************
     * Menu
     **************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**************************************************************
     * IFlowController implementation
     **************************************************************/
    @Override
    public void onItemSetReady(Set<String> items) {
        openNewFragment(new ParamsFactoryFragment());
    }

    @Override
    public void onParamSetReady(Set<String> params) {
        openNewFragment(new ComparatorFragment());
    }

    @Override
    public void onCompareActionDone(Map<String, Set<String>> data) {
        openNewFragment(new SorterFragment());
    }

    @Override
    public void onImportanceScaleReady(Map<String, Integer> importanceScale) {
        openNewFragment(new ResultsFragment());
    }

    /**************************************************************
     * Helper methods
     **************************************************************/
    private void openNewFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fr_root, container, false);

            //Example Drag and Drop ListView
            Toast.makeText(getActivity(), R.string.drag_drop,Toast.LENGTH_LONG).show();
            DynamicListView listView = (DynamicListView) rootView.findViewById(R.id.dynamiclistview);
            ArrayAdapter<String> adapter = new DragDropAdapter(getActivity());
            listView.enableDragAndDrop();
            listView.setDraggableManager(new TouchViewDraggableManager(R.id.draganddrop_touchview));
            listView.setOnItemMovedListener(new DragDropItemMovedListener(adapter, getActivity()));
            listView.setOnItemLongClickListener(new DragDropLongClickListener(listView));
            listView.setAdapter(adapter);
            return rootView;
        }
    }



}
