package com.qskj.tyt.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 选择头像界面
 */
@EActivity(R.layout.activity_select_picture)
public class SelectPictureActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SelectPictureActivity";
    public static final String INTENT_SELECTED_PICTURE = "intent_selected_picture";

    private static final int CUT_PICTURE_SIZE = 300; // 裁剪图片的大小（像素）
    private static final int PHOTO_REQUEST_TAKE_PIC = 1; // 拍照处理请求码
    private static final int PHOTO_REQUEST_CUT = 2;// 裁剪处理请求码

    private Context mContext;
    private GridView mGridView;
    private PictureAdapter mPictureAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashMap<String, Integer> tmpDir = new HashMap<String, Integer>();
    private ArrayList<ImageFolder> mDirPaths = new ArrayList<ImageFolder>();
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    private ContentResolver mContentResolver;
    private ListView mListView;
    private FolderAdapter mFolderAdapter;
    private ImageFolder imageAll, currentImageFolder;
    private String cameraPath = null;
    private Uri imageUri;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_all_pic)
    AppCompatTextView tv_all_pic;

    @Override
    public void onAfterViews() {
        initToolbar();

        mContext = this;
        mContentResolver = getContentResolver();

        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        initViews();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_select_picture);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all_pic: // 所有图片
                if (mListView.getVisibility() == View.VISIBLE) {
                    hideListAnimation();
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    showListAnimation();
                    mFolderAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void showListAnimation() {
        TranslateAnimation ta = new TranslateAnimation(1, 0f, 1, 0f, 1, 1f, 1, 0f);
        ta.setDuration(200);
        mListView.startAnimation(ta);
    }

    private void hideListAnimation() {
        TranslateAnimation ta = new TranslateAnimation(1, 0f, 1, 0f, 1, 0f, 1, 1f);
        ta.setDuration(200);
        mListView.startAnimation(ta);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mListView.setVisibility(View.GONE);
            }
        });
    }

    private void initViews() {
        tv_all_pic.setOnClickListener(this);

        imageAll = new ImageFolder();
        imageAll.setDir("/所有图片");
        currentImageFolder = imageAll;
        mDirPaths.add(imageAll);

        mGridView = (GridView) findViewById(R.id.gridView);
        mPictureAdapter = new PictureAdapter();
        mGridView.setAdapter(mPictureAdapter);

        mListView = (ListView) findViewById(R.id.listView);
        mFolderAdapter = new FolderAdapter();
        mListView.setAdapter(mFolderAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentImageFolder = mDirPaths.get(position);
                Log.i(TAG, position + "-------" + currentImageFolder.getName() + "----"
                        + currentImageFolder.images.size());
                hideListAnimation();
                mPictureAdapter.notifyDataSetChanged();
                tv_all_pic.setText(currentImageFolder.getName());
            }
        });
        getThumbnail();
    }

    /**
     * 使用相机拍照
     */
    private void goCamera() {
        // 调用系统相机
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        imageUri = getOutputMediaFileUri();
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, PHOTO_REQUEST_TAKE_PIC);
    }

    /**
     * 用于拍照时获取输出的Uri
     */
    private Uri getOutputMediaFileUri() {
        // 创建 appName (天易通) 文件夹，并创建一个图片 /sdcard/Pictures/appName (天易通)
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getStrings(R.string.app_name));
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i(TAG, "failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "JPG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
    }

    /**
     * 拍照返回图片的处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKE_PIC: // 拍照后处理
                if (cameraPath != null) {
                    // 裁剪
                    startPhotoZoom(imageUri, CUT_PICTURE_SIZE);
                }
                break;
            case PHOTO_REQUEST_CUT:// 裁剪后处理
                if (data != null) {
                    // 裁剪后的图片
                    Bitmap bitmap = (Bitmap) data.getExtras().getParcelable("data");
                    // 将裁剪后的图片存到系统相册
                    String cutPicPath = saveImageToGallery(this, bitmap);
                    Intent intent = new Intent();
                    intent.putExtra(INTENT_SELECTED_PICTURE, cutPicPath);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

    /**
     * 将图片保存到系统相册（相册名字为应用名）
     *
     * @param context
     * @param bmp
     */
    private static String saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date());
        String fileName = "CUT_JPG_" + timeStamp + ".jpg";
        File file = new File(appDir.getPath(), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果需要把裁剪的图片保存到系统相册，就放开下面注释的代码，就也会将裁减的图片保存到系统相册
        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

        return file.getAbsolutePath();
    }

    private class ViewHolder {
        AppCompatImageView iv;
    }

    /**
     * 调用系统裁剪
     *
     * @param uri
     * @param size
     */
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 图片的数据适配器
     */
    private class PictureAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return currentImageFolder.images.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_gridview_picture, null);
                holder = new ViewHolder();
                holder.iv = (AppCompatImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == 0) {
                holder.iv.setImageResource(R.mipmap.pickphotos_to_camera_normal);
                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goCamera();
                    }
                });
            } else {
                position = position - 1;
                final ImageItem item = currentImageFolder.images.get(position);
                mImageLoader.displayImage("file://" + item.path, holder.iv, options);
                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPhotoZoom(Uri.parse("file://" + item.path), CUT_PICTURE_SIZE);
                    }
                });
            }
            return convertView;
        }
    }

    private class FolderViewHolder {
        AppCompatImageView id_dir_item_image;
        AppCompatImageView choose;
        AppCompatTextView id_dir_item_name;
        AppCompatTextView id_dir_item_count;
    }

    /**
     * 文件夹的数据适配器
     */
    private class FolderAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDirPaths.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FolderViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_listview_dir, null);
                holder = new FolderViewHolder();
                holder.id_dir_item_image = (AppCompatImageView) convertView.findViewById(R.id.id_dir_item_image);
                holder.id_dir_item_name = (AppCompatTextView) convertView.findViewById(R.id.id_dir_item_name);
                holder.id_dir_item_count = (AppCompatTextView) convertView.findViewById(R.id.id_dir_item_count);
                holder.choose = (AppCompatImageView) convertView.findViewById(R.id.choose);
                convertView.setTag(holder);
            } else {
                holder = (FolderViewHolder) convertView.getTag();
            }
            ImageFolder item = mDirPaths.get(position);
            mImageLoader.displayImage("file://" + item.getFirstImagePath(), holder.id_dir_item_image, options);
            holder.id_dir_item_count.setText(item.images.size() + "张");
            holder.id_dir_item_name.setText(item.getName());
            holder.choose.setVisibility(currentImageFolder == item ? View.VISIBLE : View.GONE);
            return convertView;
        }
    }

    /**
     * 获取缩略图
     */
    private void getThumbnail() {
        Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.ImageColumns.DATA}, "", null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
        Log.i("TAG", mCursor.getCount() + "");
        if (mCursor.moveToFirst()) {
            int _date = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                // 获取图片的路径
                String path = mCursor.getString(_date);
                Log.i("TAG", path);
                imageAll.images.add(new ImageItem(path));
                // 获取该图片的父路径名
                File parentFile = new File(path).getParentFile();
                if (parentFile == null) {
                    continue;
                }
                ImageFolder imageFolder = null;
                String dirPath = parentFile.getAbsolutePath();
                if (!tmpDir.containsKey(dirPath)) {
                    // 初始化imageFolder
                    imageFolder = new ImageFolder();
                    imageFolder.setDir(dirPath);
                    imageFolder.setFirstImagePath(path);
                    mDirPaths.add(imageFolder);
                    Log.i(TAG, dirPath + "," + path);
                    tmpDir.put(dirPath, mDirPaths.indexOf(imageFolder));
                } else {
                    imageFolder = mDirPaths.get(tmpDir.get(dirPath));
                }
                imageFolder.images.add(new ImageItem(path));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        for (int i = 0; i < mDirPaths.size(); i++) {
            ImageFolder f = mDirPaths.get(i);
            Log.i(TAG, i + "-----" + f.getName() + "---" + f.images.size());
        }
        tmpDir = null;
    }

    /**
     * 图片文件夹类
     */
    private class ImageFolder {
        /**
         * 文件夹的名称3
         */
        private String name;

        /**
         * 图片的文件夹路径
         */
        private String dir;

        /**
         * 第一张图片的路径
         */
        private String firstImagePath;

        public List<ImageItem> images = new ArrayList<ImageItem>();

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
            int lastIndexOf = this.dir.lastIndexOf("/");
            this.name = this.dir.substring(lastIndexOf);
        }

        public String getFirstImagePath() {
            return firstImagePath;
        }

        public void setFirstImagePath(String firstImagePath) {
            this.firstImagePath = firstImagePath;
        }

        public String getName() {
            return name;
        }

    }

    private class ImageItem {
        String path;

        public ImageItem(String p) {
            this.path = p;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

}
