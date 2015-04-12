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

import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.LoadGames;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.domain.navigation.Navigator;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GameListPresenterImpl implements GameListPresenter {

  private View view;
  private List<Game> currentGamesLoaded;
  private Navigator navigator;
  private LoadGames loadGames;

  @Inject GameListPresenterImpl(Navigator navigator, LoadGames loadGames) {
    this.navigator = navigator;
    this.loadGames = loadGames;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void initialize() {
    obtainGames();
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  /**
   * All tasks for ObtainGames context will be dispatched in order
   */
  private void obtainGames() {
    loadGames.execute(new LoadGames.LoadGamesCallback() {
      @Override public void onGamesLoaded(List<Game> gamesLoaded) {
        view.drawGames(gamesLoaded);
        currentGamesLoaded = gamesLoaded;
      }

      @Override public void onLoadGamesError() {
        view.displayLoadGamesError();
      }
    });
  }

  @Override public void onGameClicked(Game game) {
    navigator.displayGameDetails(game);
  }

  @Override public List<Game> getCurrentGamesLoaded() {
    return currentGamesLoaded;
  }

  @Override public void restoreLoadedGames(List<Game> games) {
    currentGamesLoaded = games;
    view.drawGames(games);
  }
}
