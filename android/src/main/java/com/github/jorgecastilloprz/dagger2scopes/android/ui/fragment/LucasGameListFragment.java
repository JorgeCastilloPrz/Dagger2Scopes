package com.github.jorgecastilloprz.dagger2scopes.android.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.InjectView;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.activity.MainActivity;
import com.github.jorgecastilloprz.dagger2scopes.android.ui.adapters.GameListAdapter;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenter;
import com.github.jorgecastilloprz.dagger2scopes.presentation.GameListPresenterImpl;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class LucasGameListFragment extends BaseFragment
    implements GameListPresenterImpl.View, GameListAdapter.OnItemClickListener {

  private static final String LOADED_GAMES = "loaded_games";

  @Inject GameListPresenter presenter;
  @InjectView(R.id.gameListRecycler) RecyclerView gameList;

  private GameListAdapter gameAdapter;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_gamelist, container, false);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((MainActivity) getActivity()).component().inject(this);
    initGameList();
    presenter.setView(this);
    presenter.initialize();
  }

  private void initGameList() {
    gameList.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    gameAdapter = new GameListAdapter();
    gameAdapter.setOnItemClickListener(this);
    gameList.setAdapter(gameAdapter);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.onResume();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(LOADED_GAMES, (Serializable) presenter.getCurrentGamesLoaded());
  }

  @Override public void onViewStateRestored(Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    if (savedInstanceState != null) {
      final List<Game> games = (List<Game>) savedInstanceState.getSerializable(LOADED_GAMES);
      presenter.restoreLoadedGames(games);
    }
  }

  @Override public void drawGames(List<Game> games) {
    gameAdapter.drawGames(games);
  }

  @Override public void onItemClick(View view, Game game) {
    presenter.onGameClicked(game);
  }

  @Override public void displayConnectionError() {
    Toast.makeText(gameList.getContext(), R.string.connection_error, Toast.LENGTH_LONG).show();
  }

  @Override public void displayLoadGamesError() {
    Toast.makeText(gameList.getContext(), R.string.load_games_error, Toast.LENGTH_LONG).show();
  }

  @Override public void displayGamesStoredIndication() {
    Toast.makeText(gameList.getContext(), R.string.games_stored, Toast.LENGTH_LONG).show();
  }

  @Override public void displayStoreGamesError() {
    Toast.makeText(gameList.getContext(), R.string.store_games_error, Toast.LENGTH_LONG).show();
  }
}
