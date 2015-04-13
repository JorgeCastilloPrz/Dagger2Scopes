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
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.android.Dagger2ScopesApp;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ActivityModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.GameDetailsComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.DaggerGameDetailsComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.animator.ToolbarAnimator;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameDetailsPresenter;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GameDetailsActivity extends BaseActivity
    implements GameDetailsPresenter.View, ObservableScrollView.OnScrollChangedListener {

  private static final String GAME_EXTRA = "game_extra";
  private static final String SHARED_IMAGE_EXTRA = "sharedImage";
  private static final int MINIMUM_DELTA_TO_MOVE_TOOLBAR = 35;

  @Inject GameDetailsPresenter presenter;
  @Inject ToolbarAnimator toolbarAnimator;

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.backgroundImage) ImageView bgImage;
  @InjectView(R.id.detailsFab) FloatingActionButton detailsFab;
  @InjectView(R.id.dateAndAuthor) TextView dateAndAuthor;
  @InjectView(R.id.description) TextView descriptionText;
  @InjectView(R.id.scroll) ObservableScrollView scrollView;

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

    Bundle extras = getIntent().getExtras();
    if (extras == null) {
      throwIllegalArgExceptionForNullGame();
    }

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    excludeItemsFromTransitionIfLollipop();
    setImageTransition();
    initScrollViewListener();

    Game game = (Game) extras.getSerializable(GAME_EXTRA);
    presenter.setView(this);
    presenter.setGameModel(game);
    presenter.initialize();
  }

  private void initScrollViewListener() {
    scrollView.setOnScrollChangedListener(this);
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

  private static void throwIllegalArgExceptionForNullGame() {
    throw new IllegalArgumentException("Game must not be null if you want to show its details!.");
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void excludeItemsFromTransitionIfLollipop() {
    Slide transition = new Slide();
    transition.excludeTarget(android.R.id.statusBarBackground, true);
    transition.excludeTarget(R.id.toolbar, true);
    getWindow().setEnterTransition(transition);
    getWindow().setReturnTransition(transition);
  }

  private void setImageTransition() {
    final ImageView image = (ImageView) findViewById(R.id.backgroundImage);
    ViewCompat.setTransitionName(image, SHARED_IMAGE_EXTRA);
  }

  @Override public void setHeaderImage(String imageUrl) {
    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.lucasarts)
        .error(R.drawable.lucasarts)
        .into(bgImage);
  }

  @Override public void setTitle(String title) {
    getSupportActionBar().setTitle(title);
  }

  @Override public void setDateAndAuthor(String date, String author) {
    dateAndAuthor.setText(author + " - " + date);
  }

  @Override public void setDescription(String description) {
    descriptionText.setText(description);
  }

  @Override public void displayConnectionError() {
    Toast.makeText(this, R.string.connection_error, Toast.LENGTH_LONG).show();
  }

  @OnClick(R.id.detailsFab) public void onFavouriteButtonClick() {
    presenter.onBookmarkButtonCick();
  }

  @Override public void markGameAsFavourite() {
    detailsFab.setImageResource(R.drawable.ic_fav_white);
  }

  @Override public void displayFavouriteMessage() {
    Toast.makeText(this, R.string.game_fav, Toast.LENGTH_LONG).show();
  }

  @Override public void unmarkGameAsFavourite() {
    detailsFab.setImageResource(R.drawable.ic_fav);
  }

  @Override public void displayUnfavMessage() {
    Toast.makeText(this, R.string.game_unfav, Toast.LENGTH_LONG).show();
  }

  @Override public void displayChangeBookmarkStatusError() {
    Toast.makeText(this, R.string.bookmark_error, Toast.LENGTH_LONG).show();
  }

  @Override public void closeDetails() {
    finish();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        presenter.onUpNavigationClick();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(GAME_EXTRA, presenter.getGameModel());
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    final Game game = (Game) savedInstanceState.getSerializable(GAME_EXTRA);
    presenter.restoreGameModel(game);
  }
}
