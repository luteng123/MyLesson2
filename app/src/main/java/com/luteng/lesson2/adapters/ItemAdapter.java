package com.luteng.lesson2.adapters;

import android.content.Context;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.luteng.lesson2.R;
import com.luteng.lesson2.entity.Item;
import com.luteng.lesson2.entity.Response;
import com.luteng.lesson2.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John on 2015/12/29.
 */
public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Response.ItemsEntity> list;

    public ItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        Response.ItemsEntity item = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (item.getUser() != null) {
            holder.name.setText(item.getUser().getLogin());
            Picasso.with(context).load(getIconURL(item.getUser().getId(),item.getUser().getIcon()))
                    .transform(new CircleTransform())
                    .into(holder.icon);
        } else {
            holder.name.setText("匿名用户");
            holder.icon.setImageResource(R.mipmap.ic_launcher);
        }
        holder.content.setText(item.getContent());
        if (item.getImage() == null) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.VISIBLE);
            Log.d("ItemAdapter", "s = 11111111111  " + getImageURL(item.getImage()));
            // .fit()//定义个ImageView，在ListView不好用
            // .centerInside()//剪切还是填充
            Picasso.with(context)
                    .load(getImageURL(item.getImage()))
                    .placeholder(R.mipmap.ic_launcher)//下载中暂时填充
                    .error(R.mipmap.ic_launcher)//下载失败图片
                    .resize(parent.getWidth(), 0)
                    .into(holder.image);
        }
        return convertView;
    }

    //下面static是工具类
    public static String getImageURL(String image) {
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        //TODO:检查网络，下载图片的类型
        String s = String.format(url,matcher.group(1),matcher.group(),"small",image);
        return s;
    }

    public static String getIconURL(long id, String icon) {
        String url = "http:///pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        //return String.format(url,id/1000)
        return String.format(url,id/10000,id,icon);
    }

    private static class ViewHolder {
        private ImageView icon;
        private TextView name;
        private TextView content;
        private ImageView image;

        public ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.user_icon);
            name = (TextView) view.findViewById(R.id.user_name);
            content = (TextView) view.findViewById(R.id.content);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

    //!!!直接调用就可以
    public void addAll(Collection<? extends Response.ItemsEntity> collection) {
        list.addAll(collection);
        Log.d("ItemAdapter", "addAll" + list);
        notifyDataSetChanged();
    }
}
