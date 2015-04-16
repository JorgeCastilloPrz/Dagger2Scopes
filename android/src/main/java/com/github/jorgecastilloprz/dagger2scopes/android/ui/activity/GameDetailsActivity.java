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
package com.github.jorgecastilloprz.dagger2scopes.android.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.ScrollView;
import butterknife.InjectView;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.android.Dagger2ScopesApp;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ActivityModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.GameDetailsComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.DaggerGameDetailsComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.animator.ToolbarAnimator;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.melnykov.fab.ObservableScrollView;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GameDetailsActivity extends BaseActivity
    implements ObservableScrollView.OnScrollChangedListener {

  public static final String GAME_EXTRA = "game_extra";
  private static final String SHARED_IMAGE_EXTRA = "sharedImage";
  private static final int MINIMUM_DELTA_TO_MOVE_TOOLBAR = 35;

  @Inject ToolbarAnimator toolbarAnimator;

  @InjectView(R.id.toolbar) Toolbar toolbar;

  private boolean isToolbarVisible = true;
  private GameDetailsComponent gameDetailsComponent;

  public GameDetailsComponent component() {
    if (gameDetailsComponent == null) {
      gameDetailsComponent = DaggerGameDetailsComponent.builder()
          .applicationComponent(((Dagger2ScopesApp) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return gameDetailsComponent;
  }

  public static Intent getLaunchIntent(final Context context, Game game) {
    if (game == null) {
      throwIllegalArgExceptionForNullGame();
    }
    Intent intent = new Intent(context, GameDetailsActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable(GAME_EXTRA, game);
    return intent.putExtras(bundle);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    injectViews();
    component().inject(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    excludeItemsFromTransitionIfLollipop();
  }

  @Override
  public void onScrollChanged(ScrollView sv, int currentX, int currentY, int oldX, int oldY) {
    int deltaY = currentY - oldY;
    if (Math.abs(deltaY) >= MINIMUM_DELTA_TO_MOVE_TOOLBAR) {
      if (deltaY < 0 && !isToolbarVisible) {
        toolbarAnimator.showSmoothToolbar(toolbar);
        isToolbarVisible = true;
      }

      if (deltaY > 0 && isToolbarVisible) {
        toolbarAnimator.hideSmoothToolbar(toolbar);
        isToolbarVisible = false;
      }
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void excludeItemsFromTransitionIfLollipop() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Slide transition = new Slide();
      transition.excludeTarget(android.R.id.statusBarBackground, true);
      transition.excludeTarget(R.id.toolbar, true);
      getWindow().setEnterTransition(transition);
      getWindow().setReturnTransition(transition);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public static void throwIllegalArgExceptionForNullGame() {
    throw new IllegalArgumentException("Game must not be null if you want to show its details!.");
  }
}
