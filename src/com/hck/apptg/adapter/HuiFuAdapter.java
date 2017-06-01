package com.hck.apptg.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;

public class HuiFuAdapter extends BaseAdapterImp<User> {
	


	public HuiFuAdapter(Context context, List<User> mDatas) {
		super(context, mDatas);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_huitie_item, null);
			viewHolder = new ViewHolder();
			viewHolder.toxuaingImageView = (ImageView) convertView
					.findViewById(R.id.huitie_touxiang);
			viewHolder.xingbieImageView = (ImageView) convertView
					.findViewById(R.id.huitie_xingbie);
			viewHolder.userNameTextView = (TextView) convertView
					.findViewById(R.id.huitie_userName);
			viewHolder.tiemTextView = (TextView) convertView
					.findViewById(R.id.huitie_time);
			viewHolder.contentTextView = (TextView) convertView
					.findViewById(R.id.huitie_content);
			viewHolder.imageView1 = (ImageView) convertView
					.findViewById(R.id.huitie_img1);

			viewHolder.imageView2 = (ImageView) convertView
					.findViewById(R.id.huitie_img2);
			viewHolder.imageView3 = (ImageView) convertView
					.findViewById(R.id.huitie_img3);
			viewHolder.fensiTextView = (TextView) convertView
					.findViewById(R.id.huitie_fensi);
			viewHolder.addressTextView = (TextView) convertView
					.findViewById(R.id.huitie_address);
			viewHolder.huifuImageView = (ImageView) convertView
					.findViewById(R.id.huitie_image);
			viewHolder.yuantieTextView = (TextView) convertView
					.findViewById(R.id.huitie_yuantie);
			viewHolder.userTextView = (TextView) convertView
					.findViewById(R.id.huitie_yuan_userName);
			viewHolder.huiTieLayout = (LinearLayout) convertView
					.findViewById(R.id.huitie_huifu_lay);
			//setListener(viewHolder.huifuImageView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		HuiTieBean huiTieBean = huiTieBeans.get(position);
//		viewHolder.huifuImageView.setTag(huiTieBean);
//		SpannableString spannableString = ExpressionUtil.getExpressionString(
//				context, huiTieBean.getContent(), Constant.zhengze);
//		viewHolder.contentTextView.setText(spannableString);
//		viewHolder.userNameTextView.setText(huiTieBean.getName());
//		viewHolder.addressTextView.setText(huiTieBean.getAddress());
//		String yuantie = huiTieBean.getYuantie();
//		if (TextUtils.isEmpty(yuantie)) {
//			viewHolder.yuantieTextView.setVisibility(View.GONE);
//			viewHolder.userTextView.setVisibility(View.GONE);
//			viewHolder.huiTieLayout.setVisibility(View.GONE);
//		} else {
//			viewHolder.yuantieTextView.setVisibility(View.VISIBLE);
//			viewHolder.userTextView.setVisibility(View.VISIBLE);
//			viewHolder.huiTieLayout.setVisibility(View.VISIBLE);
//			viewHolder.userTextView.setText(huiTieBean.getHuifuUserName());
//			SpannableString spannableString2 = ExpressionUtil
//					.getExpressionString(context, yuantie, Constant.zhengze);
//			viewHolder.yuantieTextView.setText(spannableString2);
//		}
//
//		if (huiTieBean.isXingbie()) {
//			viewHolder.xingbieImageView.setImageResource(R.drawable.nan);
//		} else {
//			viewHolder.xingbieImageView.setImageResource(R.drawable.nv);
//		}
//		String imag1, imag2, imag3;
//		imag1 = huiTieBean.getImage1();
//		imag2 = huiTieBean.getIamge2();
//		imag3 = huiTieBean.getIamge3();
//		if (TextUtils.isEmpty(imag1)) {
//			viewHolder.imageView1.setVisibility(View.GONE);
//		} else {
//			viewHolder.imageView1.setVisibility(View.VISIBLE);
//			GetImageUtil.showImageDaTu(imag1, viewHolder.imageView1);
//		}
//		if (TextUtils.isEmpty(imag2)) {
//			viewHolder.imageView2.setVisibility(View.GONE);
//		} else {
//			viewHolder.imageView2.setVisibility(View.VISIBLE);
//			GetImageUtil.showImageDaTu(imag2, viewHolder.imageView2);
//		}
//		if (TextUtils.isEmpty(imag3)) {
//			viewHolder.imageView3.setVisibility(View.GONE);
//		} else {
//			viewHolder.imageView3.setVisibility(View.VISIBLE);
//			GetImageUtil.showImageDaTu(imag3, viewHolder.imageView3);
//		}
//		viewHolder.tiemTextView.setText("丨"
//				+ TimeUtil.forTime(huiTieBean.getTime()));
//		viewHolder.fensiTextView.setText("丨粉丝" + huiTieBean.getFensi());
//		ImageLoader.getInstance().displayImage(huiTieBean.getTouxiang(),
//				viewHolder.toxuaingImageView, MyTools.getoptions());
		return convertView;
	}

	static class ViewHolder {
		ImageView toxuaingImageView, xingbieImageView, huifuImageView;
		TextView userNameTextView, tiemTextView, contentTextView,
				addressTextView, fensiTextView, yuantieTextView, userTextView;
		ImageView imageView1, imageView2, imageView3;
		LinearLayout huiTieLayout;
	}



}
