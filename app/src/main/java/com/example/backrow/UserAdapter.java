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

public class UserAdapter extends ArrayAdapter<User> implements View.OnClickListener {

    private List<User> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtID;
        TextView txtUser;

    }

    public UserAdapter(List<User> data, Context context) {
        super(context, R.layout.activity_user__list_row, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        User user = (User) object;

        switch (v.getId()) {
            case R.id.userid:
                Snackbar.make(v, "Release date " + user.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        UserAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new UserAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.question_list_row, parent, false);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.qid);
            viewHolder.txtUser = (TextView) convertView.findViewById(R.id.txtQuestion1);


            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (UserAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtUser.setText(user.getName());
        viewHolder.txtID.setText(user.getUid());

        // Return the completed view to render on screen
        return convertView;
    }
}