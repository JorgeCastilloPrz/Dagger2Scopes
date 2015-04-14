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
package com.github.jorgecastilloprz.dagger2scopes.datasources.mock.mapper;

import com.github.jorgecastilloprz.dagger2scopes.datasources.DataSourceMapper;
import com.github.jorgecastilloprz.dagger2scopes.datasources.mock.model.MockGame;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * As Uncle Bob says in his blog post about Clean, when we pass data across a boundary, it is
 * always in the form that is most convenient for the inner circle. It must be done by that way
 * to dont violate the dependency rule.
 *
 * @author Jorge Castillo Pérez
 */
public class MockGameMapper implements DataSourceMapper<MockGame, Game> {

  @Inject MockGameMapper() {
  }

  @Override public Game mapFromSourceToModel(MockGame dataSourceEntity) {
    Game domainGame = new Game();
    domainGame.setId(dataSourceEntity.getId());
    domainGame.setImageUrl(dataSourceEntity.getImageUrl());
    domainGame.setName(dataSourceEntity.getName());
    domainGame.setReleaseDate(dataSourceEntity.getReleaseDate());
    domainGame.setAuthor(dataSourceEntity.getAuthor());
    domainGame.setDescription(dataSourceEntity.getDescription());
    domainGame.setBookmarked(dataSourceEntity.isBookmarked());

    return domainGame;
  }

  @Override public List<Game> mapFromSourceToModel(List<MockGame> entityCollection) {
    List<Game> domainGames = new ArrayList<>();
    for (MockGame mockGame : entityCollection) {
      domainGames.add(mapFromSourceToModel(mockGame));
    }
    return domainGames;
  }

  @Override public MockGame mapFromModelToSource(Game dataSourceEntity) {
    return null;
  }

  @Override public List<MockGame> mapFromModelToSource(List<Game> entityCollection) {
    return null;
  }
}
