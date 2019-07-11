package com.example.retrofitexample.app.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.R;
import com.example.retrofitexample.databinding.ListMovieItemBinding;
import com.example.retrofitexample.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> listMovie;

    public MovieAdapter(Context context, List<Movie> listMovie){
        this.context = context;
        this.listMovie = listMovie;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ListMovieItemBinding binding;
        public ViewHolder(ListMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListMovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_movie_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie movie = listMovie.get(i);

        Glide.with(context)
                .load(movie.getPoster())
                .into(viewHolder.binding.poster);

        viewHolder.binding.setViewModel(movie);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }
}
