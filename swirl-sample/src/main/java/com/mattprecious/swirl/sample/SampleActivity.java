package com.mattprecious.swirl.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mattprecious.swirl.SwirlView;

public class SampleActivity extends Activity
    implements RadioGroup.OnCheckedChangeListener {
  @BindView(R.id.swirl) SwirlView swirlView;
  @BindView(R.id.state) RadioGroup stateView;
  @BindView(R.id.animate) CompoundButton animateView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ButterKnife.bind(this);

    stateView.setOnCheckedChangeListener(this);
  }

  @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
    switch (checkedId) {
      case R.id.off:
        swirlView.setState(SwirlView.State.OFF, animateView.isChecked());
        break;
      case R.id.on:
        swirlView.setState(SwirlView.State.ON, animateView.isChecked());
        break;
      case R.id.error:
        swirlView.setState(SwirlView.State.ERROR, animateView.isChecked());
        break;
      default:
        throw new IllegalArgumentException("Unexpected checkedId: " + checkedId);
    }
  }
}
