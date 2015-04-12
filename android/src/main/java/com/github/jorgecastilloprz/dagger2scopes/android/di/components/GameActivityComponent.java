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
import com.github.jorgecastilloprz.dagger2scopes.android.di.GameModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment.BaseFragment;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.LoadGames;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.GameRepository;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenter;
import dagger.Component;

/**
 * @author Jorge Castillo Pérez
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, GameModule.class
}) public interface GameActivityComponent extends ActivityComponent {

  void inject(BaseFragment baseFragment);

  GameListPresenter gamePresenter();

  LoadGames loadGames();

  GameRepository gameRepository();
}
