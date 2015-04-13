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
package com.github.jorgecastilloprz.dagger2scopes.android;

import android.app.Application;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ApplicationModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.ApplicationComponent;
import com.github.jorgecastilloprz.dagger2scopes.android.di.components.DaggerApplicationComponent;

/**
 * {@link ApplicationComponent} could be used to provide dependencies needed by the whole app
 * execution. Application context linked dependencies would be exposed by it too.
 *
 * @author Jorge Castillo Pérez
 */
public class Dagger2ScopesApp extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent component() {
    return applicationComponent;
  }
}
