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
package com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.GameDetailsActivity;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameDetailsPresenter;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GameDetailsFragment extends BaseFragment implements GameDetailsPresenter.View {

  @Inject GameDetailsPresenter presenter;

  @InjectView(R.id.backgroundImage) ImageView bgImage;
  @InjectView(R.id.detailsFab) FloatingActionButton detailsFab;
  @InjectView(R.id.dateAndAuthor) TextView dateAndAuthor;
  @InjectView(R.id.description) TextView descriptionText;
  @InjectView(R.id.scroll) ObservableScrollView scrollView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_game_details, container, false);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((GameDetailsActivity) getActivity()).component().inject(this);
    initScrollViewListener();

    Bundle extra = getActivity().getIntent().getExtras();
    if (extra == null) {
      GameDetailsActivity.throwIllegalArgExceptionForNullGame();
    }

    Game game = (Game) extra.getSerializable(GameDetailsActivity.GAME_EXTRA);
    presenter.setView(this);
    presenter.setGameModel(game);
    presenter.initialize();
  }

  @Override public void onViewStateRestored(Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    if (savedInstanceState != null) {
      final Game game = (Game) savedInstanceState.getSerializable(GameDetailsActivity.GAME_EXTRA);
      presenter.restoreGameModel(game);
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(GameDetailsActivity.GAME_EXTRA, presenter.getGameModel());
  }

  private void initScrollViewListener() {
    scrollView.setOnScrollChangedListener((GameDetailsActivity) getActivity());
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
    ((GameDetailsActivity) getActivity()).getSupportActionBar().setTitle(title);
  }

  @Override public void setDateAndAuthor(String date, String author) {
    dateAndAuthor.setText(author + " - " + date);
  }

  @Override public void setDescription(String description) {
    descriptionText.setText(description);
  }

  @Override public void displayConnectionError() {
    Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_LONG).show();
  }

  @OnClick(R.id.detailsFab) public void onFavouriteButtonClick() {
    presenter.onBookmarkButtonCick();
  }

  @Override public void markGameAsFavourite() {
    detailsFab.setImageResource(R.drawable.ic_fav_white);
  }

  @Override public void displayFavouriteMessage() {
    Toast.makeText(getActivity(), R.string.game_fav, Toast.LENGTH_LONG).show();
  }

  @Override public void unmarkGameAsFavourite() {
    detailsFab.setImageResource(R.drawable.ic_fav);
  }

  @Override public void displayUnfavMessage() {
    Toast.makeText(getActivity(), R.string.game_unfav, Toast.LENGTH_LONG).show();
  }

  @Override public void displayChangeBookmarkStatusError() {
    Toast.makeText(getActivity(), R.string.bookmark_error, Toast.LENGTH_LONG).show();
  }

  @Override public void closeDetails() {
    getActivity().finish();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onResume();
  }
}
