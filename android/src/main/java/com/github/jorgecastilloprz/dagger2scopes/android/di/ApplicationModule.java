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

import android.app.Application;
import com.github.jorgecastilloprz.dagger2scopes.android.domain.MainThreadImpl;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.InteractorExecutor;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.MainThread;
import com.github.jorgecastilloprz.dagger2scopes.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module used to inject application context or generic dependencies.
 *
 * @author Jorge Castillo Pérez
 */
@Module public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton InteractorExecutor provideThreadExecutor(ThreadExecutor executor) {
    return executor;
  }

  @Provides @Singleton MainThread providePostExecutionThread(MainThreadImpl mainThread) {
    return mainThread;
  }
}