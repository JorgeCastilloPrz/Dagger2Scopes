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

import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;

/**
 * @author Jorge Castillo Pérez
 */
public interface GameDetailsPresenter extends Presenter {

  void setView(View view);

  void setGameModel(Game game);

  Game getGameModel();

  void restoreGameModel(Game game);

  void onUpNavigationClick();

  void onBookmarkButtonCick();

  interface View {

    void setHeaderImage(String imageUrl);

    void setTitle(String title);

    void setDateAndAuthor(String date, String author);

    void setDescription(String description);

    void displayConnectionError();

    void markGameAsFavourite();

    void displayFavouriteMessage();

    void unmarkGameAsFavourite();

    void displayUnfavMessage();

    void displayChangeBookmarkStatusError();

    void closeDetails();
  }
}
