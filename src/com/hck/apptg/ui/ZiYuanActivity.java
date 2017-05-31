package com.hck.apptg.ui;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hck.apptg.R;
import com.hck.apptg.data.ZiYuanData;
import com.hck.apptg.presenter.ZiYuanPresenter;
import com.hck.apptg.util.LogUtil;

public class ZiYuanActivity extends BaseActivity {
	private ZiYuanPresenter mPresenter;
	private int page = 1;
	private PullToRefreshListView mPullToRefreshListView;
	private boolean mIsUpdateIng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ziyuan);
		hiddenBackBtn();
		initTitle("资源信息");
		initView();
		setEndLabel();
		mPresenter = new ZiYuanPresenter(this);
		getData();
	}

	private void getData() {
		mPresenter.getZiYuanInfo(page);
	}

	public void updateUi(ZiYuanData ziYuanData) {
		LogUtil.D("updateUi: updateUiupdateUi");
	}

	private void initView() {
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.tieziList);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}

	private void setEndLabel() {
		ILoadingLayout endLabel = mPullToRefreshListView.getLoadingLayoutProxy(
				false, true);
		endLabel.setPullLabel(getString(R.string.load_more));
		endLabel.setReleaseLabel(getString(R.string.load_more));
		endLabel.setRefreshingLabel(getString(R.string.is_loading));
	}

	@SuppressWarnings("unchecked")
	private void setListener() {
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				page = 1;
				mIsUpdateIng = true;
				getData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				page++;
				getData();
			}
		});

		mPullToRefreshListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

					}
				});
	}

}
