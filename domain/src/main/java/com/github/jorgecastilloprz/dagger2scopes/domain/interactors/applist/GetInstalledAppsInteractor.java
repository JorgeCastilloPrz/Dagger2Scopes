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
package com.github.jorgecastilloprz.dagger2scopes.domain.interactors.applist;

import com.github.jorgecastilloprz.dagger2scopes.domain.executor.Interactor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.InteractorExecutor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.MainThread;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.AppDataModel;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.ApplicationRepository;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class GetInstalledAppsInteractor implements GetInstalledApps, Interactor {

  private InteractorExecutor executor;
  private MainThread mainThread;
  private ApplicationRepository repository;
  private GetInstalledAppsCallback callback;

  @Inject GetInstalledAppsInteractor(InteractorExecutor executor, MainThread mainThread,
      ApplicationRepository repository) {
    this.executor = executor;
    this.mainThread = mainThread;
    this.repository = repository;
  }

  @Override public void execute(GetInstalledAppsCallback callback) {
    this.callback = callback;
    this.executor.run(this);
  }

  @Override public void run() {
    List<AppDataModel> installedApps = repository.getAllInstalledApps();
    notifyAppsLoaded(installedApps);
  }

  private void notifyAppsLoaded(final List<AppDataModel> apps) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onAppsObtained(apps);
      }
    });
  }
}
