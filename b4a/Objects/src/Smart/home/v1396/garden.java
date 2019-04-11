package Smart.home.v1396;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class garden extends Activity implements B4AActivity{
	public static garden mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "Smart.home.v1396", "Smart.home.v1396.garden");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (garden).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "Smart.home.v1396", "Smart.home.v1396.garden");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Smart.home.v1396.garden", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (garden) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (garden) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return garden.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (garden) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            garden mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (garden) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.randomaccessfile.AsyncStreams _sterem = null;
public static int _counter1 = 0;
public static int _counter2 = 0;
public static int _counter3 = 0;
public static int _counter4 = 0;
public static int _counter5 = 0;
public static int _counter6 = 0;
public static int _counter7 = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_garden = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button_garden = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_temp = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_him = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_date = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_time = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_door = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_kitchen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_out_light = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_out_door = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_night = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_night = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_bed_1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_bed_2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_bed1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_bed2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_garden = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_off_main = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_off_decor = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_off_ac = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_on_ac = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar_ac = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1_ac = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7_ac = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_room = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_on_main = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_on_decor = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_off_main = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_off_decor = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5_gaz = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_status_ac = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_status_light = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_status_door = null;
public Smart.home.v1396.main _main = null;
public Smart.home.v1396.ali _ali = null;
public Smart.home.v1396.starter _starter = null;
public Smart.home.v1396.door _door = null;
public Smart.home.v1396.gaed _gaed = null;
public Smart.home.v1396.gaedn _gaedn = null;
public Smart.home.v1396.frm _frm = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 66;BA.debugLine="Activity.LoadLayout(\"Garden\")";
mostCurrent._activity.LoadLayout("Garden",mostCurrent.activityBA);
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _button_back_click() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub Button_Back_Click";
 //BA.debugLineNum = 81;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _button_garden_click() throws Exception{
String _g = "";
byte[] _data = null;
 //BA.debugLineNum = 85;BA.debugLine="Sub Button_Garden_Click";
 //BA.debugLineNum = 87;BA.debugLine="COUNTER6 = COUNTER6 + 1";
_counter6 = (int) (_counter6+1);
 //BA.debugLineNum = 89;BA.debugLine="Dim G As String";
_g = "";
 //BA.debugLineNum = 90;BA.debugLine="G = COUNTER6 Mod 2";
_g = BA.NumberToString(_counter6%2);
 //BA.debugLineNum = 92;BA.debugLine="Try";
try { //BA.debugLineNum = 93;BA.debugLine="If G = 1 Then";
if ((_g).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 95;BA.debugLine="Label_status_light.Text = \"  bedroom 1 Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("  bedroom 1 Light On"));
 //BA.debugLineNum = 97;BA.debugLine="ImageView_Garden.SetBackgroundImage(LoadBitmap";
mostCurrent._imageview_garden.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 99;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 100;BA.debugLine="send_data(\"RS_Bed1on_RE\")";
_send_data("RS_Bed1on_RE");
 //BA.debugLineNum = 101;BA.debugLine="Sterem.Write(data)";
_sterem.Write(_data);
 }else {
 //BA.debugLineNum = 105;BA.debugLine="Label_status_light.Text =\"  bedroom 1 Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("  bedroom 1 Light Off"));
 //BA.debugLineNum = 107;BA.debugLine="ImageView_Garden.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_garden.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 109;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 110;BA.debugLine="send_data(\"RS_Bed1off_RE\")";
_send_data("RS_Bed1off_RE");
 //BA.debugLineNum = 111;BA.debugLine="Sterem.Write(data)";
_sterem.Write(_data);
 };
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 116;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private ImageView_Garden As ImageView";
mostCurrent._imageview_garden = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Button_Garden As Button";
mostCurrent._button_garden = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private ImageView_Garden As ImageView";
mostCurrent._imageview_garden = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Lbl_temp As Label";
mostCurrent._lbl_temp = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Lbl_Him As Label";
mostCurrent._lbl_him = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Lbl_date As Label";
mostCurrent._lbl_date = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Lbl_Time As Label";
mostCurrent._lbl_time = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Btn_Door As Button";
mostCurrent._btn_door = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Btn_Kitchen As Button";
mostCurrent._btn_kitchen = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Btn_Out_Light As Button";
mostCurrent._btn_out_light = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ImageView_Out_Door As ImageView";
mostCurrent._imageview_out_door = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private Btn_Night As Button";
mostCurrent._btn_night = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private ImageView_Night As ImageView";
mostCurrent._imageview_night = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Btn_Bed_1 As Button";
mostCurrent._btn_bed_1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Btn_Bed_2 As Button";
mostCurrent._btn_bed_2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private ImageView_Bed1 As ImageView";
mostCurrent._imageview_bed1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private ImageView_Bed2 As ImageView";
mostCurrent._imageview_bed2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Btn_Garden As Button";
mostCurrent._btn_garden = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private ImageView_off_main As ImageView";
mostCurrent._imageview_off_main = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private ImageView_off_decor As ImageView";
mostCurrent._imageview_off_decor = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private Btn_off_AC As Button";
mostCurrent._btn_off_ac = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private Btn_on_AC As Button";
mostCurrent._btn_on_ac = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private SeekBar_AC As SeekBar";
mostCurrent._seekbar_ac = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private ImageView1_AC As ImageView";
mostCurrent._imageview1_ac = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private Label7_AC As Label";
mostCurrent._label7_ac = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private ImageView_Room As ImageView";
mostCurrent._imageview_room = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private Btn_on_main As Button";
mostCurrent._btn_on_main = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Btn_on_Decor As Button";
mostCurrent._btn_on_decor = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Btn_off_main As Button";
mostCurrent._btn_off_main = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Btn_off_Decor As Button";
mostCurrent._btn_off_decor = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Label5_Gaz As Label";
mostCurrent._label5_gaz = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private Label_status_AC As Label";
mostCurrent._label_status_ac = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private Label_status_light As Label";
mostCurrent._label_status_light = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private Label_status_door As Label";
mostCurrent._label_status_door = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim Sterem As AsyncStreams";
_sterem = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 9;BA.debugLine="Dim  COUNTER1 As Int = 1";
_counter1 = (int) (1);
 //BA.debugLineNum = 10;BA.debugLine="Dim  COUNTER2 As Int = 1";
_counter2 = (int) (1);
 //BA.debugLineNum = 11;BA.debugLine="Dim  COUNTER3 As Int = 1";
_counter3 = (int) (1);
 //BA.debugLineNum = 12;BA.debugLine="Dim  COUNTER4 As Int = 1";
_counter4 = (int) (1);
 //BA.debugLineNum = 13;BA.debugLine="Dim  COUNTER5 As Int = 1";
_counter5 = (int) (1);
 //BA.debugLineNum = 14;BA.debugLine="Dim  COUNTER6 As Int = 1";
_counter6 = (int) (1);
 //BA.debugLineNum = 15;BA.debugLine="Dim  COUNTER7 As Int = 1";
_counter7 = (int) (1);
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _send_data(String _go) throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 122;BA.debugLine="Sub send_data(go As String)";
 //BA.debugLineNum = 124;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 125;BA.debugLine="data = go.GetBytes(\"UTF-8\")";
_data = _go.getBytes("UTF-8");
 //BA.debugLineNum = 126;BA.debugLine="Sterem.Write(data)";
_sterem.Write(_data);
 //BA.debugLineNum = 127;BA.debugLine="Log(go)";
anywheresoftware.b4a.keywords.Common.Log(_go);
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
}
