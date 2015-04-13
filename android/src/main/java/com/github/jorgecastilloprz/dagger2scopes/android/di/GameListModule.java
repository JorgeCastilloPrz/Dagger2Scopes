/**
 * Copyright (C) 2015 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.github.jorgecastilloprz.dagger2scopes.android.di;

import com.github.jorgecastilloprz.dagger2scopes.android.di.scopes.ActivityScope;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.LoadGames;
import com.github.jorgecastilloprz.dagger2scopes.domain.interactors.LoadGamesInteractor;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.GameCatalog;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.LucasArtCatalog;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.GameRepository;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenterImpl;
import com.github.jorgecastilloprz.dagger2scopes.repository.GameRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module public class GameListModule {

  @Provides @ActivityScope GameListPresenter provideGamePresenter(GameListPresenterImpl presenter) {
    return presenter;
  }

  @Provides @ActivityScope LoadGames provideLoadGamesInteractor(LoadGamesInteractor interactor) {
    return interactor;
  }

  @Provides @ActivityScope GameRepository provideGameRepository(GameRepositoryImpl repository) {
    return repository;
  }

  @Provides @ActivityScope GameCatalog provideGameCatalog(LucasArtCatalog catalog) {
    return catalog;
  }
}
