package xx.hh.videonews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xx.hh.videonews.ui.local.LocalVideoFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new LocalVideoFragment();
                case 1:
                    return new LocalVideoFragment();
                case 2:
                    return new LocalVideoFragment();
                default:
                    throw new RuntimeException("不存在的数据");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.btnNews)
    Button btnNews;
    @InjectView(R.id.btnLocal)
    Button btnLocal;
    @InjectView(R.id.btnLikes)
    Button btnLikes;
    @InjectView(R.id.buttonBar)
    LinearLayout buttonBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (viewPager == null) {
            viewPager = (ViewPager) findViewById(R.id.viewPager);
        } else {
            Log.e("TAG", "viewpager=" + viewPager + "++++++++++++++++++++++++++++++++++++");
            Log.e("TAG", "adapter=" + adapter + "++++++++++++++++++++++++++++++++++++");
            viewPager.setAdapter(adapter);
            // 对ViewPager进行监听(为了在Pager切换时，下方Button的切换)
            viewPager.addOnPageChangeListener(this);
            // 首次进入默认选中在线新闻
            btnNews.setSelected(true);
        }
    }

    @OnClick({R.id.btnNews, R.id.btnLocal, R.id.btnLikes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNews:
                viewPager.setCurrentItem(0, false);
                return;
            case R.id.btnLocal:
                viewPager.setCurrentItem(1, false);
                return;
            case R.id.btnLikes:
                viewPager.setCurrentItem(2, false);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        // ViewPager页面变化时设置下方按钮的选中状态
        btnNews.setSelected(position == 0);
        btnLocal.setSelected(position == 1);
        btnLikes.setSelected(position == 2);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
