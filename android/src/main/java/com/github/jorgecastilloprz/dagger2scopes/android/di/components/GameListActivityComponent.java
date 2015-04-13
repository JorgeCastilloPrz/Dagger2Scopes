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
package com.github.jorgecastilloprz.dagger2scopes.android.di.components;

import com.github.jorgecastilloprz.dagger2scopes.android.di.ActivityModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.GameListModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.MainActivity;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment.LucasGameListFragment;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.LoadGames;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.GameCatalog;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.GameRepository;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenter;
import dagger.Component;

/**
 * Game list related dependencies linked to the activity context will be exposed by this component.
 * This component extends {@link AbstractActivityComponent}, so it is capable of providing activity
 * context and navigator too. (They dont need to get exposed here again as they are in the parent).
 *
 * @author Jorge Castillo Pérez
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, GameListModule.class
}) public interface GameListActivityComponent extends AbstractActivityComponent {

  //Main activity and game list fragments can get injected through this component.
  void inject(MainActivity mainActivity);

  void inject(LucasGameListFragment lucasGameListFragment);

  GameListPresenter gamePresenter();

  LoadGames loadGames();

  GameRepository gameRepository();

  GameCatalog gameCatalog();
}
