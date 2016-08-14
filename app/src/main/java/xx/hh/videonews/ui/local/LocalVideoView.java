package xx.hh.videonews.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import xx.hh.videonews.R;
import xx.hh.videoplayer.full.VideoViewActivity;

/**
 * Created by J on 2016/8/12 0014.
 */
public class LocalVideoView extends FrameLayout {
    @InjectView(R.id.ivPreview)
    ImageView ivPreview;
    @InjectView(R.id.tvVideoName)
    TextView tvVideoName;

    private String filePath; // 本地视频文件路径

    public LocalVideoView(Context context) {
        this(context, null);
    }

    public LocalVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocalVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_local_video, this, true);
    }

    public void bind(Cursor cursor) {
        filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
        tvVideoName.setText(videoName);
        // 清除old预览图
        ivPreview.setImageBitmap(null);
    }

    @OnClick({R.id.ivPreview, R.id.tvVideoName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPreview:
                // 全屏播放
                VideoViewActivity.open(getContext(), filePath);
                break;
            case R.id.tvVideoName:
                break;
        }
    }

    @UiThread
    public void setPreview(@NonNull Bitmap bitmap) {
        ivPreview.setImageBitmap(bitmap);
    }

    public void setPreview(final String filePath, final Bitmap bitmap) {
        if (!filePath.equals(this.filePath)) return;
        post(new Runnable() {
            @Override
            public void run() {
                // 二次确认
                if (!filePath.equals(LocalVideoView.this.filePath)) return;
                ivPreview.setImageBitmap(bitmap);
            }
        });
    }

    public String getFilePath() {
        return filePath;
    }
}
