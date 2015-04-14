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
package com.github.jorgecastilloprz.dagger2scopes.repository;

import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.domain.repository.GameRepository;
import com.github.jorgecastilloprz.dagger2scopes.repository.datasource.GameDataSource;
import java.util.List;
import javax.inject.Inject;

/**
 * Game repository implementation details. DataSources are injected into it using the dependency
 * injection framework.
 *
 * @author Jorge Castillo Pérez
 */
public class GameRepositoryImpl implements GameRepository {

  private GameDataSource dataSource;

  @Inject GameRepositoryImpl(GameDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override public List<Game> getGames() {
    return dataSource.getGames();
  }
}
