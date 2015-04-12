/*
 * Copyright (C) 2014 Jorge Castillo Pérez
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
package com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.BaseActivity;

/**
 * Created by jorge on 2/02/15.
 */
public class BaseFragment extends Fragment {

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    injectDependencies();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injectViews(view);
  }

  /**
   * ButterKnife injection
   */
  private void injectViews(View view) {
    ButterKnife.inject(this, view);
  }

  /**
   * Dagger injection using activity graph scope
   */
  private void injectDependencies() {
    ((BaseActivity) getActivity()).inject(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
