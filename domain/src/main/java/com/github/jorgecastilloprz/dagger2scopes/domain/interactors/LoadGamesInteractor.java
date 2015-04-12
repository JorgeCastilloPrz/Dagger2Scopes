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

import com.github.jorgecastilloprz.dagger2scopes.domain.executor.Interactor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.InteractorExecutor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.MainThread;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.GameRepository;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class LoadGamesInteractor implements LoadGames, Interactor {

  private InteractorExecutor executor;
  private MainThread mainThread;
  private GameRepository repository;
  private LoadGamesCallback callback;

  @Inject LoadGamesInteractor(InteractorExecutor executor, MainThread mainThread,
      GameRepository repository) {
    this.executor = executor;
    this.mainThread = mainThread;
    this.repository = repository;
  }

  @Override public void execute(LoadGamesCallback callback) {
    this.callback = callback;
    this.executor.run(this);
  }

  @Override public void run() {
    mockLoadingTime();
    if (hasToFail()) {
      notifyLoadingGamesError();
    } else {
      List<Game> games = repository.getGames();
      notifyGamesLoaded(games);
    }
  }

  private void mockLoadingTime() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      //Empty
    }
  }

  private boolean hasToFail() {
    Random errorRandom = new Random();
    return errorRandom.nextInt(100) >= 95;
  }

  private void notifyLoadingGamesError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onLoadGamesError();
      }
    });
  }

  private void notifyGamesLoaded(final List<Game> games) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onGamesLoaded(games);
      }
    });
  }
}
