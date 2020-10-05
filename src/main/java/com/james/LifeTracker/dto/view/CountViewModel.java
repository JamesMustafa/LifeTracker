package com.james.LifeTracker.dto.view;

import com.james.LifeTracker.db.entity.common.BaseEntity;

public class CountViewModel<T extends BaseEntity> {

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    //here we can add all that percentage that i have in mind. ;)
}
