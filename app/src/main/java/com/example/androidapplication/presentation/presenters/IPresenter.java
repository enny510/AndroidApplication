package com.example.androidapplication.presentation.presenters;


import com.example.androidapplication.presentation.views.IView;

public interface IPresenter<V extends IView> {
    void attachView(V view);
    void detachView();
}
