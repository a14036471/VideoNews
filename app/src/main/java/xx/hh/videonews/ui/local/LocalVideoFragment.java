package xx.hh.videonews.ui.local;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xx.hh.videonews.R;

/**
 * Created by J on 2016/8/12 0014.
 */
public class LocalVideoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @InjectView(R.id.gridView)
    GridView gridView;
    private LocalVideoAdapter localVideoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localVideoAdapter = new LocalVideoAdapter(getContext());
        // 初始当前页面的Loader
        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_video, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setAdapter(localVideoAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localVideoAdapter.release();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MediaStore.Video.Media._ID, // 视频Id
                MediaStore.Video.Media.DATA, // 视频文件路径
                MediaStore.Video.Media.DISPLAY_NAME, // 视频名称
        };
        return new CursorLoader(getContext(),
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        localVideoAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        localVideoAdapter.swapCursor(null);
    }
}
