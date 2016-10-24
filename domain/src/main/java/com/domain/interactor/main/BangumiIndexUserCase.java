package com.domain.interactor.main;

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
public class BangumiIndexUserCase extends UseCase {

    MainRepository mainRepository;

    @Inject
    public BangumiIndexUserCase(MainRepository mainRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mainRepository = mainRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mainRepository.getSeasonNewBangumiList();
    }
}
