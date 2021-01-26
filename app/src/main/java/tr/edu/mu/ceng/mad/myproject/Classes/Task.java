package tr.edu.mu.ceng.mad.myproject.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Task implements Serializable {

    private ToDo task_todo;
    private Done task_done;

    public Task(ArrayList<ToDo> toDosArrayList, Object task_done) {
    }

    public Task(ToDo task_todo, Done task_done) {
        this.task_todo = task_todo;
        this.task_done = task_done;
    }

    public ToDo getTask_todo() {
        return task_todo;
    }

    public void setTask_todo(ToDo task_todo) {
        this.task_todo = task_todo;
    }

    public Done getTask_done() {
        return task_done;
    }

    public void setTask_done(Done task_done) {
        this.task_done = task_done;
    }
}

