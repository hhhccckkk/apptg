package com.hck.apptg.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class BaseAdapterImp<T> extends BaseAdapter {

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;

	public BaseAdapterImp(Context context, List<T> mDatas) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
	}

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas == null ? null : mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
