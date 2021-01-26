package tr.edu.mu.ceng.mad.myproject.Classes;

import android.net.Uri;

import java.io.Serializable;

public class Course implements Serializable {
    private String id;
    private String course_name;
    private String teacher_name;


    public Course() {
    }

    public Course(String id,String course_name, String teacher_name) {
        this.course_name = course_name;
        this.teacher_name = teacher_name;
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

