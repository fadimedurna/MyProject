package tr.edu.mu.ceng.mad.myproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tr.edu.mu.ceng.mad.myproject.Classes.Course;
import tr.edu.mu.ceng.mad.myproject.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseDetailsActivity extends AppCompatActivity {
    private EditText textCourseName, textTeacherName;
    private Button buttonSave, buttonBack;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        textCourseName= findViewById(R.id.textCourseName);
        textTeacherName= findViewById(R.id.textTeacherName);
        buttonSave= findViewById(R.id.buttonSave);
        buttonBack= findViewById(R.id.buttonBack);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("courses");

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String course_name = textCourseName.getText().toString();
                String teacher_name = textTeacherName.getText().toString();

                Course course = new Course("",course_name,teacher_name);

                myRef.push().setValue(course);
                finish();
            }
        });

    }

    public void onCloseClick(View view) {
        finish();
    }



}