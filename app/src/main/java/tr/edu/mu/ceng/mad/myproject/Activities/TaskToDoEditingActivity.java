package tr.edu.mu.ceng.mad.myproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tr.edu.mu.ceng.mad.myproject.Classes.Done;
import tr.edu.mu.ceng.mad.myproject.Classes.ToDo;
import tr.edu.mu.ceng.mad.myproject.Fragments.DoneFragment;
import tr.edu.mu.ceng.mad.myproject.Fragments.ToDoFragment;
import tr.edu.mu.ceng.mad.myproject.MyService;
import tr.edu.mu.ceng.mad.myproject.R;

public class TaskToDoEditingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextDescription, editTextTime;
    private TextView textView_CourseName;
    private Spinner spinner;
    private Switch switch_button;
    //3.FIREBASE: WE HAVE TO PICK ToDo OBJECT THAT WAS IN THE TASKDETAILACTIVITY.JAVA
    // (We looked at the Adapter class for this)
    private Done done;
    private ToDo toDo;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    final DoneFragment doneFragment = new DoneFragment();
    final ToDoFragment toDoFragment = new ToDoFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_to_do_editing);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        final ToDoFragment toDoFragment = new ToDoFragment();

        toolbar = findViewById(R.id.toolbar2);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextTime = findViewById(R.id.editTextTime);
        textView_CourseName = findViewById(R.id.textView_CourseName);
        spinner = findViewById(R.id.spinner_courses);
        switch_button = findViewById(R.id.switch1);

        toolbar.setTitle("Task Editing");
        setSupportActionBar(toolbar);

        //3.FIREBASE: WE HAVE TO PICK ToDo OBJECT THAT WAS IN THE TASKDETAILACTIVITY.JAVA
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tasks").child("to_do");

        toDo = (ToDo) getIntent().getSerializableExtra("object");
        //3.FIREBASE:for update
        editTextDescription.setText(toDo.getDescription());
        textView_CourseName.setText(toDo.getCourse_name());
        editTextTime.setText(String.valueOf(toDo.getDeadline()));
        switch_button.setTextOn(String.valueOf(toDo.isDone()));

        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    startService(new Intent
                            (TaskToDoEditingActivity.this, MyService.class));
                    Log.e("Switch Button", String.valueOf("ON"));
                    toDo.setDone(true);
                }else{
                    stopService(new Intent
                            (TaskToDoEditingActivity.this,MyService.class));
                    Log.e("Switch Button", String.valueOf("OFF"));
                    toDo.setDone(false);

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_delete:
                Snackbar.make(toolbar,"Delete it?", Snackbar.LENGTH_LONG).
                        setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //IMPORTANT!!
                                myRef.child(toDo.getId()).removeValue();
                                finish();
                            }
                        }).show();
                return true;
            case R.id.action_update:



                Boolean switchState = switch_button.isChecked();
                Log.e("Switch State", String.valueOf(switchState));

                //3.FIREBASE
                String description = editTextDescription.getText().toString().trim();
                String course_name = textView_CourseName.getText().toString().trim();
                String deadline = editTextTime.getText().toString().trim();
                String done = switch_button.getTextOn().toString().trim();

                Map<String,Object> datas= new HashMap<>();
                datas.put("description", description);
                datas.put("course_name",course_name);
                datas.put("deadline", Double.parseDouble(deadline));
                datas.put("done", Boolean.parseBoolean(done));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle b1= new Bundle();

                if(switchState==true){

                    b1.putSerializable("datas",doneFragment);
                    Log.e("datasDONE: ", String.valueOf(datas));
                    myRef.child(toDo.getId()).updateChildren(datas);
                    //doneFragment.allDoneTasks();
                    transaction.commit();
                }else{
                    b1.putSerializable("datas", toDoFragment);
                    Log.e("datasTODO: ", String.valueOf(datas));
                    myRef.child(toDo.getId()).updateChildren(datas);
                    transaction.commit();
                }

                //IMPORTANT!!

                finish();
                return true;
            default:
                return false;
        }
    }

}
