package tr.edu.mu.ceng.mad.myproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import tr.edu.mu.ceng.mad.myproject.Classes.Task;
import tr.edu.mu.ceng.mad.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TASKS = "tasks";
    private ArrayList<Task> tasks;

    // TODO: Rename and change types of parameters
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    //ITabbedAdapter = Fragment dene!!!!



    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tasks Parameter 1.
     * @return A new instance of fragment TaskFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String tasks) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASKS, tasks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container,
                false);

        viewPager2 = view.findViewById(R.id.viewpager2);
        tabLayout = view.findViewById(R.id.tablayout);

        //FRAGMENTS(On Tablayout)
        fragmentList.add(new ToDoFragment());
        fragmentList.add(new DoneFragment());

        TaskPagerAdapter taskPagerAdapter = new TaskPagerAdapter
                (getChildFragmentManager(), getLifecycle());

        viewPager2.setAdapter(taskPagerAdapter);

        titleList.add("To Do");
        titleList.add("Done");

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titleList.get(position))).attach();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

    }

    //PAGERADAPTER BETWEEN TABS!!!!!!
    public class TaskPagerAdapter extends FragmentStateAdapter {

        public TaskPagerAdapter(@NonNull FragmentManager fragmentManager,
                                @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }


    }


}