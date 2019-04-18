package com.prashant.movieapp.ui;

import com.prashant.movieapp.data.model.BaseItem;

import java.util.List;

/**
 * Created by Prashant Mhetre on 18/4/19.
 */
public class demo<T extends List<? extends BaseItem>> {
    private List<? extends BaseItem> list;
    public demo(List<? extends BaseItem> mylist){
        list=mylist;
    }
}

class demo3<T> {
    private T list;
    public<U extends T> demo3(U mylist){
        list=mylist;
    }
}
