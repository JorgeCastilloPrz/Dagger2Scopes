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
package com.github.jorgecastilloprz.dagger2scopes.android.di;

import android.app.Activity;
import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.android.navigation.Dagger2ScopesNavigator;
import com.github.jorgecastilloprz.dagger2scopes.domain.navigation.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Dependency injection module used to provide activity scope context and satisfy activity/fragment
 * dependency needs
 *
 * @author Jorge Castillo Pérez
 */
@Module public class ActivityModule {
  private final Activity activityContext;

  public ActivityModule(Activity activityContext) {
    this.activityContext = activityContext;
  }

  @Provides @ActivityScope Activity getActivityContext() {
    return this.activityContext;
  }

  @Provides @ActivityScope Navigator provideNavigator(Dagger2ScopesNavigator navigator) {
    return navigator;
  }
}
