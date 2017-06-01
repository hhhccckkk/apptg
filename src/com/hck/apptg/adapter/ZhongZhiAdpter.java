package com.hck.apptg.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.ui.TongZhiMsgFragment;

public class ZhongZhiAdpter extends BaseAdapterImp<User> {
	
	public ZhongZhiAdpter(Context context, List<User> mDatas) {
		super(context, mDatas);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_item_tz, null);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.tz_user_img);
			viewHolder.contenTextView = (TextView) convertView
					.findViewById(R.id.zt_content);
			viewHolder.userTextView = (TextView) convertView
					.findViewById(R.id.tz_userName);
			viewHolder.caozuoTextView = (TextView) convertView
					.findViewById(R.id.tz_caozuo);
			setListener(viewHolder.caozuoTextView);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		viewHolder.contenTextView.setText(msgInviteBeans.get(position)
//				.getContent());
//		viewHolder.userTextView.setText(msgInviteBeans.get(position)
//				.getUserName() + "请加您为好友");
//		viewHolder.caozuoTextView.setTag(msgInviteBeans.get(position));
//		int state = msgInviteBeans.get(position).getState();
//		if (state == TongZhiMsgFragment.JUJUE) {
//			viewHolder.caozuoTextView.setText("已拒绝");
//			viewHolder.caozuoTextView.setBackgroundResource(R.color.whilt);
//			viewHolder.caozuoTextView.setTextColor(context.getResources()
//					.getColor(R.color.text_color666));
//		} else if (state == TongZhiMsgFragment.TONGYI) {
//			viewHolder.caozuoTextView.setText("已添加");
//			viewHolder.caozuoTextView.setBackgroundResource(R.color.whilt);
//			viewHolder.caozuoTextView.setTextColor(context.getResources()
//					.getColor(R.color.text_color666));
//		} else {
//			viewHolder.caozuoTextView.setText("操作");
//			viewHolder.caozuoTextView
//					.setBackgroundResource(R.drawable.liaotian_btn_selector);
//			viewHolder.caozuoTextView.setTextColor(context.getResources()
//					.getColor(R.color.whilt));
//		}
		//ImageLoader.getInstance().displayImage(
		//		msgInviteBeans.get(position).getTouxiang(),
		//		viewHolder.imageView,MyTools.getoptions());
		return convertView;
	}

	private void setListener(TextView textView) {
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			//	MsgInviteBean msgInviteBean = (MsgInviteBean) v.getTag();
			//	onTongZhiListener.caozuo(msgInviteBean);

			}
		});
	}

	class ViewHolder {
		ImageView imageView;
		TextView contenTextView, userTextView, caozuoTextView;
	}

}
