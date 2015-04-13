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
package com.github.jorgecastilloprz.dagger2scopes.presentation;

import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.ChangeBookmarkGameStatus;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GameDetailsPresenterImpl implements GameDetailsPresenter {

  private View view;
  private Game gameModel;
  private ChangeBookmarkGameStatus changeBookmarkGameStatus;

  @Inject GameDetailsPresenterImpl(ChangeBookmarkGameStatus changeBookmarkGameStatus) {
    this.changeBookmarkGameStatus = changeBookmarkGameStatus;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void setGameModel(Game game) {
    if (game == null) {
      throw new IllegalArgumentException("Game model must not be null!");
    }
    this.gameModel = game;
  }

  @Override public Game getGameModel() {
    return gameModel;
  }

  @Override public void restoreGameModel(Game game) {
    gameModel = game;
    initialize();
  }

  @Override public void initialize() {
    view.setHeaderImage(gameModel.getImageUrl());
    view.setTitle(gameModel.getName());
    view.setDateAndAuthor(gameModel.getReleaseDate(), gameModel.getAuthor());
    view.setDescription(gameModel.getDescription());
    if (gameModel.isBookmarked()) {
      view.markGameAsFavourite();
    }
  }

  @Override public void onUpNavigationClick() {
    view.closeDetails();
  }

  @Override public void onBookmarkButtonCick() {
    changeBookmarkGameStatus.execute(new ChangeBookmarkGameStatus.ChangeBookmarkCallback() {
      @Override public void onBookMarkStatusChanged() {
        if (gameModel.isBookmarked()) {
          view.unmarkGameAsFavourite();
          view.displayUnfavMessage();
          gameModel.setBookmarked(false);
        } else {
          view.markGameAsFavourite();
          view.displayFavouriteMessage();
          gameModel.setBookmarked(true);
        }
      }

      @Override public void onBookmarkError() {
        view.displayChangeBookmarkStatusError();
      }
    });
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }
}
