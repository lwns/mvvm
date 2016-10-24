//package com.domain.interactor.user;
//
//import com.domain.executor.PostExecutionThread;
//import com.domain.executor.ThreadExecutor;
//import com.domain.interactor.UseCase;
//import com.domain.repository.UserRepository;
//
//import javax.inject.Inject;
//
//import rx.Observable;
//
///**
// * @author op
// * @version 1.0
// * @description
// * @createDate 2016/8/8
// */
//public class UserRegister extends UseCase {
//    private final UserRepository userRepository;
//
//    private String name;
//    private String password;
//
//    @Inject
//    public UserRegister(String name, String password, UserRepository userRepository, ThreadExecutor threadExecutor,
//                        PostExecutionThread postExecutionThread) {
//        super(threadExecutor, postExecutionThread);
//        this.name = name;
//        this.password = password;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    protected Observable buildUseCaseObservable() {
//        return userRepository.login(name, password);
//    }
//}
