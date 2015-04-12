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

import android.content.Context;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ApplicationModule;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.BaseActivity;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.InteractorExecutor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.MainThread;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author Jorge Castillo Pérez
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(BaseActivity baseActivity);

  Context provideApplicationContext();

  InteractorExecutor provideThreadExecutor();

  MainThread providePostExecutionThread();
}
