package com.whitefm.utils.toast;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtilAdapter {
	private static ToastUtilAdapter instance;
	
	private Toast toast;
	private Context context;
	private Handler handler;
	
	private ToastUtilAdapter (Context context){
		this.context = context;
		handler = new Handler();
	}
	
	public static ToastUtilAdapter getInstance(Context context){
		if(instance == null){
			synchronized (ToastUtilAdapter.class) {
				if(instance == null){
					instance = new ToastUtilAdapter(context);
				}
			}
		}
		return instance;
	}

	public void toast(final String toastMsg){
		if(context == null){
			return ;
		}
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (toast == null) {  
					toast = Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT);
		        } else {  
		        	toast.setText(toastMsg);  
		        	toast.setDuration( Toast.LENGTH_SHORT);
		        }  
				toast.show();  
		        
				/*if(toast != null){
					toast.cancel();
					toast = null;
				}
				toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
				toast.setText(toastMsg);
				toast.show();*/
			}
		});
	}
	
	public void toast(int resId){
		if(context == null){
			return ;
		}
		toast( context.getResources().getString(resId));
	}
	
}
