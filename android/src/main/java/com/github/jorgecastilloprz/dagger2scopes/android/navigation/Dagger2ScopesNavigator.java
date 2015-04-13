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
package com.github.jorgecastilloprz.dagger2scopes.android.navigation;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.GameDetailsActivity;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.domain.navigation.Navigator;
import javax.inject.Inject;

/**
 * @author Jorge Castillo Pérez
 */
public class Dagger2ScopesNavigator implements Navigator {

  private Activity activity;

  @Inject Dagger2ScopesNavigator(Activity activity) {
    this.activity = activity;
  }

  private FragmentManager getFragmentManager() {
    return ((FragmentActivity) activity).getSupportFragmentManager();
  }

  @Override public void displayGameDetails(Game game) {
    Intent displayGameDetailsIntent = GameDetailsActivity.getLaunchIntent(activity, game);
    activity.startActivity(displayGameDetailsIntent);
  }
}
