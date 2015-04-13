/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.dagger2scopes.android.ui.animator;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import javax.inject.Inject;

/**
 * Concrete animation logic used to show / hide toolbars.
 *
 * @author Jorge Castillo Pérez
 */
public class ToolbarAnimatorImpl implements ToolbarAnimator {

  private final int TOOLBAR_SMOOTH_ANIM_DURATION = 150;
  private final int TOOLBAR_ANIMATIONS_DELAY = 500;

  private ToolbarAnimationListener animationListener;

  @Inject ToolbarAnimatorImpl() {
  }

  @Override public void attachToolbarAnimationListener(ToolbarAnimationListener listener) {
    this.animationListener = listener;
  }

  /**
   * Instant toolbar hide to top animation
   */
  public void hideInstantToolbar(final View toolbarView) {
    toolbarView.post(new Runnable() {
      @Override public void run() {
        ValueAnimator toolbarShowAnim =
            ObjectAnimator.ofFloat(toolbarView, "translationY", -toolbarView.getBottom());
        toolbarShowAnim.setDuration(0);
        toolbarShowAnim.start();
      }
    });
  }

  /**
   * Smooth toolbar display from top animation
   */
  @Override public void showDelayedSmoothToolbar(final View toolbarView) {
    toolbarView.postDelayed(new Runnable() {
      @Override public void run() {
        showSmoothToolbar(toolbarView);
      }
    }, TOOLBAR_ANIMATIONS_DELAY);
  }

  public void showSmoothToolbar(View toolbarView) {
    ValueAnimator toolbarShowAnim =
        ObjectAnimator.ofFloat(toolbarView, "translationY", -toolbarView.getBottom(), 0);
    toolbarShowAnim.setInterpolator(new AccelerateInterpolator());
    toolbarShowAnim.setDuration(TOOLBAR_SMOOTH_ANIM_DURATION);
    toolbarShowAnim.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
      }

      @Override public void onAnimationEnd(Animator animation) {
        if (animationListener != null) {
          animationListener.onToolbarTotallyDisplayed();
        }
      }

      @Override public void onAnimationCancel(Animator animation) {
      }

      @Override public void onAnimationRepeat(Animator animation) {
      }
    });
    toolbarShowAnim.start();
  }

  @Override public void hideSmoothToolbar(View toolbarView) {
    ValueAnimator toolbarShowAnim =
        ObjectAnimator.ofFloat(toolbarView, "translationY", -toolbarView.getBottom());
    toolbarShowAnim.setInterpolator(new AccelerateInterpolator());
    toolbarShowAnim.setDuration(150);
    toolbarShowAnim.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
      }

      @Override public void onAnimationEnd(Animator animation) {
        if (animationListener != null) {
          animationListener.onToolbarTotallyHidden();
        }
      }

      @Override public void onAnimationCancel(Animator animation) {
      }

      @Override public void onAnimationRepeat(Animator animation) {
      }
    });
    toolbarShowAnim.start();
  }
}
