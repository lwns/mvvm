package com.core.op.lib.base;

import static org.eclipse.jdt.internal.core.JavadocConstants.P;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public abstract class BDViewModel<T> {

    T binding;

    public void setBinding(T binding) {
        this.binding = binding;
    }
}
