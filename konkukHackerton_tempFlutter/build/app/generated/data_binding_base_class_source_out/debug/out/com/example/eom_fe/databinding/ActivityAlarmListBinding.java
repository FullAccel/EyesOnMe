// Generated by view binder compiler. Do not edit!
package com.example.eom_fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eom_fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAlarmListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button addAlarmBtn;

  @NonNull
  public final EditText alarmText;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TimePicker timePicker;

  private ActivityAlarmListBinding(@NonNull LinearLayout rootView, @NonNull Button addAlarmBtn,
      @NonNull EditText alarmText, @NonNull RecyclerView recyclerView,
      @NonNull TimePicker timePicker) {
    this.rootView = rootView;
    this.addAlarmBtn = addAlarmBtn;
    this.alarmText = alarmText;
    this.recyclerView = recyclerView;
    this.timePicker = timePicker;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAlarmListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAlarmListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_alarm_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAlarmListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addAlarmBtn;
      Button addAlarmBtn = ViewBindings.findChildViewById(rootView, id);
      if (addAlarmBtn == null) {
        break missingId;
      }

      id = R.id.alarmText;
      EditText alarmText = ViewBindings.findChildViewById(rootView, id);
      if (alarmText == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.timePicker;
      TimePicker timePicker = ViewBindings.findChildViewById(rootView, id);
      if (timePicker == null) {
        break missingId;
      }

      return new ActivityAlarmListBinding((LinearLayout) rootView, addAlarmBtn, alarmText,
          recyclerView, timePicker);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}