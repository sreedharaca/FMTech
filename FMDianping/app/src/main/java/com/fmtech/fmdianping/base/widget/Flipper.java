package com.fmtech.fmdianping.base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.fmtech.fmdianping.R;
import com.fmtech.fmdianping.widget.NavigationDot;

/**
 * ==================================================================
 * Copyright (C) 2015 FMTech All Rights Reserved.
 *
 * @author Drew.Chiang
 * @version v1.0.0
 * @email chiangchuna@gmail.com
 * @create_date 2015/11/13 21:50
 * @description ${todo}
 * <p/>
 * Modification History:
 * Date            Author            Version         Description
 * -----------------------------------------------------------------
 * 2015/11/13 21:50  Drew.Chiang       v1.0.0          create
 * <p/>
 * ==================================================================
 */

public class Flipper<T> extends FrameLayout{

    protected static final int ANIM_NONE = 0;
    private static final int ANIM_TRANS = 2;
    private static final int ANIM_TRANS_DURATION1 = 30;
    private static final int ANIM_TRANS_DURATION2 = 150;
    private static final int ANIM_TRANS_TO_NEXT = 1;
    private static final int ANIM_TRANS_TO_PREVIOUS = -1;
    private static final int FLING_VELOCITY = 500;
    protected FlipperAdapter<T> mAdapter;
    private int mAnimDuration;
    private int mAnimMode = 0;
    private long mAnimStartTimeMs;
    private float mAnimStartX;
    private float mAnimEndX;
    private float mWidth;
    private T mCurrentItem;
    protected View mCurrentView;
    private float mScrolledDistance = 0.0F;
    protected boolean isScrolling;
    private int mItemSpaceAdjust;
    public NavigationDot mNavigationDot;
    protected T mNextItem;
    protected View mNextView;
    protected T mPreviousItem;
    protected View mPreviousView;
    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            System.out.println("-------OnGestureListener:onFling()");
            Flipper.this.onFling(velocityX);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
//            System.out.println("-------OnGestureListener:onScroll()");
			if(mPreviousView == null && mNextView == null){
//                System.out.println("-------OnGestureListener:mPreviousView == null && mNextView == null");
				isScrolling = false;
				return true;
			}

			isScrolling = true;
			onScrollX(event1, event2, distanceX);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
//            System.out.println("-------OnGestureListener:onShowPress()");
            mAnimMode = 0;
            super.onShowPress(e);
        }

        /**
         * The user has performed a down {@link MotionEvent} and not performed
         * a move or up yet. This event is commonly used to provide visual
         * feedback to the user to let them know that their action has been
         * recognized i.e. highlight an element.
         *
         * @param e The down motion event
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
//            System.out.println("-------OnGestureListener:onSingleTapUp()");
            Flipper.this.onSingleTap();
            return super.onSingleTapUp(e);
        }
    };
    private GestureDetector mGestureDetector = new GestureDetector(mGestureListener);

    public Flipper(Context context) {
        super(context);
    }

    public Flipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        System.out.println("-------dispatchTouchEvent");
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
		}
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
//        System.out.println("-------onTouchEvent");
        /**
         * Analyzes the given motion event and if applicable triggers the
         * appropriate callbacks on the {@link OnGestureListener} supplied.
         *
         * @param ev The current motion event.
         * @return true if the {@link OnGestureListener} consumed the event,
         *              else false.
         */
		if(!mGestureDetector.onTouchEvent(event)){
			if(event.getAction() == MotionEvent.ACTION_UP && (isScrolling)){
                onScrollXEnd();
                isScrolling = false;
            }

            if(event.getAction() == MotionEvent.ACTION_CANCEL){
                onScrollXEnd();
                isScrolling = false;
            }

            return true;
		}
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        System.out.println("-------dispatchDraw");
        if(mAnimMode == ANIM_TRANS_TO_NEXT || mAnimMode == ANIM_TRANS_TO_PREVIOUS || mAnimMode == ANIM_TRANS) {
            long currentTimeMs = AnimationUtils.currentAnimationTimeMillis();
            if (currentTimeMs <= (mAnimStartTimeMs + mAnimDuration)) {
                float scrollPercent = (float) (currentTimeMs - mAnimStartTimeMs) / mAnimDuration;
//                System.out.println("-------scrollPercent:"+scrollPercent);
                if(mAnimMode == ANIM_TRANS){
                    mScrolledDistance = mAnimStartX - (scrollPercent * (mAnimStartX - mAnimEndX));
                }else{
                    mScrolledDistance = mAnimStartX + (scrollPercent * (mAnimStartX - mAnimEndX));
                }
//                System.out.println("-------dispatchDraw， mScrolledDistance: " + mScrolledDistance);
                invalidate();
            }else{
                mScrolledDistance = 0.0f;
                mAnimMode = ANIM_NONE;
            }
        }
//        System.out.println("-------dispatchDraw， mScrolledDistance: " + mScrolledDistance);
        super.dispatchDraw(canvas);
    }

	/**
	*@return True if an invalidate() was issued
	*/
    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//        System.out.println("-------drawChild");
		boolean invalidateIssued = false;
		if(child instanceof NavigationDot){
//            System.out.println("-------NavigationDot, NavigationDot.getTop: "+child.getTop());
			invalidateIssued = super.drawChild(canvas, child, drawingTime);
		}

		if(child == mPreviousView){
			canvas.save();
			canvas.translate(-getWidth() - mScrolledDistance, 0.0F);
			invalidateIssued = super.drawChild(canvas, child, drawingTime);
			canvas.restore();
		}
		
		if(child == mCurrentView){
			canvas.save();
			canvas.translate(0.0F -mScrolledDistance, 0.0F);
			invalidateIssued = super.drawChild(canvas, child, drawingTime);
			canvas.restore();
		}
		
		if(child == mNextView){
			canvas.save();
			canvas.translate(getWidth() -mScrolledDistance, 0.0F);
			invalidateIssued = super.drawChild(canvas, child,drawingTime);
            canvas.restore();
		}
        return invalidateIssued;
    }

    public void enableNavigationDotView(int viewCount){
        if(viewCount > 1){
//            System.out.println("-------null == mNavigationDot: "+(null == mNavigationDot));
            if(null == mNavigationDot){
                mNavigationDot = (NavigationDot)View.inflate(getContext(), R.layout.navigation_dots, null);
                addView(mNavigationDot);
            }
            mNavigationDot.setDotCount(viewCount);
        }
    }

    public void onFling(float velocityX){
        System.out.println("-------velocityX: "+velocityX);
        mWidth = getWidth();
        if(velocityX < -FLING_VELOCITY || mScrolledDistance > mWidth/2){
            moveToNext(true);
        }else if(velocityX > FLING_VELOCITY || mScrolledDistance < -mWidth/2){
            moveToPrevious(true);
        }else{
            restorePosition(true);
        }
    }

    public void onSingleTap(){
        mAdapter.onTap(mCurrentItem);
    }

    public void onScrollX(MotionEvent e1, MotionEvent e2, float distanceX){
//        System.out.println("-------onScrollX, distanceX:　"+ distanceX);
        if((distanceX <0 &&  null == mPreviousView && mScrolledDistance <= 0)|| (distanceX >0 && null == mNextView && mScrolledDistance >= 0)){
            distanceX = 0.0f;
        }
        mScrolledDistance = (mScrolledDistance + distanceX);
		invalidate();
    }

    public void onScrollXEnd(){
//        System.out.println("-------onScrollXEnd, mScrolledDistance:　"+ mScrolledDistance);
        mWidth = getWidth();
        if(mScrolledDistance > mWidth/2.0F){
            moveToNext(true);
            return;
        }

        if(mScrolledDistance <-mWidth/2.0F){
            moveToPrevious(true);
            return;
        }

        restorePosition(true);
    }

    public void onTap(){

    }

	private boolean isEquals(Object obj1, Object obj2){
		return obj1 == obj2;
	}
	
    public void restorePosition(boolean restore){
        if(mScrolledDistance == 0.0f){
            return;
        }
        if(restore){
            mAnimStartX = mScrolledDistance;
            mAnimEndX = 0;
            mAnimMode = ANIM_TRANS;
            mAnimStartTimeMs = AnimationUtils.currentAnimationTimeMillis();
            mAnimDuration = (ANIM_TRANS_DURATION1 + (int)(120.0F * (Math.abs(mAnimStartX) / mWidth)));
            invalidate();
            return;
        }
        mScrolledDistance = 0.0f;
        mAnimMode = ANIM_NONE;
        invalidate();
    }

    /**
     * moveing direction left.
     * @param restore
     * @return
     */
    public boolean moveToNext(boolean restore){
        if(null != mNextView) {
            if(null != mPreviousView){
                removeView(mPreviousView);
            }
            mPreviousView = mCurrentView;
            mPreviousItem = mCurrentItem;
            mCurrentView = mNextView;
            mCurrentItem = mNextItem;
            mNextItem = mAdapter.getNextItem(mCurrentItem);
            mNextView = mAdapter.getView(mNextItem, null);
            if (null != mNextView) {
                addView(mNextView);
            }
            mAnimStartX = mWidth - mScrolledDistance;
            mAnimEndX = 0;
            mAnimMode = ANIM_TRANS_TO_NEXT;
            mAnimStartTimeMs = AnimationUtils.currentAnimationTimeMillis();
            mAnimDuration = (ANIM_TRANS_DURATION1 + (int) (120.0F * (Math.abs(mAnimStartX) / mWidth)));
            invalidate();
            if(null != mNavigationDot){
                mNavigationDot.moveToNext();
            }
            return true;
        }

        return false;
    }

    /**
     * moving direction right
     * @param restore
     * @return
     */
    public boolean moveToPrevious(boolean restore){
        if(null != mPreviousView) {
            if(null != mNextView){
                removeView(mNextView);
            }
            mNextItem = mCurrentItem;
            mNextView = mCurrentView;
            mCurrentItem = mPreviousItem;
            mCurrentView = mPreviousView;
            mPreviousItem = mAdapter.getPreviousItem(mCurrentItem);
            mPreviousView = mAdapter.getView(mPreviousItem, null);
            if (null != mPreviousView) {
                addView(mPreviousView);
            }
            mAnimStartX = mWidth + mScrolledDistance;
            mAnimEndX = 0;
            mAnimMode = ANIM_TRANS_TO_PREVIOUS;
            mAnimStartTimeMs = AnimationUtils.currentAnimationTimeMillis();
            mAnimDuration = (ANIM_TRANS_DURATION1 + (int) (120.0F * (Math.abs(mAnimStartX) / mWidth)));
            invalidate();

            if (null != mNavigationDot) {
                mNavigationDot.moveToPrevious();
            }
        }
        return false;
    }

    public void setAdapter(FlipperAdapter<T> adapter){
        mAdapter = adapter;
    }

    public void setCurrentSelectDot(int index){
        if(null != mNavigationDot){
            mNavigationDot.setCurrentSelectedDot(index);
        }

    }

    public void setCurrentItem(T currentItem){
		if(null == mAdapter){
			return;
		}
		Object objPre = mPreviousItem;
		Object objCurr = mCurrentItem;
		Object objNext = mNextItem;
		mCurrentItem = currentItem;
		
		if(!isEquals(objPre, mPreviousItem)){
			if(null != mPreviousView){
				removeView(mPreviousView);
			}
			if(null == mPreviousItem){
                mPreviousView = null;
			}
            mPreviousView = mAdapter.getView(mPreviousItem, mPreviousView);
			if(null != mPreviousView){
				addView(mPreviousView);
			}
		}
		
		if(!isEquals(objCurr, mCurrentItem)){
			if(null != mCurrentView){
				removeView(mCurrentView);		
			}
			if(null == mCurrentItem){
				mCurrentView = null;
			}
			mCurrentView = mAdapter.getView(mCurrentItem, mCurrentView);
			if(null != mCurrentView){
				addView(mCurrentView);
			}
		}
		
		if(!isEquals(objNext, mNextItem)){
			if(null != mNextView){
				removeView(mNextView);		
			}
			if(null == mNextItem){
				mNextView = null;
			}
			mNextView = mAdapter.getView(mNextItem, mNextView);
			if(null != mNextView){
				addView(mNextView);
			}
		}
		
    }

    public void setItemSpaceSpanAdjust(int spaceSpan){

    }

	/**
	*
	*/
    public void update(){
        if(null == mCurrentItem){
            return;
        }
		mPreviousItem = mAdapter.getPreviousItem(mCurrentItem);
        mNextItem = mAdapter.getNextItem(mCurrentItem);
        if(null != mCurrentView){
            removeView(mCurrentView);
        }
        mCurrentView = mAdapter.getView(mCurrentItem, mCurrentView);
        if(null != mCurrentView){
            addView(mCurrentView);
        }

        if(null != mPreviousView){
            removeView(mPreviousView);
        }
        mPreviousView = mAdapter.getView(mPreviousItem, mPreviousView);
        if(null != mPreviousView){
            addView(mPreviousView);
        }

        if(null != mNextView){
            removeView(mNextView);
        }
        mNextView = mAdapter.getView(mNextItem, mNextView);
        if(null != mNextView){
            addView(mNextView);
        }
    }
}
