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
package com.github.jorgecastilloprz.dagger2scopes.presentation.filterlist;

import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.EventBus;
import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.events.AnimateOnBackPressedEvent;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.applist.GetInstalledApps;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.AppDataModel;
import com.github.jorgecastilloprz.dagger2scopes.presentation.navigation.Navigator;
import com.squareup.otto.Subscribe;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class FilterListPresenterImpl implements FilterListPresenter {

  private EventBus bus;
  private Navigator navigator;
  private GetInstalledApps getInstalledApps;
  private List<AppDataModel> installedApps;

  private View view;

  @Inject FilterListPresenterImpl(EventBus bus, Navigator navigator,
      GetInstalledApps getInstalledApps) {
    this.bus = bus;
    this.navigator = navigator;
    this.getInstalledApps = getInstalledApps;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throwViewNotNullException();
    }
    this.view = view;
  }

  @Override public void initialize() {
    if (view == null) {
      throwViewNotNullException();
    }
    preloadInstalledApps();
  }

  private void preloadInstalledApps() {
    getInstalledApps.execute(new GetInstalledApps.GetInstalledAppsCallback() {
      @Override public void onAppsObtained(List<AppDataModel> applications) {
        installedApps = applications;
        view.showAnimatedFilterButton();
      }
    });
  }

  @Override public void onResume() {
    bus.register(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  @Override public void onAddFilterButtonClick() {
    view.hideNewFilterButton();
    navigator.displayAddFilterScreen(installedApps);
  }

  @Subscribe public void animateOnBackEvent(AnimateOnBackPressedEvent event) {
    view.displayFilterButton();
  }

  private void throwViewNotNullException() {
    throw new IllegalArgumentException("View must not be null!");
  }
}
