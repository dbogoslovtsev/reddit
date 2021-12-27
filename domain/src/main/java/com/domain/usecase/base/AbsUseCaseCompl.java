package com.domain.usecase.base;


import com.shark.radio.usecase.executor.UIExecutionThread;
import com.shark.radio.usecase.executor.WorkExecutionThread;

import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableCompletableObserver;

public abstract class AbsUseCaseCompl<Params> extends AbsBaseUseCase {

    public AbsUseCaseCompl(@NonNull WorkExecutionThread threadExecutor, @NonNull UIExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Completable buildUseCaseObservable(@Nullable Params params);

    public void execute(@NonNull DisposableCompletableObserver observer, @Nullable Params params) {

        final Completable completable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler());

        addDisposable(completable.subscribeWith(observer));
    }

    public void execute(@NonNull DisposableCompletableObserver observer, @NonNull CompletableTransformer transformer, @Nullable Params params) {
        final Completable completable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler())
                .compose(transformer);
        addDisposable(completable.subscribeWith(observer));
    }
}
