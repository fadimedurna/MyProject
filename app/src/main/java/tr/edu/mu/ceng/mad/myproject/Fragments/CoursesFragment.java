package tr.edu.mu.ceng.mad.myproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.Objects;

import tr.edu.mu.ceng.mad.myproject.Activities.CourseDetailsActivity;
import tr.edu.mu.ceng.mad.myproject.Adapters.MyCoursesRecyclerViewAdapter;
import tr.edu.mu.ceng.mad.myproject.Classes.Course;
import tr.edu.mu.ceng.mad.myproject.R;

/**
 * A fragment representing a list of Items.
 */
public class CoursesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COURSE = "course";
    private OnCourseListInteractionListener mListener;
    private ArrayList<Course> courses;

    private FloatingActionButton fab;
    private RecyclerView list;
    public ArrayList<Course> courseArrayList;
    private MyCoursesRecyclerViewAdapter coursesRecyclerViewAdapter;
    //FIREBASE
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CoursesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CoursesFragment newInstance(ArrayList<Course> courses) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COURSE, courses);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.
                SCREEN_ORIENTATION_PORTRAIT);
        if (getArguments() != null) {
            courses = (ArrayList<Course>)getArguments().getSerializable(ARG_COURSE);
        }

        //FIREBASE!!!1
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("courses");
        allCourses();


    }
    //Adding courses in Recyclerview.
    public void allCourses() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseArrayList.clear();

                for(DataSnapshot d: snapshot.getChildren()){
                    Course course= d.getValue(Course.class);
                    course.setId(d.getKey());//Getting Ids for delete and update operations

                    courseArrayList.add(course);
                }
                coursesRecyclerViewAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_courses_list, container,
                false);

        fab = view.findViewById(R.id.fab);
        list= view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        courseArrayList = new ArrayList<>();

        /*Course c1 = new Course("0","English","Ali Yılmaz");
        Course c2 = new Course("1","Mobile Application","Özgür Kılıç");

        courseArrayList.add(c1);
        courseArrayList.add(c2);*/

        coursesRecyclerViewAdapter = new MyCoursesRecyclerViewAdapter(courseArrayList,
                (OnCourseListInteractionListener) getActivity());

        list.setAdapter(coursesRecyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CourseDetailsActivity.class));
            }
        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new MyCoursesRecyclerViewAdapter
                    (courses, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnCourseListInteractionListener){
            mListener=(OnCourseListInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+
                    " should implement OnCourseListInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }

    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivity(new Intent(getContext(), CourseDetailsActivity.class));
                break;
        }
    }
*/

    /*public interface OnCourseListInteractionListener{
        void onCourseSelected(Course course);
    }*/

    public interface OnCourseListInteractionListener {
        @SuppressWarnings({"FieldNever", "unused"})
        void onCourseSelected(Course course);
    }


}