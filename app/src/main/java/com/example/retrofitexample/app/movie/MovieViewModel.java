package com.example.retrofitexample.app.movie;

import android.arch.lifecycle.ViewModel;
import com.example.retrofitexample.model.Movie;
import com.example.retrofitexample.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.subjects.PublishSubject;


public class MovieViewModel extends ViewModel {

    public Observable<List<Movie>> getListMovieObservable(String search){
        final PublishSubject<List<Movie>> result = PublishSubject.create();
        MovieRepository.getInstance().getListMovie(search)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(res -> {
                    result.onNext(res.getListMovie());
                }, error -> {
                    result.onNext(new ArrayList<>());
                });
        return result;
    }
}
