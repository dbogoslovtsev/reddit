package com.example.domain.usecase.base;


import com.shark.radio.usecase.executor.UIExecutionThread;
import com.shark.radio.usecase.executor.WorkExecutionThread;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableObserver;

public abstract class AbsUseCaseObs<Result, Params> extends AbsBaseUseCase {

    public AbsUseCaseObs(@NonNull WorkExecutionThread threadExecutor, @NonNull UIExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Observable<Result> buildUseCaseObservable(@Nullable Params params);

    public void execute(@NonNull DisposableObserver<Result> observer, @Nullable Params params) {
        final Observable<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(@NonNull DisposableObserver<Result> observer, @NonNull ObservableTransformer<Result, Result> transformer, @Nullable Params params) {
        final Observable<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler())
                .compose(transformer);
        addDisposable(observable.subscribeWith(observer));
    }
}
