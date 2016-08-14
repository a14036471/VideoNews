package xx.hh.videonews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xx.hh.videoplayer.part.SimpleVideoView;

/**
 * Created by J on 2016/8/11 0014.
 */
public class PartPlayActivity extends AppCompatActivity {


    @InjectView(R.id.simpleVideoPlayer)
    SimpleVideoView simpleVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_play);
        ButterKnife.inject(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        simpleVideoPlayer.setVideoPath(getTestVideo1());
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleVideoPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleVideoPlayer.onPasuse();
    }

    private String getTestVideo1() {
        return "http://o9ve1mre2.bkt.clouddn.com/raw_%E6%B8%A9%E7%BD%91%E7%94%B7%E5%8D%95%E5%86%B3%E8%B5%9B.mp4";
    }
}
