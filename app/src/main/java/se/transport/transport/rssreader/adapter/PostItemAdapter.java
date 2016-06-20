/**
 * PostItemAdapter.java
 * 
 * Adapter Class which configs and returns the View for ListView
 * 
 */
package se.transport.transport.rssreader.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import se.transport.transport.R;
import se.transport.transport.rssreader.vo.PostData;

import java.util.ArrayList;
import java.util.Random;

public class PostItemAdapter extends ArrayAdapter<PostData> {
	private LayoutInflater inflater;
	private ArrayList<PostData> datas;
	final Random rnd = new Random();

	public PostItemAdapter(Context context, int textViewResourceId,
						   ArrayList<PostData> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		inflater = ((Activity) context).getLayoutInflater();
		datas = objects;
	}

	static class ViewHolder {
		TextView postTitleView;
		TextView postDateView;
		ImageView postThumbView;
		TextView postFirstLetter;
		TextView postDescriptionView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.postitem, null);

			viewHolder = new ViewHolder();
			viewHolder.postThumbView = (ImageView) convertView
					.findViewById(R.id.postThumb);
			viewHolder.postTitleView = (TextView) convertView
					.findViewById(R.id.postTitleLabel);
			viewHolder.postDateView = (TextView) convertView
					.findViewById(R.id.postDateLabel);
			viewHolder.postFirstLetter = (TextView) convertView
					.findViewById(R.id.postFirstLetter);
			viewHolder.postDescriptionView = (TextView) convertView
					.findViewById(R.id.postDescription);


			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
/*
		if (datas.get(position).postThumbUrl == null) {
			viewHolder.postThumbView
					.setImageResource(R.drawable.postthumb_loading);
		}*/

		viewHolder.postTitleView.setText(datas.get(position).postTitle.replaceAll("<.*?>", ""));

		String getFirstLetter = datas.get(position).postTitle;
		String firstLetterIs = getFirstLetter.substring(0,1);
		if (firstLetterIs.matches("[^a-öA-Ö0-9]")){
			firstLetterIs = getFirstLetter.substring(1,2);
		}

		viewHolder.postFirstLetter.setText(firstLetterIs);
		viewHolder.postDescriptionView.setText(datas.get(position).postDescription.replaceAll("<.*?>", "").replace("&nbsp;",""));

		int rndN = rnd.nextInt(12) ;

		String imgName = "circle_" + rndN;
		Resources res = getContext().getResources();
		int id = res.getIdentifier(imgName, "drawable", getContext().getPackageName());




		viewHolder.postThumbView.setImageResource(id);
		viewHolder.postDateView.setText(datas.get(position).postDate);

		return convertView;
	}
}
