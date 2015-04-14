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
package com.github.jorgecastilloprz.dagger2scopes.datasources.mock.model;

import java.io.Serializable;

/**
 * Mock game model. "mockNotImportantInfo" fields are just examples of fields that would not reach
 * the next layer, as the mapper for this layer would ignore them while doing the mapping.
 *
 * @author Jorge Castillo Pérez
 */
public class MockGame implements Serializable {

  private static final long serialVersionUID = -6470090944414208496L;

  protected int id;
  protected String imageUrl;
  protected String name;
  protected String releaseDate;
  protected String author;
  protected String description;
  protected boolean bookmarked;

  //This fields will not reach the subsequent inner layer
  protected String mockNotImportantInfo1;
  protected String mockNotImportantInfo2;
  protected String mockNotImportantInfo3;
  protected String mockNotImportantInfo4;
  protected String mockNotImportantInfo5;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isBookmarked() {
    return bookmarked;
  }

  public void setBookmarked(boolean bookmarked) {
    this.bookmarked = bookmarked;
  }

  public String getMockNotImportantInfo1() {
    return mockNotImportantInfo1;
  }

  public void setMockNotImportantInfo1(String mockNotImportantInfo1) {
    this.mockNotImportantInfo1 = mockNotImportantInfo1;
  }

  public String getMockNotImportantInfo2() {
    return mockNotImportantInfo2;
  }

  public void setMockNotImportantInfo2(String mockNotImportantInfo2) {
    this.mockNotImportantInfo2 = mockNotImportantInfo2;
  }

  public String getMockNotImportantInfo3() {
    return mockNotImportantInfo3;
  }

  public void setMockNotImportantInfo3(String mockNotImportantInfo3) {
    this.mockNotImportantInfo3 = mockNotImportantInfo3;
  }

  public String getMockNotImportantInfo4() {
    return mockNotImportantInfo4;
  }

  public void setMockNotImportantInfo4(String mockNotImportantInfo4) {
    this.mockNotImportantInfo4 = mockNotImportantInfo4;
  }

  public String getMockNotImportantInfo5() {
    return mockNotImportantInfo5;
  }

  public void setMockNotImportantInfo5(String mockNotImportantInfo5) {
    this.mockNotImportantInfo5 = mockNotImportantInfo5;
  }
}
