package tr.edu.mu.ceng.mad.myproject.Adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tr.edu.mu.ceng.mad.myproject.Classes.Done;
import tr.edu.mu.ceng.mad.myproject.R;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDoneRecyclerViewAdapter extends
        RecyclerView.Adapter<MyDoneRecyclerViewAdapter.ViewHolder> {

    //ArrayList<Done>
    //private Bundle mValues;//done tasks list
    private ArrayList<Done> doneArrayList;
    private Context mContext;

    public MyDoneRecyclerViewAdapter( ArrayList<Done> doneArrayList,
                                      Context mContext) {
        //this.mValues = mValues;
        this.doneArrayList = doneArrayList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_done, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Done doneDatas = doneArrayList.get(position);
        holder.mItem = doneArrayList.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mDoneView.setText(doneDatas.getDescription());

        holder.note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return doneArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mDoneView;
        public final CardView note_card;
        public Object mItem;

        public ViewHolder(View view) {
            super(view);
            //mIdView = (TextView) view.findViewById(R.id.item_number);
            mDoneView = view.findViewById(R.id.taskDescription_done);
            note_card = view.findViewById(R.id.note_card);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDoneView.getText() + "'";
        }
    }
}
