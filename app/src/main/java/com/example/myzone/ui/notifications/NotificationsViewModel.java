package com.example.myzone.ui.notifications;

import android.view.Gravity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.synnapps.carouselview.CarouselViewPager;
import com.synnapps.carouselview.CirclePageIndicator;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.util.Timer;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private final int DEFAULT_GRAVITY = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;

    private static final int DEFAULT_SLIDE_INTERVAL = 3500;
    private static final int DEFAULT_SLIDE_VELOCITY = 400;
    public static final int DEFAULT_INDICATOR_VISIBILITY = 0;


    private int mPageCount;
    private int slideInterval = DEFAULT_SLIDE_INTERVAL;
    private int mIndicatorGravity = DEFAULT_GRAVITY;
    private int indicatorMarginVertical;
    private int indicatorMarginHorizontal;
    private int pageTransformInterval = DEFAULT_SLIDE_VELOCITY;
    private int indicatorVisibility = DEFAULT_INDICATOR_VISIBILITY;

    private CarouselViewPager containerViewPager;
    private CirclePageIndicator mIndicator;
    private ViewListener mViewListener = null;
    private ImageListener mImageListener = null;
    private ImageClickListener imageClickListener = null;

    private Timer swipeTimer;
    private SwipeTask swipeTask;

    private boolean autoPlay;
    private boolean disableAutoPlayOnUserInteraction;
    private boolean animateOnBoundary = true;

    private int previousState;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}