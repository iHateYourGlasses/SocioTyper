package ru.npotpz.sociotyper.models;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import ru.npotpz.sociotyper.R;

/**
 * Created by Art on 26.01.2017.
 */

public class ReininAdapter extends ArrayAdapter<ReininTypeModel> {


    public ReininAdapter(Context context, ArrayList<ReininTypeModel> ReininTypes) {
        super(context, 0, ReininTypes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context ctx;
        // Get the data item for this position
        ReininTypeModel typesData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_reinin_type_switch, parent, false);
        }

        final Button typeOneView = (Button) convertView.findViewById(R.id.bTypeOne);
        typeOneView.setTag(position);
        final Button typeTwoView = (Button) convertView.findViewById(R.id.bTypeTwo);
        typeTwoView.setTag(position);
        final CheckBox isSwitchActiveView = (CheckBox) convertView.findViewById(R.id.cbIsTypeActive);
        isSwitchActiveView.setTag(position);

        // Populate the data into the template view using the data object
        typeOneView.setText(typesData.typeOne);
        typeTwoView.setText(typesData.typeTwo);
        if(typesData.isSwitchActive){
            isSwitchActiveView.setChecked(true);
            if(typesData.isFirstTypeActive){
                typeOneView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonActive));
                typeTwoView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonPassive));
            }else{
                typeOneView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonPassive));
                typeTwoView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonActive));
            }
        }else{
            isSwitchActiveView.setChecked(false);
            typeTwoView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonPassive));
            typeOneView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorReininButtonPassive));
        }

        typeOneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                ReininTypeModel curSwitchData = getItem(position);
                curSwitchData.isFirstTypeActive = !curSwitchData.isFirstTypeActive;
                curSwitchData.isSwitchActive = true;

                typeOneView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorReininButtonActive));
                typeTwoView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorReininButtonPassive));
                isSwitchActiveView.setChecked(true);

            }
        });

        typeTwoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                ReininTypeModel curSwitchData = getItem(position);
                curSwitchData.isFirstTypeActive = !curSwitchData.isFirstTypeActive;
                curSwitchData.isSwitchActive = true;

                typeOneView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorReininButtonPassive));
                typeTwoView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorReininButtonActive));
                isSwitchActiveView.setChecked(true);

            }
        });

        isSwitchActiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                ReininTypeModel curSwitchData = getItem(position);
                curSwitchData.isSwitchActive = !curSwitchData.isSwitchActive;
                isSwitchActiveView.setChecked(curSwitchData.isSwitchActive);

            }
        });

        // Return the completed view to render on screenctx
        return convertView;
    }
}