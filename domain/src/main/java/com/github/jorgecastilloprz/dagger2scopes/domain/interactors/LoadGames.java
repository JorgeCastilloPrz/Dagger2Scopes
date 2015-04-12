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
package com.github.jorgecastilloprz.dagger2scopes.domain.interactors;

import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import java.util.List;

/**
 * Used to decouple LoadGames details. LoadGames commands could not be interactors.
 * They must be declared as generic actions.
 *
 * @author Jorge Castillo Pérez
 */
public interface LoadGames {
  void execute(LoadGamesCallback callback);

  interface LoadGamesCallback {
    void onGamesLoaded(List<Game> gamesLoaded);

    void onLoadGamesError();
  }
}
