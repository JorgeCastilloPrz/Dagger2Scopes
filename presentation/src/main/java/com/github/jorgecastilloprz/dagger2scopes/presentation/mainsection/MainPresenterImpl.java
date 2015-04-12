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
package com.github.jorgecastilloprz.dagger2scopes.presentation.mainsection;

import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.EventBus;
import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.events.AnimateOnBackPressedEvent;
import com.github.jorgecastilloprz.dagger2scopes.presentation.navigation.Navigator;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class MainPresenterImpl implements MainPresenter {

  private View view;
  private Navigator navigator;
  private EventBus bus;

  @Inject MainPresenterImpl(Navigator navigator, EventBus bus) {
    this.navigator = navigator;
    this.bus = bus;
  }

  @Override public void initialize() {
  }

  public void setView(View view) {
    this.view = view;
  }

  @Override public void onBackPressed() {
    if (navigator.isJustAppListFragmentAvailable()) {
      bus.post(new AnimateOnBackPressedEvent());
    } else {
      view.emulateBackPress();
    }
  }

  @Override public void onResume() {
  }

  @Override public void onPause() {
  }
}
