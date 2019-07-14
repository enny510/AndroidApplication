package com.example.androidapplication.presentation.presenters;

import com.example.androidapplication.presentation.views.IView;

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
