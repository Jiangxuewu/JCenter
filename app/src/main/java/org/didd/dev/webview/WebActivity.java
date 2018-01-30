package org.didd.dev.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.didd.dev.R;
import org.didd.webview.WebViewUI;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = new Intent(this, WebViewUI.class);
//        intent.putExtra("url", "http://jsfiddle.net/gh/gist/library/pure/6164084/");
//        intent.putExtra("url", "http://m.palmplaystore.com/app/soft_detail?packageName=com.yomobigroup.yoweather");
//        intent.putExtra("url", "http://h5video.palmplaystore.com/play.html?showTitle=F&id=eMUqQVYutIk&likeCount=30573&activeIndex=2&lastPage=1&category=Comedy&title=(offensive%20Jokes)%20The%20Death%20of%20Comedy%20(18%2B%20only)&lastPage=None&lng=113.95469&netType=WIFI&display=720*1280&channel=Transsion&sign=67cb1195c21ad5b20bb9611db8f45938&sessionID=dd938df0-fcc5-11e7-819c-0292cd9b559b&systemVersionCode=24&ua=TECNO%20WX4%20Pro&uid=930195616&curClientVersionCode=64&curPage=Fun_Video&lan=zh&imei=216141430211168&brand=TECNO&lat=22.532533&curVersionName=6.5.7&timestamp=1516951471554");
//     VIDEO
// intent.putExtra("url", "http://android.palmplaystore.com/url_redirect/?urlCategory=tabUrl&url=http%3A//h5video.palmplaystore.com/");
        //iReader
//        intent.putExtra("url", "http://android.palmplaystore.com/url_redirect/?urlCategory=tabUrl&url=http%3A//abs.ireaderm.net/zyhw/app/app.php%3Fca%3DChannel.Index%26key%3DMLYW03%26p2%3D120138%26p3%3D200044%26pluginjs%3Dpalmplay");
        intent.putExtra("url", "http://samsung-news.op-mobile.opera.com/feeds/za-copyright/en");
        startActivity(intent);
        finish();
    }
}
