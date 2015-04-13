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
import com.github.jorgecastilloprz.dagger2scopes.android.di.GameDetailsModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.GameDetailsActivity;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.animator.ToolbarAnimator;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.ChangeBookmarkGameStatus;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameDetailsPresenter;
import dagger.Component;

/**
 * Every game related dependency linked to the activity context will be exposed by this component.
 * This component extends {@link AbstractActivityComponent}, so it is capable of providing activity
 * context
 * and navigator too. (They dont need to get exposed here again as they are in the parent).
 *
 * @author Jorge Castillo Pérez
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, GameDetailsModule.class
}) public interface GameDetailsComponent extends AbstractActivityComponent {

  void inject(GameDetailsActivity detailsActivity);

  GameDetailsPresenter getPresenter();

  ChangeBookmarkGameStatus getBookmarkInteractor();

  ToolbarAnimator getToolbarAnimator();
}
