package tr.edu.mu.ceng.mad.myproject.Fragments;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import tr.edu.mu.ceng.mad.myproject.Adapters.MyDoneRecyclerViewAdapter;
import tr.edu.mu.ceng.mad.myproject.Classes.Done;
import tr.edu.mu.ceng.mad.myproject.R;

/**
 * A fragment representing a list of Items.
 */
public class DoneFragment extends Fragment implements Serializable{ //implements Serializable

    // TODO: Customize parameter argument names
    private static final String ARG_DONE = "task_done";
    private ArrayList<Done> doneArrayList;
    //public IAdapter mListener;
    //private Bundle b2= new Bundle();
    private MyDoneRecyclerViewAdapter doneRecyclerViewAdapter;
    private RecyclerView list;
    //FIREBASE1
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DoneFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DoneFragment newInstance(int doneArrayList) {
        DoneFragment fragment = new DoneFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DONE, doneArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.
                SCREEN_ORIENTATION_PORTRAIT);
        if (getArguments() != null) {
            doneArrayList = (ArrayList<Done>) getArguments().getSerializable(ARG_DONE);

            //FIREBASE!!!1
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("tasks").child("done");
            allDoneTasks();
        }
    }


    //Adding tasks_done in Recyclerview.
    public void allDoneTasks() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doneArrayList.clear();

                for(DataSnapshot d: snapshot.getChildren()){
                    Done done= d.getValue(Done.class);
                    Done.setId_done(d.getKey());//Setting Ids for delete and update operations

                    doneArrayList.add(done);
                }
                doneRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR:"," Can't click the list object!");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done_list, container, false);

        list= view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        doneArrayList = new ArrayList<>();

        //Bundle b2= new Bundle();


        doneRecyclerViewAdapter = new MyDoneRecyclerViewAdapter(doneArrayList,
                getContext());
        list.setAdapter(doneRecyclerViewAdapter);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        return view;
    }


}