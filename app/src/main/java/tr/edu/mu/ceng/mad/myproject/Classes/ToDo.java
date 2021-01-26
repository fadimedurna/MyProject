package tr.edu.mu.ceng.mad.myproject.Classes;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class ToDo implements Serializable{

    private String id;
    private String description;
    private String course_name; //LINK
    private double deadline; //saat
    private boolean done;

    public ToDo() {
    }

    public ToDo(String id, String description, String course_name,
                double deadline, boolean done) {
        this.id = id;
        this.description = description;
        this.course_name = course_name;
        this.deadline = deadline;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public double getDeadline() {
        return deadline;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
