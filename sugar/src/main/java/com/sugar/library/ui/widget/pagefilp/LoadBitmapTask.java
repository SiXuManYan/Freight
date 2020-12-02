/*
 * Copyright (C) 2016 eschao <esc.chao@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sugar.library.ui.widget.pagefilp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.sugar.library.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * A singleton thread task to load bitmap
 * <p>Attempt to load bitmap in separate thread to get better performance.</p>
 *
 * @author eschao
 */

public final class LoadBitmapTask implements Runnable {

    private final static String TAG = "LoadBitmapTask";
    private static LoadBitmapTask __object;

    //    final static int SMALL_BG = 0;
//    final static int MEDIUM_BG = 1;
//    final static int LARGE_BG = 2;
    final static int BG_COUNT = 10;
    private static Context mContext;

    int mQueueMaxSize;
//    int mPreRandomNo;

    boolean mIsLandscape;
    boolean mStop;

    Thread mThread;

    LinkedList<Bitmap> mQueue;
    private ArrayList<String> mImageUrls;
    private int index = 0;

    /**
     * 获取唯一的任务对象
     *
     * @param context Android context
     * @return unique task object
     */
    public static LoadBitmapTask get(Context context) {
        mContext = context;
        if (__object == null) {
            __object = new LoadBitmapTask(context);
        }
        return __object;
    }


    public void initImageUrls(ArrayList<String> imageUrls) {
        mImageUrls = imageUrls;
    }

    /**
     * Constructor
     *
     * @param context Android context
     */
    private LoadBitmapTask(Context context) {
        mStop = false;
        mThread = null;
//        mPreRandomNo = 0;

        mIsLandscape = false;
        mQueueMaxSize = 1;
        mQueue = new LinkedList<Bitmap>();
    }


    /**
     * 获取bitmap以显示
     * 如果没有缓存的bitmap，它将立即加载一个
     *
     * @return bitmap
     */
    public Bitmap getBitmap(int number) {
        this.index = number;

        Bitmap b = null;
        synchronized (this) {
            if (mQueue.size() > 0) {
                b = mQueue.pop();
                // 根据下标获取元素
            }
            notify();
        }

        if (b == null) {
            Log.d(TAG, "立即加载 bitmap !");
            b = getRandomBitmap(number);
        }

        return b;
    }

    /**
     * Is task running?
     *
     * @return true if task is running
     */
    public boolean isRunning() {
        return mThread != null && mThread.isAlive();
    }

    /**
     * Start task
     */
    public synchronized void start() {
        if (mThread == null || !mThread.isAlive()) {
            mStop = false;
            mThread = new Thread(this);
            mThread.start();
        }
    }

    /**
     * Stop task
     * <p>Set mStop flag with true and notify task thread, at last, it will
     * check if task is alive every 500ms with 3 times to make sure the thread
     * stop</p>
     */
    public void stop() {
        synchronized (this) {
            mStop = true;
            notify();
        }

        // wait for thread stopping
        for (int i = 0; i < 3 && mThread.isAlive(); ++i) {
            Log.d(TAG, "Waiting thread to stop ...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {

            }
        }

        if (mThread.isAlive()) {
            Log.d(TAG, "Thread is still alive after waited 1.5s!");
        }
    }

    /**
     * 设置位图的宽度，高度。和缓存队列的最大大小
     *
     * @param w         width of bitmap
     * @param h         height of bitmap
     * @param maxCached maximum size of cache queue
     */
    public void set(int w, int h, int maxCached) {

        mIsLandscape = w > h;

        if (maxCached != mQueueMaxSize) {
            mQueueMaxSize = maxCached;
        }

    }

    /**
     * 从 resources 中随机加载 bitmap
     *
     * @return bitmap object
     */
    private Bitmap getRandomBitmap(int number) {


        FutureTarget<Bitmap> futureBitmap = Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.ic_net_error)
                .fallback(R.drawable.ic_net_error)
                .error(R.drawable.ic_net_error)
//                .load(mImageUrls.get(number))
                .load(R.drawable.img_data_no_found)
                .submit(500, 500);
        try {
            return futureBitmap.get(2, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Clear cache queue
     */
    private void cleanQueue() {
        for (int i = 0; i < mQueue.size(); ++i) {
            mQueue.get(i).recycle();
        }
        mQueue.clear();
    }

    public void run() {

        while (true) {
            synchronized (this) {
                // check if ask thread stopping
                if (mStop) {
                    cleanQueue();
                    break;
                }
                // 仅当队列中没有缓存的 bitmap 时才加载 bitmap
                int size = mQueue.size();
                if (size < 1) {
                    for (int i = 0; i < mImageUrls.size(); ++i) {
                        Log.d(TAG, "Load Queue:" + i + " in background!");
//                        mQueue.push(getRandomBitmap(i));
                        mQueue.add(getRandomBitmap(i));
                    }
                }

                // wait to be awaken
                try {
                    wait();
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}
