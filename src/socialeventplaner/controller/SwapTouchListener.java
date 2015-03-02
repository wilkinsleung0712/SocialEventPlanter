package socialeventplaner.controller;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SwapTouchListener implements OnTouchListener, 
OnGestureListener,
OnDoubleTapListener{

private final String DEBUG_TAG=this.getClass().getName();
private GestureDetectorCompat mDetector;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action =MotionEventCompat.getActionMasked(event);
		 switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	            Log.d(DEBUG_TAG,"Action was DOWN");
	            return true;
	        case (MotionEvent.ACTION_MOVE) :
	            Log.d(DEBUG_TAG,"Action was MOVE");
	            return true;
	        case (MotionEvent.ACTION_UP) :
	            Log.d(DEBUG_TAG,"Action was UP");
	            return true;
	        case (MotionEvent.ACTION_CANCEL) :
	            Log.d(DEBUG_TAG,"Action was CANCEL");
	            return true;
	        case (MotionEvent.ACTION_OUTSIDE) :
	            Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
	                    "of current screen element");
	            return true;      
	        default : 
	            return false;
	    }   
	}
	
	    @Override
	    public boolean onDown(MotionEvent event) { 
	        Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
	        return true;
	    }

	    @Override
	    public boolean onFling(MotionEvent event1, MotionEvent event2, 
	            float velocityX, float velocityY) {
	        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
	        return true;
	    }

	    @Override
	    public void onLongPress(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onLongPress: " + event.toString()); 
	    }

	    @Override
	    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	            float distanceY) {
	        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
	        return true;
	    }

	    @Override
	    public void onShowPress(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
	    }

	    @Override
	    public boolean onSingleTapUp(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
	        return true;
	    }

	    @Override
	    public boolean onDoubleTap(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
	        return true;
	    }

	    @Override
	    public boolean onDoubleTapEvent(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
	        return true;
	    }

	    @Override
	    public boolean onSingleTapConfirmed(MotionEvent event) {
	        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
	        return true;
	    }


}
