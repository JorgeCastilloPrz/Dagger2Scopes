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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.InjectView;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ActivityModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.HasComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.DaggerGameActivityComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.GameActivityComponent;

public class MainActivity extends BaseActivity implements HasComponent<GameActivityComponent> {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  private GameActivityComponent gameActivityComponent;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActivityComponent();
    injectViews();
    setSupportActionBar(toolbar);
  }

  private void initActivityComponent() {
    this.gameActivityComponent = DaggerGameActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public GameActivityComponent getComponent() {
    return gameActivityComponent;
  }
}
