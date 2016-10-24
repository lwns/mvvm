package com.core.op.data.repository;

import com.core.op.data.ApiClient;
import com.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/8
 */
@Singleton
public class UserRepositoryImp implements UserRepository {

    ApiClient apiClient;

    @Inject
    public UserRepositoryImp(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

//    @Override
//    public Observable<User> login(String name, String psw) {
//        return apiClient.login(name, psw).compose(new ErrorTransformer());
//    }

}
