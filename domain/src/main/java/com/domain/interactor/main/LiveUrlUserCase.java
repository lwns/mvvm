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
public class LiveUrlUserCase extends UseCase {

    MainRepository mainRepository;

    @Inject
    public LiveUrlUserCase(MainRepository mainRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mainRepository = mainRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mainRepository.getLiveUrl(Integer.parseInt(params[0]));
    }
}
