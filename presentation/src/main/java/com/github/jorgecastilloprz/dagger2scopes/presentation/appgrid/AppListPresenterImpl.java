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
package com.github.jorgecastilloprz.dagger2scopes.presentation.appgrid;

import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.EventBus;
import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.events.AnimateOnBackPressedEvent;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.AppDataModel;
import com.github.jorgecastilloprz.dagger2scopes.presentation.navigation.Navigator;
import com.squareup.otto.Subscribe;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class AppListPresenterImpl implements AppListPresenter {

  private EventBus bus;
  private View view;
  private Navigator navigator;
  private List<AppDataModel> installedApps;

  @Inject AppListPresenterImpl(EventBus bus, Navigator navigator) {
    this.bus = bus;
    this.navigator = navigator;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throwViewNotNullException();
    }
    this.view = view;
  }

  @Override public void setInstalledApps(List<AppDataModel> apps) {
    installedApps = apps;
  }

  @Override public void initialize() {
    if (view == null) {
      throwViewNotNullException();
    }
    view.drawApplications(installedApps);
  }

  @Override public void onResume() {
    bus.register(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  @Override public void onContinueButtonClick(List<AppDataModel> selectedApps) {
    if (selectedApps.size() == 0) {
      view.displayNoSelectedAppsWarning();
    } else {
      navigator.moveToConfigureActionsScreen(selectedApps);
    }
  }

  @Subscribe public void animateOnBackEvent(AnimateOnBackPressedEvent event) {
    view.displayBackwardsRevealIfPossible();
  }

  private void throwViewNotNullException() {
    throw new IllegalArgumentException("View must not be null!");
  }
}
