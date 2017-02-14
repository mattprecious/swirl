package com.mattprecious.swirl;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.M)
public final class SwirlView extends ImageView {

  public enum State {
    OFF,
    ON,
    ERROR,
  }

  private State state = State.OFF;

  public SwirlView(Context context) {
    this(context, null);
  }

  public SwirlView(Context context, AttributeSet attrs) {
    super(context, attrs);

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      throw new AssertionError("API 23 required.");
    }

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.swirl_Swirl);
    int state = a.getInteger(R.styleable.swirl_Swirl_swirl_state, -1);
    if (state != -1) {
      setState(State.values()[state], false);
    }
    a.recycle();
  }

  public void setState(State state) {
    setState(state, true);
  }

  public void setState(State state, boolean animate) {
    if (state == this.state) return;

    @DrawableRes int resId = getDrawable(this.state, state, animate);
    if (resId == 0) {
      setImageResource(resId);
    } else {
      Drawable icon = getContext().getDrawable(resId);
      setImageDrawable(icon);

      if (icon instanceof AnimatedVectorDrawable) {
        ((AnimatedVectorDrawable) icon).start();
      }
    }

    this.state = state;
  }

  @DrawableRes private static int getDrawable(State currentState, State newState, boolean animate) {
    switch (newState) {
      case OFF:
        if (animate) {
          if (currentState == State.ON) {
            return R.drawable.swirl_draw_off_animation;
          } else if (currentState == State.ERROR) {
            return R.drawable.swirl_error_off_animation;
          }
        }

        return 0;
      case ON:
        if (animate) {
          if (currentState == State.OFF) {
            return R.drawable.swirl_draw_on_animation;
          } else if (currentState == State.ERROR) {
            return R.drawable.swirl_error_state_to_fp_animation;
          }
        }

        return R.drawable.swirl_fingerprint;
      case ERROR:
        if (animate) {
          if (currentState == State.ON) {
            return R.drawable.swirl_fp_to_error_state_animation;
          } else if (currentState == State.OFF) {
            return R.drawable.swirl_error_on_animation;
          }
        }

        return R.drawable.swirl_error;
      default:
        throw new IllegalArgumentException("Unknown state: " + newState);
    }
  }
}
