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
package com.github.jorgecastilloprz.dagger2scopes.android.ui.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.github.jorgecastilloprz.dagger2scopes.R;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.Game;
import com.github.jorgecastilloprz.dagger2scopes.domain.model.LucasArtGame;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts a {@link Game} collection with some methods to allow RecyclerView to draw it. It
 * is not working with ViewModel items as it is a mocked item collection with so little info.
 * The normal thing to do would be having domain items with whole info to work in the app, and
 * ViewModel items to use here just with the info we need to draw.
 *
 * @author Jorge Castillo Pérez
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder>
    implements View.OnClickListener {

  private List<Game> items = new ArrayList<>();
  private OnItemClickListener onItemClickListener;

  public GameListAdapter() {
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public void drawGames(List<Game> games) {
    items = games;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
    v.setOnClickListener(this);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Game item = items.get(position);
    holder.image.setImageBitmap(null);
    Glide.with(holder.image.getContext()).load(item.getImageUrl()).centerCrop().into(holder.image);
    holder.itemView.setTag(item);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }

  @Override public void onClick(final View v) {
    if (onItemClickListener != null) {
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          onItemClickListener.onItemClick(v, (LucasArtGame) v.getTag());
        }
      }, 200);
    }
  }

  protected static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView text;

    public ViewHolder(View itemView) {
      super(itemView);
      image = (ImageView) itemView.findViewById(R.id.image);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(View view, LucasArtGame game);
  }
}
