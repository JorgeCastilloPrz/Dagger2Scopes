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
package com.github.jorgecastilloprz.dagger2scopes.domain.model;

import java.io.Serializable;

/**
 * @author Jorge Castillo Pérez
 */
public class AppDataModel implements Serializable {

  private static final long serialVersionUID = 0L;

  private String name;
  private String packageName;
  private String iconUri;
  private boolean selected;

  public AppDataModel(String name, String packageName, String iconUri) {
    this.name = name;
    this.packageName = packageName;
    this.iconUri = iconUri;
  }

  public String getName() {
    return name;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getIconUri() {
    return iconUri;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public boolean isSelected() {
    return selected;
  }
}
