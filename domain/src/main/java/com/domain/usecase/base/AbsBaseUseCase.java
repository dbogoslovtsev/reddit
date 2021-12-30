package com.domain.usecase.base;


import com.domain.executor.UIExecutionThread;
import com.domain.executor.WorkExecutionThread;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AbsBaseUseCase {

    final WorkExecutionThread mWorkExecutionThread;
    final UIExecutionThread mPostExecutionThread;
    private final CompositeDisposable mDisposables;

    AbsBaseUseCase(@NonNull WorkExecutionThread threadExecutor, @NonNull UIExecutionThread postExecutionThread) {
        this.mWorkExecutionThread = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
        this.mDisposables = new CompositeDisposable();
    }

    public void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    void addDisposable(@NonNull Disposable disposable) {
        mDisposables.add(disposable);
    }
}
