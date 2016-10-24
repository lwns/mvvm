package com.domain.interactor.main;

import com.domain.bean.LiveIndex;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.interactor.UseCase;
import com.domain.repository.MainRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/11
 */
public class LiveIndexUserCase extends UseCase<LiveIndex> {

    MainRepository mainRepository;

    @Inject
    public LiveIndexUserCase(MainRepository mainRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mainRepository = mainRepository;
    }

    @Override
    protected Observable<LiveIndex> buildUseCaseObservable() {
        return mainRepository.liveIndex();
    }
}
