package tr.edu.mu.ceng.mad.myproject.Fragments;

import android.content.Context;
import android.content.Intent;
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
import java.util.TimeZone;

import tr.edu.mu.ceng.mad.myproject.Activities.TaskDetailsActivity;
import tr.edu.mu.ceng.mad.myproject.Adapters.MyToDoRecyclerViewAdapter;
import tr.edu.mu.ceng.mad.myproject.Classes.Task;
import tr.edu.mu.ceng.mad.myproject.Classes.ToDo;
import tr.edu.mu.ceng.mad.myproject.R;


/**
 * A fragment representing a list of Items.
 */
public class ToDoFragment extends Fragment implements Serializable{

    // TODO: Customize parameter argument names
    private static final String ARG_TODO = "task_todo";
    private OnTodoTaskListInteractionListener mListener;
    //private ArrayList<Task> tasks1;
    private ArrayList<ToDo> toDosArrayList;
    private MyToDoRecyclerViewAdapter toDoRecyclerViewAdapter;
    private FloatingActionButton fab;
    private RecyclerView list;
    //FIREBASE1
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ToDoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ToDoFragment newInstance(int toDosArrayList) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO, toDosArrayList);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.
                SCREEN_ORIENTATION_PORTRAIT);
        if (getArguments() != null) {
            toDosArrayList = (ArrayList<ToDo>) getArguments().getSerializable(ARG_TODO);
        }

        //FIREBASE!!!1
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tasks").child("to_do");
        allToDoTasks();
    }

    //Adding tasks_todo in Recyclerview.
    public void allToDoTasks() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toDosArrayList.clear();

                for(DataSnapshot d: snapshot.getChildren()){
                    ToDo toDo= d.getValue(ToDo.class);
                    toDo.setId(d.getKey());//Setting Ids for delete and update operations

                    toDosArrayList.add(toDo);
                }
                toDoRecyclerViewAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_todo_list, container,
                false);

        fab = view.findViewById(R.id.fab);
        list= view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        /*tasks1= new ArrayList<>();
        Task tasks_first= new Task(toDosArrayList, null);
        tasks1.add(tasks_first);*/

        toDosArrayList = new ArrayList<>();
        /*ToDo t1 = new ToDo("1","task1","English",
                0.0, false);
        ToDo t2 = new ToDo("2","task2","Turkish",
                0.0, false);
        ToDo t3 = new ToDo("3","task2","German",
                0.0, false);
        toDosArrayList.add(t1);
        toDosArrayList.add(t2);
        toDosArrayList.add(t3);*/

        toDoRecyclerViewAdapter = new MyToDoRecyclerViewAdapter(toDosArrayList, getContext());

        list.setAdapter(toDoRecyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TaskDetailsActivity.class));
            }
        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            //recyclerView.setAdapter(new MyToDoRecyclerViewAdapter(tasks, mListener));
        }
        return view;
    }



    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ToDoFragment.OnTodoTaskListInteractionListener){
            mListener=(ToDoFragment.OnTodoTaskListInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+
                    " should implement OnTodoTaskListInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener=null;
    }*/

    public class OnTodoTaskListInteractionListener {
        void onTodoTaskSelected(Task task) {
            Log.d("TAG:", " onTodoTaskSelected");
        }
    }


}
