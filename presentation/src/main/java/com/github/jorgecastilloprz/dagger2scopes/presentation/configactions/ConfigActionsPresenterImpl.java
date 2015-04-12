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
package com.github.jorgecastilloprz.dagger2scopes.presentation.configactions;

import com.github.jorgecastilloprz.dagger2scopes.domain.busevent.EventBus;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Action;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.ActionTypes;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.AppDataModel;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.BlockAction;
import com.github.jorgecastilloprz.dagger2scopes.presentation.navigation.Navigator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class ConfigActionsPresenterImpl implements ConfigActionsPresenter {

  private EventBus bus;
  private View view;
  private Navigator navigator;
  private List<AppDataModel> selectedApps;
  private List<Action> pickedActions;

  @Inject ConfigActionsPresenterImpl(EventBus bus, Navigator navigator) {
    this.bus = bus;
    this.navigator = navigator;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throwViewNotNullException();
    }
    this.view = view;
  }

  @Override public void setSelectedApps(List<AppDataModel> apps) {
    selectedApps = apps;
  }

  @Override public void onAddActionButtonClick() {
    view.displayActionPicker();
  }

  @Override public void onBlockActionPick() {
    if (!isActionAlreadyPicked(ActionTypes.BLOCK)) {
      addBlockActionToSelectedApps();
      view.drawBlockAction();
    } else {
      view.displayActionAlreadyPickedError();
    }
  }

  @Override public void onBackgroundActionPick() {
    if (!isActionAlreadyPicked(ActionTypes.BACKGROUND)) {

    } else {
      view.displayActionAlreadyPickedError();
    }
  }

  @Override public void onIconActionPick() {
    if (!isActionAlreadyPicked(ActionTypes.ICON)) {

    } else {
      view.displayActionAlreadyPickedError();
    }
  }

  @Override public void onTextColorActionPick() {
    if (!isActionAlreadyPicked(ActionTypes.TEXT_COLOR)) {

    } else {
      view.displayActionAlreadyPickedError();
    }
  }

  private boolean isActionAlreadyPicked(ActionTypes pickedType) {
    for (Action action : pickedActions) {
      if (action.getType() == pickedType) {
        return true;
      }
    }
    return false;
  }

  private void addBlockActionToSelectedApps() {
    for (AppDataModel app : selectedApps) {
      pickedActions.add(new BlockAction(app.getPackageName()));
    }
  }

  @Override public void initialize() {
    if (view == null) {
      throwViewNotNullException();
    }
    pickedActions = new ArrayList<>();
  }

  @Override public void onResume() {
    bus.register(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  private void throwViewNotNullException() {
    throw new IllegalArgumentException("View must not be null!");
  }
}
