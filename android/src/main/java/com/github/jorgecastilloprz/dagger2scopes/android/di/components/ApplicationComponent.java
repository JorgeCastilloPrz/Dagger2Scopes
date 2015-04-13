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

import android.app.Application;
import com.github.jorgecastilloprz.dagger2scopes.android.Dagger2ScopesApp;
import com.github.jorgecastilloprz.dagger2scopes.android.di.ApplicationModule;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.InteractorExecutor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.MainThread;
import dagger.Component;
import javax.inject.Singleton;

/**
 * ApplicationComponent is the top level component for this architecture. It provides generic
 * dependencies like {@link InteractorExecutor} or {@link MainThread} and exposes them to
 * subcomponents and to external dependant classes.
 *
 * Scope {@link Singleton} is used to limit dependency instances across whole execution.
 *
 * @author Jorge Castillo Pérez
 */
@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  //field injections for the dependencies of the Dagger2ScopesApp
  void inject(Dagger2ScopesApp app);

  //Exported to child components
  Application application();

  InteractorExecutor threadExecutor();

  MainThread mainThread();
}
