package tr.edu.mu.ceng.mad.myproject.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tr.edu.mu.ceng.mad.myproject.Classes.Course;
import tr.edu.mu.ceng.mad.myproject.Fragments.CoursesFragment;
import tr.edu.mu.ceng.mad.myproject.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CourseEditingActivity extends AppCompatActivity implements
        CoursesFragment.OnCourseListInteractionListener{

    private Toolbar toolbar;
    private EditText updatedTextCourse, updatedTextTeacher;
    //course nesnesi yakalanır
    private Course course;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editing);

        toolbar = findViewById(R.id.toolbar);
        updatedTextCourse = findViewById(R.id.updatedTextCourseName);
        updatedTextTeacher = findViewById(R.id.updatedTextTeacherName);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("courses");

        course = (Course)getIntent().getSerializableExtra("object"); //ÖNEMLİ!!
        updatedTextCourse.setText(course.getCourse_name());
        updatedTextTeacher.setText(course.getTeacher_name());

        toolbar.setTitle("Course Editing");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                Snackbar.make(toolbar, "Delete it?", Snackbar.LENGTH_LONG).
                        setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myRef.child(course.getId()).removeValue();//DELETE!!!

                                finish();
                                //new Intent(getContext(), CourseDetailsActivity.class)
                            }
                        }).show();
                return true;
            case R.id.action_update:

                String course_name = updatedTextCourse.getText().toString().trim();
                String teacher_name = updatedTextTeacher.getText().toString().trim();

                Map<String, Object> datas = new HashMap<>();
                datas.put("course_name", course_name);
                datas.put("teacher_name", teacher_name);

                myRef.child(course.getId()).updateChildren(datas); //EDITING-UPDATING!!!!(id'ye göre edit işlemi yapılır)

                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onCourseSelected(Course course) {
        Log.d("TAG:", " onCourseSelected");
    }
}