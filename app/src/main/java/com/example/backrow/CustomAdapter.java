package com.example.backrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Question> implements View.OnClickListener{

    private List<Question> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtID;
        TextView txtQuestion;

    }

    public CustomAdapter(List<Question> data, Context context) {
        super(context, R.layout.question_list_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Question question=(Question)object;

        switch (v.getId())
        {
            case R.id.qid:
                Snackbar.make(v, "Release date " +question.getQuestion(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Question question= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.question_list_row, parent, false);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.qid);
            viewHolder.txtQuestion = (TextView) convertView.findViewById(R.id.txtQuestion1);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtQuestion.setText(question.getQuestion());
        viewHolder.txtID.setText(question.getId());

        // Return the completed view to render on screen
        return convertView;
    }
}