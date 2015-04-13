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

import android.app.Activity;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ActivityModule;
import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.animator.ToolbarAnimator;
import com.github.jorgecastilloprz.dagger2scopes.domain.navigation.Navigator;
import dagger.Component;

/**
 * Components linked to the activity context or to the activity lifecycle will be depending or
 * extending this one. Common dependencies linked to activity context or lifecycles would be
 * exposed by this component. {@link Navigator} and {@link ToolbarAnimator} are good examples of
 * that kind of dependencies.
 *
 * Fragment components may depend on this component. Activity-level components should extend this
 * component.
 *
 * @author Jorge Castillo Pérez
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AbstractActivityComponent {

  // Expose the activity to sub-graphs.
  Activity activityContext();

  Navigator navigator();

  ToolbarAnimator getToolbarAnimator();
}