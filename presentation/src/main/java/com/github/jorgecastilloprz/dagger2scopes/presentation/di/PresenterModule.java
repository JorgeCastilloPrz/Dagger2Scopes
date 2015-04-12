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
package com.github.jorgecastilloprz.dagger2scopes.presentation.di;

import com.github.jorgecastilloprz.dagger2scopes.presentation.appgrid.AppListPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.appgrid.AppListPresenterImpl;
import com.github.jorgecastilloprz.dagger2scopes.presentation.configactions.ConfigActionsPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.configactions.ConfigActionsPresenterImpl;
import com.github.jorgecastilloprz.dagger2scopes.presentation.filterlist.FilterListPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.filterlist.FilterListPresenterImpl;
import com.github.jorgecastilloprz.dagger2scopes.presentation.mainsection.MainPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.mainsection.MainPresenterImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author Jorge Castillo Pérez
 */
@Module(
    injects = { FilterListPresenterImpl.class },
    library = true, complete = false) public class PresenterModule {

  @Provides @Singleton MainPresenter provideMainSectionPresenter(MainPresenterImpl presenter) {
    return presenter;
  }

  @Provides @Singleton FilterListPresenter provideFilterListPresenter(
      FilterListPresenterImpl presenter) {
    return presenter;
  }

  @Provides @Singleton AppListPresenter provideAppListPresenter(AppListPresenterImpl presenter) {
    return presenter;
  }

  @Provides @Singleton ConfigActionsPresenter provideConfigActionsPresenter(
      ConfigActionsPresenterImpl presenter) {
    return presenter;
  }
}
