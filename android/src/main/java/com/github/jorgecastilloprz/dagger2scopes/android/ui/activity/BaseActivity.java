/*
 * Copyright (C) 2014 Jorge Castillo Pérez
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
package com.github.jorgecastilloprz.dagger2scopes.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import com.github.jorgecastilloprz.dagger2scopes.android.Dagger2ScopesApp;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.ActivityComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.ApplicationComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.GameActivityComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment.BaseFragment;

/**
 * BaseActivity will be extended by every activity in the app, and it hides
 * common logic for concrete activities, like initial dependency and view injections
 * <p/>
 * ActivityComponent could have different subcomponents like {@link GameActivityComponent} which
 * would expose different providing dependencies to the app. Everyone of them would extend
 * ActivityComponent and because of that, common dependencies like activity context would be
 * provided too.
 *
 * Created by jorge on 10/01/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

  protected ActivityComponent activityComponent;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initInjection();
  }

  private void initInjection() {
    this.getApplicationComponent().inject(this);
    this.initActivityComponent();
    activityComponent.inject(this);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((Dagger2ScopesApp) getApplication()).getApplicationComponent();
  }

  public void inject(BaseFragment baseFragment) {
    activityComponent.inject(baseFragment);
  }

  protected void injectViews() {
    ButterKnife.inject(this);
  }

  protected abstract void initActivityComponent();
}
