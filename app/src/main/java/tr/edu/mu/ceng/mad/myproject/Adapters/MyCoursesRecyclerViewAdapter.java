package tr.edu.mu.ceng.mad.myproject.Adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import tr.edu.mu.ceng.mad.myproject.Activities.CourseEditingActivity;
import tr.edu.mu.ceng.mad.myproject.Classes.Course;
import tr.edu.mu.ceng.mad.myproject.Fragments.CoursesFragment;
import tr.edu.mu.ceng.mad.myproject.R;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCoursesRecyclerViewAdapter extends
        RecyclerView.Adapter<MyCoursesRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Course> mValues; //dersler liste
    private CoursesFragment.OnCourseListInteractionListener mListener; //mContext

    public MyCoursesRecyclerViewAdapter(ArrayList<Course> mValues,
                                        CoursesFragment.OnCourseListInteractionListener
                                                mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_courses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Course course= mValues.get(position);
        //holder.mItem = mValues.get(position);
        holder.mCourseView.setText(course.getCourse_name());
        holder.mTeacherView.setText(course.getTeacher_name());

        holder.note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Context) mListener, CourseEditingActivity.class);
                intent.putExtra("object", course);
                ((Context) mListener).startActivity(intent);

                /*if(null != mListener){
                    mListener.onCourseSelected(holder.mItem);
                }*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public final TextView mCourseView;
        public final TextView mTeacherView;
        public final CardView note_card;
        public Course mItem;

        public ViewHolder(View view) {
            super(view);
            //mView = view;
            mCourseView = view.findViewById(R.id.courseName);
            mTeacherView = view.findViewById(R.id.teacherName);
            note_card = view.findViewById(R.id.note_card);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCourseView.getText()
                    + "'";
        }
    }
}
