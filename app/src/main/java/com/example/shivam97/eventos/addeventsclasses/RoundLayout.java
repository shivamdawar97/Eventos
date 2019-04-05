package com.example.shivam97.eventos.addeventsclasses;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shivam97.eventos.R;

public class RoundLayout extends LinearLayout {

    private TextView roundNo;
    private LinearLayout gridName;
    boolean collapsed=false;
    private String index;
    private EditText name,date,venue,time,desc;
    private Animation slidDown;

    public RoundLayout(Context context,String i) {
        super(context);
        index=i;
        inflateLayout(context);

    }

    public RoundLayout(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public RoundLayout(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    public RoundLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        slidDown=AnimationUtils.loadAnimation(context,R.anim.slide_down);

        inflate(context, R.layout.event_round_layout,this).startAnimation(slidDown);

        roundNo =getRootView().findViewById(R.id.round_num);
        roundNo.setText(String.format("Round %s", index));
        gridName=getRootView().findViewById(R.id.grid_layout);
        date=getRootView().findViewById(R.id.round_dates);
        venue=getRootView().findViewById(R.id.round_venue);
        time=getRootView().findViewById(R.id.round_time);
        desc=getRootView().findViewById(R.id.round_desc);
        name=getRootView().findViewById(R.id.round_name);

        collapse(gridName);
        collapsed=true;
        roundNo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collapsed)
                    expand(gridName);
                else
                    collapse(gridName);
            }
        });
    }

    public  void expand(final View v) {
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        collapsed=false;
    }

    public  void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        collapsed=true;
    }


    public String getDate() {
        return date.getText().toString();
    }
    public String getName() {
        return name.getText().toString();
    }
    public String getVenue() {
        return venue.getText().toString();
    }
    public String getTime() {
        return time.getText().toString();
    }
    public String getDesc() {
        return desc.getText().toString();
    }

}
