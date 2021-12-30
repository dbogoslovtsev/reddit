package com.domain.usecase.base;


import com.domain.executor.UIExecutionThread;
import com.domain.executor.WorkExecutionThread;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class AbsUseCaseSingle<Result, Params> extends AbsBaseUseCase {

    public AbsUseCaseSingle(@NonNull WorkExecutionThread threadExecutor, @NonNull UIExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Single<Result> buildUseCaseObservable(@Nullable Params params);

    public void execute(@NonNull DisposableSingleObserver<Result> observer, @Nullable Params params) {
        final Single<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler());
        
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(@NonNull DisposableSingleObserver<Result> observer, @NonNull SingleTransformer<Result, Result> transformer, @Nullable Params params) {
        final Single<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkExecutionThread.getScheduler())
                .observeOn(mPostExecutionThread.getScheduler())
                .compose(transformer);

        addDisposable(observable.subscribeWith(observer));
    }
}
