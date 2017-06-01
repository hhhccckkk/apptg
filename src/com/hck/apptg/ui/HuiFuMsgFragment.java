package com.hck.apptg.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hck.apptg.R;
import com.hck.apptg.adapter.HuiFuAdapter;
import com.hck.apptg.view.Pdialog;

public class HuiFuMsgFragment extends BaseFragment {
	private PullToRefreshListView pullToRefreshListView;
	private int page = 1;
	private boolean isUpdate;
	private HuiFuAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mRootView == null) {
			mRootView = inflater.inflate(R.layout.fragment_huifu_meg, null);
			initView(mRootView);
			setListener();
			setEndLabel();
			getData();
			Pdialog.showLoading(getActivity(), true);
		}
		ViewGroup parent = (ViewGroup) mRootView.getParent();
		if (parent != null) {
			parent.removeView(mRootView);
		}
		return mRootView;
	}

	private void getData() {
		

	}

	private void updateView() {
		
	}

	private void setEndLabel() {
		ILoadingLayout endLabel = pullToRefreshListView.getLoadingLayoutProxy(
				false, true);
		endLabel.setPullLabel(getString(R.string.load_more));
		endLabel.setReleaseLabel(getString(R.string.load_more));
		endLabel.setRefreshingLabel(getString(R.string.is_loading));
	}

	private void setListener() {

	}

	private void initView(View mRootView) {
		pullToRefreshListView = (PullToRefreshListView) mRootView
				.findViewById(R.id.huifu_list);
		pullToRefreshListView.setMode(Mode.BOTH);
	}

}
