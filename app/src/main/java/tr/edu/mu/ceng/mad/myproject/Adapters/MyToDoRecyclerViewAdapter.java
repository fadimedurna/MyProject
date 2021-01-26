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

import tr.edu.mu.ceng.mad.myproject.Activities.TaskToDoEditingActivity;
import tr.edu.mu.ceng.mad.myproject.Classes.ToDo;
import tr.edu.mu.ceng.mad.myproject.R;

/*
 * TODO: Replace the implementation with code for your data type.
 */
public class MyToDoRecyclerViewAdapter extends
        RecyclerView.Adapter<MyToDoRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ToDo> mValues; //to-do ödevler liste
    //private ToDoFragment.OnTodoTaskListInteractionListener mListener;//mContext
    private Context mContext;


    public MyToDoRecyclerViewAdapter(ArrayList<ToDo> mValues, Context mContext) {
        this.mValues = mValues;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) //parent.getContext()=mContext
                .inflate(R.layout.fragment_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ToDo toDoDatas= mValues.get(position);
        //holder.mItem = mValues.get(position);
        holder.mTodoView.setText(toDoDatas.getDescription());

        holder.note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TaskToDoEditingActivity.class);
                intent.putExtra("object", toDoDatas);
                (mContext).startActivity(intent);

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
        public final TextView mTodoView;
        public final CardView note_card;
        public ToDo mItem;

        public ViewHolder(View view) {
            super(view);
            //mView = view;
            mTodoView = view.findViewById(R.id.taskDescription_todo);
            note_card = view.findViewById(R.id.note_card);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mTodoView.getText()
                    + "'";
        }
    }
}