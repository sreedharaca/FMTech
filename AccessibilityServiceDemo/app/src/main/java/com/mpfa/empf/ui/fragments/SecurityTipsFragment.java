package com.mpfa.empf.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mpfa.empf.ui.fragments.base.PageFragment;

import com.mpfa.empf.R;

/**
 * ==================================================================
 * Copyright (C) 2015 Mtelnet All Rights Reserved.
 *
 * @author Drew.Chiang
 * @version v1.0.0
 * @email chiangchuna@gmail.com
 * @create_date 2015/12/29 13:42
 * @description ${todo}
 * <p/>
 * Modification History:
 * Date            Author            Version         Description
 * -----------------------------------------------------------------
 * 2015/12/29 13:42  Drew.Chiang       v1.0.0          create
 * <p/>
 * ==================================================================
 */

public class SecurityTipsFragment extends PageFragment {

    private String mBreadcrumb;

    public static SecurityTipsFragment newInstance(){
        SecurityTipsFragment newsFragment = new SecurityTipsFragment();
        return newsFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBreadcrumb = mContext.getString(R.string.fragment_title_security_tips);
        rebindActionBar();
    }

    @Override
    public void rebindActionBar() {
        this.mPageFragmentHost.updateActionBarTitle(mBreadcrumb);
        mPageFragmentHost.updateCurrentBackendId(FragmentConfig.FRAGMENT_SECURITY_TIPS -1, true);
        mPageFragmentHost.switchToRegularActionBar();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_security_tips;
    }

    @Override
    public void rebindViews() {

    }

    @Override
    public void requestData() {

    }
}
