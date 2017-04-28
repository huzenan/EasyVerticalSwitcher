package com.hzn.easyverticalswitcher;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewSwitcher;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 纵向滚动视图的基类，继承此类并实现{@link EasyVerticalSwitcher#updateView(Object, View)}方法进行视图更新即可
 * Created by huzn on 2017/4/28.
 */
public abstract class EasyVerticalSwitcher extends ViewSwitcher {

    private ArrayList<Object> dataList;
    private int curItem;
    private AutoSwitchHandler autoSwitchHandler;
    private long autoSwitchDelay;
    public static final long DEFAULT_AUTO_SWITCH_DELAY = 1000;

    public EasyVerticalSwitcher(Context context) {
        this(context, null);
    }

    public EasyVerticalSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.autoSwitchHandler = new AutoSwitchHandler(this);
        initData();
        initAnimation(context);
    }

    private void initData() {
        dataList = new ArrayList<>();
        curItem = -1;
        autoSwitchDelay = DEFAULT_AUTO_SWITCH_DELAY;
    }

    private void initAnimation(Context context) {
        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_in));
        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_out));
    }

    public void setView(final int layoutResId) {
        setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                return LayoutInflater.from(getContext()).inflate(layoutResId, null);
            }
        });
    }

    public void setDataList(ArrayList<Object> dataList) {
        if (null == dataList || dataList.size() <= 0)
            return;

        stopAutoSwitch();
        curItem = -1;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        showNextView();
    }

    /**
     * 播放动画并显示下一个视图，当视图数据dataList小于等于2条时不做处理
     */
    public void showNextView() {
        if (null == dataList || dataList.size() <= 2)
            return;

        curItem = curItem == dataList.size() - 1 ? 0 : curItem + 1;
        updateView(this.dataList.get(curItem), getNextView());
        showNext();
    }

    /**
     * 更新视图，在显示获取下一个视图前，更新下一个视图的内容
     *
     * @param data 数据项
     * @param view 下一个视图
     */
    public abstract void updateView(Object data, View view);

    /**
     * 设置自动切换的延迟时间
     *
     * @param delay 自动切换的延迟时间，单位ms
     */
    public void setAutoSwitchDelay(long delay) {
        autoSwitchDelay = delay;
    }

    /**
     * 开始自动切换
     */
    public void startAutoSwitch() {
        if (null == dataList || dataList.size() <= 1)
            return;

        autoSwitchHandler.removeMessages(0);
        autoSwitchHandler.sendEmptyMessageDelayed(0, autoSwitchDelay);
    }

    /**
     * 停止自动切换
     */
    public void stopAutoSwitch() {
        autoSwitchHandler.removeMessages(0);
    }

    private static class AutoSwitchHandler extends Handler {
        private WeakReference<EasyVerticalSwitcher> switcher;

        public AutoSwitchHandler(EasyVerticalSwitcher switcher) {
            this.switcher = new WeakReference<EasyVerticalSwitcher>(switcher);
        }

        @Override
        public void handleMessage(Message msg) {
            EasyVerticalSwitcher switcher = this.switcher.get();
            if (null != switcher) {
                switcher.showNextView();
                switcher.startAutoSwitch();
            }
        }
    }
}
