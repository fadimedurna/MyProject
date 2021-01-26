package tr.edu.mu.ceng.mad.myproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tr.edu.mu.ceng.mad.myproject.Classes.ToDo;
import tr.edu.mu.ceng.mad.myproject.R;

public class TaskDetailsActivity extends AppCompatActivity {

    private EditText editTextDescription, editTextTime;
    private TextView textView_CourseName;
    private Spinner spinner;
    private Button buttonSave;
    //private ToDo toDo;
    //private RadioButton yes, no;
    int i=0;

    //FIREBASE
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        editTextDescription= findViewById(R.id.editTextDescription);
        editTextTime= findViewById(R.id.editTextTime);
        textView_CourseName= findViewById(R.id.textView_CourseName);
        spinner = findViewById(R.id.spinner_courses);
        buttonSave = findViewById(R.id.buttonSave);

        //toDo = new ToDo();
        /*yes = findViewById(R.id.radioButtonYes);
        no = findViewById(R.id.radioButtonNo);*/

        //FIREBASE!!!2
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tasks").child("to_do");//


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SAVING DATAS(FIREBASE)!!!2
                String description = editTextDescription.getText().toString().trim();
                String course_name = textView_CourseName.getText().toString().trim();
                String deadline = editTextTime.getText().toString().trim();
                /*String y = yes.getText().toString();
                String n = no.getText().toString();


                if (yes.isChecked()) {
                    toDo.setYes(y);
                    myRef.child(String.valueOf(i + 1)).setValue(toDo); //
                }

                if(no.isChecked()){
                    toDo.setNo(n);
                    myRef.child(String.valueOf(i + 1)).setValue(toDo);
                }
*/
                ToDo toDo = new ToDo("",description, course_name,Double.parseDouble(deadline),
                        Boolean.parseBoolean(String.valueOf(false))); //,y,n

                myRef.push().setValue(toDo);
                finish();

            }
        });

    }

    public void onCloseClick(View view) {
        finish();
    }
}