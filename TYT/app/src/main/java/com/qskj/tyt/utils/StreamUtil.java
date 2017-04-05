package com.qskj.tyt.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 赵 鑫 on 2015/10/21.
 */
public class StreamUtil {

    /**
     * 获取Asset内的文件 并保存到 sdCard/quanshuntong 文件夹
     *
     * @param filename 必须是完整文件名（文件名+格式）
     */
    public static void getFileFromAssetAndSaveToSdCard(final String filename, Context context) {
        try {
            //获取指定Assets 字节流
            InputStream is = context.getResources().getAssets().open(filename);

            //检查是否存在sd卡
            String status = Environment.getExternalStorageState();
            if (!status.equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(context, "请插入sd卡", Toast.LENGTH_LONG).show();
                return;
            }

            /**
             * 在Android中1.5、1.6的sdcard目录为/sdcard，而Android2.0以上都是/mnt/sdcard，因此如果我们在保存时直接写具体目录会不妥，因此我们可以使用:
             * Environment.getExternalStorageDirectory();获取sdcard目录；
             */
            String directory = Environment.getExternalStorageDirectory().toString() + "/quanshuntong";
            File rootFile = new File(directory);
            //如不存在文件夹，则新建文件夹 quanshuntong
            if (!rootFile.exists())
                rootFile.mkdir();
            //在文件夹下加入获取的文件
            File file = new File(directory, filename);
            try {
                //文件输出流
                FileOutputStream fos = new FileOutputStream(file);
                // 将输入流is写入文件输出流fos中
                int ch = 0;
                try {
                    while ((ch = is.read()) != -1) {
                        fos.write(ch);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    fos.close();
                    is.close();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
