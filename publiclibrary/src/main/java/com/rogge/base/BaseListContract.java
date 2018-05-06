package com.rogge.base;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.rogge.base_bean.BaseRespModel;

import java.util.List;
import java.util.Map;

import rx.Observable;


/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2016/12/23.
 * @since 1.0.0
 */

public interface BaseListContract {

    interface Model<T> extends BaseModel {
        Observable<BaseRespModel<List<T>>> getListData(Map<String, String> op);
    }

    interface View<T> extends BaseView {
        Map<String, String> createParams();

        void initAdapter(List<T> resultData);

        BaseQuickAdapter getAdapter();

        TwinklingRefreshLayout getRefreshView();
    }

    abstract class Presenter extends BasePresenter {
        public abstract void lodeDataRequest(boolean isFirst);

        public abstract void loadMoreDataFail();
    }
}
