package com.github.jorgecastilloprz.dagger2scopes.presentation.navigation;

import com.github.jorgecastilloprz.dagger2scopes.domain.model.AppDataModel;
import java.util.List;

/**
 * @author Jorge Castillo Pérez
 */
public interface Navigator {

  void displayAddFilterScreen(List<AppDataModel> apps);

  void moveToConfigureActionsScreen(List<AppDataModel> selectedApps);

  boolean isJustAppListFragmentAvailable();
}
