package tr.edu.mu.ceng.mad.myproject.Classes;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Done implements Serializable {

    private String id_done;
    private String description;
    private static boolean done;
    private double deadline; //saat
    private String course_name; //LINK


    public Done() {
    }

    public Done(String id_done, String description, String course_name,
                double deadline, boolean done) {
        this.id_done = id_done;
        this.deadline = deadline;
        this.course_name = course_name;
        this.description = description;
        this.done = done;
    }


    public String getId_done() {
        return id_done;
    }

    public static void setId_done(String id_done) { id_done = id_done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public double getDeadline() {
        return deadline;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
