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
import java.util.Random;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class ChangeBookmarkGameStatusImpl implements ChangeBookmarkGameStatus, Interactor {

  private InteractorExecutor executor;
  private MainThread mainThread;
  private ChangeBookmarkCallback callback;

  @Inject ChangeBookmarkGameStatusImpl(InteractorExecutor executor, MainThread mainThread) {
    this.executor = executor;
    this.mainThread = mainThread;
  }

  @Override public void execute(ChangeBookmarkCallback callback) {
    this.callback = callback;
    this.executor.run(this);
  }

  @Override public void run() {
    mockInterestingTime();
    if (hasToFail()) {
      notifyBookmarkGameError();
    } else {
      notifyBookmarkStatusChanged();
    }
  }

  private void mockInterestingTime() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      //Empty
    }
  }

  private boolean hasToFail() {
    Random errorRandom = new Random();
    return errorRandom.nextInt(100) >= 95;
  }

  private void notifyBookmarkGameError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onBookmarkError();
      }
    });
  }

  private void notifyBookmarkStatusChanged() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onBookMarkStatusChanged();
      }
    });
  }
}
