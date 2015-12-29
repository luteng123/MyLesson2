package com.luteng.lesson2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import com.luteng.lesson2.adapters.ItemAdapter;
import com.luteng.lesson2.entity.Response;
import com.luteng.lesson2.retrofir.QsbkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<Response> {//之前的 implements Callback
//    private TextView textView;
    private ListView listView;
    private ItemAdapter adapter;
    private Call<Response> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView = (TextView) findViewById(R.id.main_text_view);
        listView = (ListView) findViewById(R.id.main_list);
        adapter = new ItemAdapter(this);
        listView.setAdapter(adapter);

        //下面的retrofit实现
        Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
//                下面是json解析，再往下为Gson解析
//                .addConverterFactory(new Converter.Factory() {
//                    @Override
//                    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
//                        return new Converter<ResponseBody, List<Item>>() {
//                            @Override
//                            public List<Item> convert(ResponseBody value) throws IOException {
//                                String s = value.string();
//                                final List<Item> list = new ArrayList<>();
//                                JSONObject object = null;
//                                try {
//                                   object = new JSONObject(s);
//                                    JSONArray array = null;
//                                    array = object.getJSONArray("items");
//
//                                    for (int i = 0; i < array.length(); i++) {
//                                        list.add(new Item(array.getJSONObject(i)));
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                return list;
//                            }
//                        };
//                    }
//                })
                //下面是Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QsbkService service = build.create(QsbkService.class);
        call = service.getList("image", 1);
        call.enqueue(this);

//        //使用 OkHttp，需要new  OkHttpClient对象
//        OkHttpClient client = new OkHttpClient();
//        //连接方式有七种，GET，POST，
//
//        //这是文本d的连接
//        //Request request = new Request.Builder().url("http://m2.qiushibaike.com/article/list/text?page=").get().build();
//        //图片的连接
//        Request request = new Request.Builder().url("http://m2.qiushibaike.com/article/list/suggest?page=").get().build();
//        //得到Call对象请求，成员变量防止突然退出界面，
//        call = client.newCall(request);
//        //同步请求：在本线程直接去请求，返回response；
//        //Android使用频率较低
//        //Response response = call.execute();
//        //下面是异步请求,请求结果以回调方式回来

    }
//
//    /**
//     * 请求失败，会告诉怎们哪个失败了。！！！打印异常信息
//     * ！！！！在非UI线程中执行，不可以直接更新到 UI
//     * 方法谁调用，则在哪个线程执行，方法从属的类和线程没有关系；
//     * @param request
//     * @param e
//     */
//    @Override
//    public void onFailure(Request request, IOException e) {
//        e.printStackTrace();//打印异常
//        Log.d("MainActivity", "onFailure" + Thread.currentThread().getName());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MainActivity.this,"网络问题",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    /**
//     * 用的是---->httpConnection，HttpClient5.0过时，6.0包没有
//     *  //请求成功回调方法
//     //！！！！非UI线程执行
//     * @param response
//     * @throws IOException
//     */
//    @Override
//    public void onResponse(Response response) throws IOException {
//        //可以说String，InputStream，byte[]。。。
//        final String s = response.body().string();
//        Log.d("MainActivity", "onResponse" + Thread.currentThread().getName());
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray array = jsonObject.getJSONArray("items");
//            final List<Item> list = new ArrayList<>();
//            for (int i = 0; i < array.length(); i++) {
//                list.add(new Item(array.getJSONObject(i)));
//            }
//            Log.d("MainActivity", "onResponse"+list);
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.addAll(list);
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //别的情况；UI直接操作情况，如果解析数据量大，则会耗时操作
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText(s);
//            }
//        });
        @Override
        protected void onStop() {
            super.onStop();
            //用户突然退出程序
            call.cancel();
        }

    @Override
    public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(this,"网络问题",Toast.LENGTH_SHORT).show();
    }
}




