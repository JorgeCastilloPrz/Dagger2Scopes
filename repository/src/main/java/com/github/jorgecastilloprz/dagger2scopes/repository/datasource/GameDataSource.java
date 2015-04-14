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
package com.github.jorgecastilloprz.dagger2scopes.repository.datasource;

import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import java.util.List;

/**
 * Came DataSource. Data sources implementations are part of the application details and
 * because of that, they are located into the outer layer. This class is just the entry point to
 * reach them, and it would be at the same level as the repository. By doing that, repository will
 * be able to use DataSources without depending on an outer layer, using Inversion of Control
 * principle.
 *
 * @author Jorge Castillo Pérez
 */
public interface GameDataSource {

  List<Game> getGames();
}
