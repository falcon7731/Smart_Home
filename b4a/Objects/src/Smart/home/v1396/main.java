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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "Smart.home.v1396", "Smart.home.v1396.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "Smart.home.v1396", "Smart.home.v1396.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Smart.home.v1396.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_garden = null;
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
public anywheresoftware.b4a.objects.ButtonWrapper _btn_security = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1_security = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_secur = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_lok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_room_on = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_room_off = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button_ali = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_secu_text = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_connect = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_disconnect = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_exit = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_cell = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_100 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_200 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_300 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_400 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview_garden_dry = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_ac712 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_soild = null;
public anywheresoftware.b4a.objects.Serial _s1 = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.student.PersianDate _date1 = null;
public anywheresoftware.b4a.randomaccessfile.AsyncStreams _sterem = null;
public static String _temp = "";
public static String _hum = "";
public static String _gaz = "";
public static String _secu = "";
public static String _cell = "";
public static String _soild = "";
public static String _ac712 = "";
public static int _counter1 = 0;
public static int _counter3 = 0;
public static int _counter4 = 0;
public static int _counter5 = 0;
public static int _counter6 = 0;
public static int _counter7 = 0;
public static int _counter8 = 0;
public anywheresoftware.b4a.phone.Phone.PhoneVibrate _vibra = null;
public static String _speed1 = "";
public anywheresoftware.b4a.audio.Beeper _b1 = null;
public static String _m2 = "";
public static String _c = "";
public Smart.home.v1396.ali _ali = null;
public Smart.home.v1396.starter _starter = null;
public Smart.home.v1396.door _door = null;
public Smart.home.v1396.garden _garden = null;
public Smart.home.v1396.gaed _gaed = null;
public Smart.home.v1396.gaedn _gaedn = null;
public Smart.home.v1396.frm _frm = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (door.mostCurrent != null);
vis = vis | (garden.mostCurrent != null);
vis = vis | (gaed.mostCurrent != null);
vis = vis | (gaedn.mostCurrent != null);
vis = vis | (frm.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 133;BA.debugLine="Activity.LoadLayout(\"Main\")";
mostCurrent._activity.LoadLayout("Main",mostCurrent.activityBA);
 //BA.debugLineNum = 135;BA.debugLine="s1.Initialize(\"Bluetooth\")";
mostCurrent._s1.Initialize("Bluetooth");
 //BA.debugLineNum = 139;BA.debugLine="Btn_on_Decor.TextColor = 0xFFFCFFFE";
mostCurrent._btn_on_decor.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 140;BA.debugLine="Btn_on_Decor.Enabled = True";
mostCurrent._btn_on_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 141;BA.debugLine="Btn_on_main.TextColor = 0xFFFCFFFE";
mostCurrent._btn_on_main.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 147;BA.debugLine="If s1.IsEnabled = False Then";
if (mostCurrent._s1.IsEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 149;BA.debugLine="Msgbox(\"Please turn on your Blutooth mobile phon";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please turn on your Blutooth mobile phone"),BA.ObjectToCharSequence("Notice"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _bluetooth_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Sub Bluetooth_Connected (Success As Boolean)";
 //BA.debugLineNum = 159;BA.debugLine="If Success = True Then";
if (_success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 161;BA.debugLine="Sterem.Initialize(s1.InputStream ,s1.OutputStre";
mostCurrent._sterem.Initialize(processBA,mostCurrent._s1.getInputStream(),mostCurrent._s1.getOutputStream(),"DATE");
 //BA.debugLineNum = 163;BA.debugLine="Btn_Connect.Enabled = False";
mostCurrent._btn_connect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 164;BA.debugLine="Btn_Connect.TextColor = 0xFF020202";
mostCurrent._btn_connect.setTextColor((int) (0xff020202));
 //BA.debugLineNum = 167;BA.debugLine="Btn_DisConnect.Enabled = True";
mostCurrent._btn_disconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 168;BA.debugLine="Btn_DisConnect.TextColor = 0xFFFFFFFF";
mostCurrent._btn_disconnect.setTextColor((int) (0xffffffff));
 //BA.debugLineNum = 172;BA.debugLine="ToastMessageShow(\"Its Success\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Its Success"),anywheresoftware.b4a.keywords.Common.False);
 }else if(_success==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 176;BA.debugLine="Btn_Connect.Enabled = True";
mostCurrent._btn_connect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 179;BA.debugLine="ToastMessageShow(\"Its failed\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Its failed"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static String  _btn_bed_1_click() throws Exception{
String _g = "";
byte[] _data = null;
 //BA.debugLineNum = 793;BA.debugLine="Sub Btn_Bed_1_Click";
 //BA.debugLineNum = 795;BA.debugLine="COUNTER6 = COUNTER6 + 1";
_counter6 = (int) (_counter6+1);
 //BA.debugLineNum = 797;BA.debugLine="Dim G As String";
_g = "";
 //BA.debugLineNum = 798;BA.debugLine="G = COUNTER6 Mod 2";
_g = BA.NumberToString(_counter6%2);
 //BA.debugLineNum = 801;BA.debugLine="If G = 1 Then";
if ((_g).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 803;BA.debugLine="Label_status_light.Text = \"  bedroom 1 Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("  bedroom 1 Light On"));
 //BA.debugLineNum = 805;BA.debugLine="ImageView_Bed1.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 807;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 808;BA.debugLine="send_data(\"RS_Bed1on_RE\")";
_send_data("RS_Bed1on_RE");
 //BA.debugLineNum = 809;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 }else {
 //BA.debugLineNum = 813;BA.debugLine="Label_status_light.Text =\"  bedroom 1 Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("  bedroom 1 Light Off"));
 //BA.debugLineNum = 815;BA.debugLine="ImageView_Bed1.SetBackgroundImage(LoadBitmap";
mostCurrent._imageview_bed1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 817;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 818;BA.debugLine="send_data(\"RS_Bed1off_RE\")";
_send_data("RS_Bed1off_RE");
 //BA.debugLineNum = 819;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 };
 //BA.debugLineNum = 823;BA.debugLine="End Sub";
return "";
}
public static String  _btn_bed_2_click() throws Exception{
String _f = "";
byte[] _data = null;
 //BA.debugLineNum = 762;BA.debugLine="Sub Btn_Bed_2_Click";
 //BA.debugLineNum = 764;BA.debugLine="COUNTER5 = COUNTER5 + 1";
_counter5 = (int) (_counter5+1);
 //BA.debugLineNum = 766;BA.debugLine="Dim F As String";
_f = "";
 //BA.debugLineNum = 767;BA.debugLine="F= COUNTER5 Mod 2";
_f = BA.NumberToString(_counter5%2);
 //BA.debugLineNum = 769;BA.debugLine="If F = 1 Then";
if ((_f).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 771;BA.debugLine="Label_status_light.Text = \"  bedroom 2 Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("  bedroom 2 Light On"));
 //BA.debugLineNum = 773;BA.debugLine="ImageView_Bed2.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 775;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 776;BA.debugLine="send_data(\"RS_Bed2on_RE\")";
_send_data("RS_Bed2on_RE");
 //BA.debugLineNum = 777;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 }else {
 //BA.debugLineNum = 781;BA.debugLine="Label_status_light.Text =\" bedroom 2 Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence(" bedroom 2 Light Off"));
 //BA.debugLineNum = 783;BA.debugLine="ImageView_Bed2.SetBackgroundImage(LoadBitma";
mostCurrent._imageview_bed2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 785;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 786;BA.debugLine="send_data(\"RS_Bed2off_RE\")";
_send_data("RS_Bed2off_RE");
 //BA.debugLineNum = 787;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 };
 //BA.debugLineNum = 791;BA.debugLine="End Sub";
return "";
}
public static String  _btn_connect_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _m1 = null;
anywheresoftware.b4a.objects.collections.List _l1 = null;
int _result = 0;
int _i = 0;
 //BA.debugLineNum = 198;BA.debugLine="Sub Btn_Connect_Click";
 //BA.debugLineNum = 200;BA.debugLine="Try";
try { //BA.debugLineNum = 201;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 202;BA.debugLine="Dim l1 As List";
_l1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 203;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 205;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 206;BA.debugLine="l1.Initialize";
_l1.Initialize();
 //BA.debugLineNum = 208;BA.debugLine="m1 = s1.GetPairedDevices";
_m1 = mostCurrent._s1.GetPairedDevices();
 //BA.debugLineNum = 209;BA.debugLine="For i = 0 To m1.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (_m1.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 210;BA.debugLine="l1.Add(m1.GetKeyAt(i))";
_l1.Add(_m1.GetKeyAt(_i));
 }
};
 //BA.debugLineNum = 213;BA.debugLine="result = InputList(l1,\"Select One Of Device\",-1)";
_result = anywheresoftware.b4a.keywords.Common.InputList(_l1,BA.ObjectToCharSequence("Select One Of Device"),(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 215;BA.debugLine="s1.Connect(m1.Get(l1.Get(result)))     'Connect";
mostCurrent._s1.Connect(processBA,BA.ObjectToString(_m1.Get(_l1.Get(_result))));
 //BA.debugLineNum = 220;BA.debugLine="Btn_Door.Enabled = True";
mostCurrent._btn_door.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 221;BA.debugLine="Btn_Garden.Enabled = True";
mostCurrent._btn_garden.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 222;BA.debugLine="Btn_Kitchen.Enabled = True";
mostCurrent._btn_kitchen.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 223;BA.debugLine="Btn_Security.Enabled = True";
mostCurrent._btn_security.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 224;BA.debugLine="Btn_off_AC.Enabled = True";
mostCurrent._btn_off_ac.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 225;BA.debugLine="Btn_on_AC.Enabled = True";
mostCurrent._btn_on_ac.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 226;BA.debugLine="Btn_Out_Light.Enabled = True";
mostCurrent._btn_out_light.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 227;BA.debugLine="Btn_Night.Enabled = True";
mostCurrent._btn_night.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 228;BA.debugLine="Btn_Bed_1.Enabled = True";
mostCurrent._btn_bed_1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 229;BA.debugLine="Btn_Bed_2.Enabled = True";
mostCurrent._btn_bed_2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 230;BA.debugLine="Btn_off_Decor.Enabled = True";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 231;BA.debugLine="Btn_off_main.Enabled = True";
mostCurrent._btn_off_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 232;BA.debugLine="Btn_on_main.Enabled = True";
mostCurrent._btn_on_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 233;BA.debugLine="Btn_Room_off.Enabled = True";
mostCurrent._btn_room_off.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 234;BA.debugLine="Btn_Room_on.Enabled = True";
mostCurrent._btn_room_on.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 } 
       catch (Exception e29) {
			processBA.setLastException(e29); //BA.debugLineNum = 238;BA.debugLine="Msgbox(\"Hardware not connected\", \"Notice\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Hardware not connected"),BA.ObjectToCharSequence("Notice"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _btn_disconnect_click() throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub Btn_DisConnect_Click";
 //BA.debugLineNum = 247;BA.debugLine="Btn_Connect.Enabled = True";
mostCurrent._btn_connect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 248;BA.debugLine="Btn_Connect.TextColor = 0xFFFD00FD";
mostCurrent._btn_connect.setTextColor((int) (0xfffd00fd));
 //BA.debugLineNum = 250;BA.debugLine="Btn_DisConnect.TextColor = 0xFFFCE100";
mostCurrent._btn_disconnect.setTextColor((int) (0xfffce100));
 //BA.debugLineNum = 254;BA.debugLine="ToastMessageShow(\"Your connection via Bluetooth d";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Your connection via Bluetooth disconnected"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 256;BA.debugLine="s1.Disconnect";
mostCurrent._s1.Disconnect();
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _btn_door_click() throws Exception{
String _a = "";
byte[] _data = null;
 //BA.debugLineNum = 495;BA.debugLine="Sub Btn_Door_Click";
 //BA.debugLineNum = 498;BA.debugLine="COUNTER1 = COUNTER1 + 1";
_counter1 = (int) (_counter1+1);
 //BA.debugLineNum = 500;BA.debugLine="Dim A As String";
_a = "";
 //BA.debugLineNum = 501;BA.debugLine="A = COUNTER1 Mod 2";
_a = BA.NumberToString(_counter1%2);
 //BA.debugLineNum = 503;BA.debugLine="If A = 1 Then";
if ((_a).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 505;BA.debugLine="Btn_Door.Text = \"Close \"";
mostCurrent._btn_door.setText(BA.ObjectToCharSequence("Close "));
 //BA.debugLineNum = 506;BA.debugLine="Label_status_door.Text =\"Close Door\"";
mostCurrent._label_status_door.setText(BA.ObjectToCharSequence("Close Door"));
 //BA.debugLineNum = 508;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 509;BA.debugLine="send_data(\"RS_DoorClose_RE\")";
_send_data("RS_DoorClose_RE");
 //BA.debugLineNum = 510;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 512;BA.debugLine="b1.Initialize(1000,1500)";
mostCurrent._b1.Initialize((int) (1000),(int) (1500));
 //BA.debugLineNum = 513;BA.debugLine="b1.Beep";
mostCurrent._b1.Beep();
 //BA.debugLineNum = 515;BA.debugLine="Label_status_light.Text = \"Out Door Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Out Door Light Off"));
 //BA.debugLineNum = 516;BA.debugLine="ImageView_Out_Door.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_out_door.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 }else {
 //BA.debugLineNum = 523;BA.debugLine="Btn_Door.Text = \"Open\"";
mostCurrent._btn_door.setText(BA.ObjectToCharSequence("Open"));
 //BA.debugLineNum = 524;BA.debugLine="Label_status_door.Text =\"Open Door\"";
mostCurrent._label_status_door.setText(BA.ObjectToCharSequence("Open Door"));
 //BA.debugLineNum = 526;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 527;BA.debugLine="send_data(\"RS_DoorOpen_RE\")";
_send_data("RS_DoorOpen_RE");
 //BA.debugLineNum = 528;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 530;BA.debugLine="Label_status_light.Text = \"Out Door Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Out Door Light On"));
 //BA.debugLineNum = 531;BA.debugLine="ImageView_Out_Door.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_out_door.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 533;BA.debugLine="b1.Initialize(2000,2000)";
mostCurrent._b1.Initialize((int) (2000),(int) (2000));
 //BA.debugLineNum = 534;BA.debugLine="b1.Beep";
mostCurrent._b1.Beep();
 };
 //BA.debugLineNum = 539;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_click() throws Exception{
int _result = 0;
 //BA.debugLineNum = 478;BA.debugLine="Sub Btn_exit_Click";
 //BA.debugLineNum = 480;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 482;BA.debugLine="result =	Msgbox2(\"Do you want to exit ?\" ,\"Exit";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Do you want to exit ?"),BA.ObjectToCharSequence("Exit the program"),"Yes","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ff.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 484;BA.debugLine="If result = DialogResponse.POSITIVE   Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 486;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else if(_result==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 };
 //BA.debugLineNum = 493;BA.debugLine="End Sub";
return "";
}
public static String  _btn_garden_click() throws Exception{
String _m = "";
 //BA.debugLineNum = 825;BA.debugLine="Sub Btn_Garden_Click";
 //BA.debugLineNum = 827;BA.debugLine="COUNTER7 = COUNTER7 + 1";
_counter7 = (int) (_counter7+1);
 //BA.debugLineNum = 829;BA.debugLine="Dim m As String";
_m = "";
 //BA.debugLineNum = 830;BA.debugLine="m = COUNTER7 Mod 2";
_m = BA.NumberToString(_counter7%2);
 //BA.debugLineNum = 834;BA.debugLine="If m = 0 Then";
if ((_m).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 836;BA.debugLine="Label5_Gaz.Visible =False";
mostCurrent._label5_gaz.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 837;BA.debugLine="Label_Soild.Visible =True";
mostCurrent._label_soild.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 854;BA.debugLine="Label_Soild.Visible = False";
mostCurrent._label_soild.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 855;BA.debugLine="Label5_Gaz.Visible =True";
mostCurrent._label5_gaz.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 873;BA.debugLine="End Sub";
return "";
}
public static String  _btn_night_click() throws Exception{
String _e = "";
byte[] _data = null;
 //BA.debugLineNum = 730;BA.debugLine="Sub Btn_Night_Click";
 //BA.debugLineNum = 733;BA.debugLine="COUNTER4 = COUNTER4 + 1";
_counter4 = (int) (_counter4+1);
 //BA.debugLineNum = 734;BA.debugLine="Dim E As String";
_e = "";
 //BA.debugLineNum = 735;BA.debugLine="E = COUNTER4 Mod 2";
_e = BA.NumberToString(_counter4%2);
 //BA.debugLineNum = 737;BA.debugLine="If E = 1 Then";
if ((_e).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 739;BA.debugLine="Label_status_light.Text = \"Light Sleep On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Light Sleep On"));
 //BA.debugLineNum = 741;BA.debugLine="ImageView_Night.SetBackgroundImage(LoadBitmap(";
mostCurrent._imageview_night.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 743;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 744;BA.debugLine="send_data(\"RS_Nighton_RE\")";
_send_data("RS_Nighton_RE");
 //BA.debugLineNum = 745;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 }else {
 //BA.debugLineNum = 749;BA.debugLine="Label_status_light.Text =  \"Light Sleep Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Light Sleep Off"));
 //BA.debugLineNum = 751;BA.debugLine="ImageView_Night.SetBackgroundImage(LoadBitma";
mostCurrent._imageview_night.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 753;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 754;BA.debugLine="send_data(\"RS_Nightoff_RE\")";
_send_data("RS_Nightoff_RE");
 //BA.debugLineNum = 755;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 };
 //BA.debugLineNum = 760;BA.debugLine="End Sub";
return "";
}
public static String  _btn_off_ac_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 1089;BA.debugLine="Sub Btn_off_AC_Click";
 //BA.debugLineNum = 1091;BA.debugLine="Label_status_AC.Text = \"Off Cooler\"";
mostCurrent._label_status_ac.setText(BA.ObjectToCharSequence("Off Cooler"));
 //BA.debugLineNum = 1093;BA.debugLine="Btn_off_AC.TextColor = 0xFFFEFEFE";
mostCurrent._btn_off_ac.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 1095;BA.debugLine="Btn_on_AC.Text = \"A/C ON\"";
mostCurrent._btn_on_ac.setText(BA.ObjectToCharSequence("A/C ON"));
 //BA.debugLineNum = 1096;BA.debugLine="Btn_on_AC.TextColor = 0xFFFEFEFE";
mostCurrent._btn_on_ac.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 1100;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1101;BA.debugLine="send_data(\"RS_AcOff_RE\")";
_send_data("RS_AcOff_RE");
 //BA.debugLineNum = 1102;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1105;BA.debugLine="End Sub";
return "";
}
public static String  _btn_off_decor_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 1041;BA.debugLine="Sub Btn_off_Decor_Click";
 //BA.debugLineNum = 1043;BA.debugLine="Label_status_light.Text = \"Decor Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Decor Light Off"));
 //BA.debugLineNum = 1045;BA.debugLine="ImageView_off_decor.SetBackgroundImage(LoadB";
mostCurrent._imageview_off_decor.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 1048;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1049;BA.debugLine="send_data(\"RS_Decoroff_RE\")";
_send_data("RS_Decoroff_RE");
 //BA.debugLineNum = 1050;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1053;BA.debugLine="End Sub";
return "";
}
public static String  _btn_off_main_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 617;BA.debugLine="Sub Btn_off_main_Click";
 //BA.debugLineNum = 619;BA.debugLine="Label_status_light.Text =\"Main Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Main Light Off"));
 //BA.debugLineNum = 620;BA.debugLine="ImageView_off_main.SetBackgroundImage(LoadBitmap(";
mostCurrent._imageview_off_main.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 623;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 624;BA.debugLine="send_data(\"RS_Mainoff_RE\")";
_send_data("RS_Mainoff_RE");
 //BA.debugLineNum = 625;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 628;BA.debugLine="End Sub";
return "";
}
public static String  _btn_on_ac_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 1071;BA.debugLine="Sub Btn_on_AC_Click";
 //BA.debugLineNum = 1074;BA.debugLine="Label_status_AC.Text = \"On Cooler\"";
mostCurrent._label_status_ac.setText(BA.ObjectToCharSequence("On Cooler"));
 //BA.debugLineNum = 1076;BA.debugLine="Btn_on_AC.Text = \"Set\"";
mostCurrent._btn_on_ac.setText(BA.ObjectToCharSequence("Set"));
 //BA.debugLineNum = 1077;BA.debugLine="Btn_on_AC.TextColor = 0xFF000000";
mostCurrent._btn_on_ac.setTextColor((int) (0xff000000));
 //BA.debugLineNum = 1082;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1083;BA.debugLine="send_data(\"RS_AcOn_RE\"&speed1)";
_send_data("RS_AcOn_RE"+mostCurrent._speed1);
 //BA.debugLineNum = 1084;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1087;BA.debugLine="End Sub";
return "";
}
public static String  _btn_on_decor_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 1025;BA.debugLine="Sub Btn_on_Decor_Click";
 //BA.debugLineNum = 1029;BA.debugLine="Label_status_light.Text = \"Decor Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Decor Light On"));
 //BA.debugLineNum = 1031;BA.debugLine="ImageView_off_decor.SetBackgroundImage(LoadBit";
mostCurrent._imageview_off_decor.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 1034;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1035;BA.debugLine="send_data(\"RS_Decoron_RE\")";
_send_data("RS_Decoron_RE");
 //BA.debugLineNum = 1036;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1039;BA.debugLine="End Sub";
return "";
}
public static String  _btn_on_main_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 601;BA.debugLine="Sub Btn_on_main_Click";
 //BA.debugLineNum = 604;BA.debugLine="Label_status_light.Text = \"Main Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Main Light On"));
 //BA.debugLineNum = 605;BA.debugLine="ImageView_off_main.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_off_main.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 610;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 611;BA.debugLine="send_data(\"RS_Mainon_RE\")";
_send_data("RS_Mainon_RE");
 //BA.debugLineNum = 612;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 615;BA.debugLine="End Sub";
return "";
}
public static String  _btn_out_light_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 568;BA.debugLine="Sub Btn_Out_Light_Click";
 //BA.debugLineNum = 571;BA.debugLine="COUNTER3 = COUNTER3 + 1";
_counter3 = (int) (_counter3+1);
 //BA.debugLineNum = 573;BA.debugLine="Dim C As String";
mostCurrent._c = "";
 //BA.debugLineNum = 574;BA.debugLine="C = COUNTER3 Mod 2";
mostCurrent._c = BA.NumberToString(_counter3%2);
 //BA.debugLineNum = 577;BA.debugLine="If C = 1 Then";
if ((mostCurrent._c).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 579;BA.debugLine="Label_status_light.Text = \"Out Door Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Out Door Light On"));
 //BA.debugLineNum = 581;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 582;BA.debugLine="send_data(\"RS_LightDoorOn_RE\")";
_send_data("RS_LightDoorOn_RE");
 //BA.debugLineNum = 583;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 585;BA.debugLine="ImageView_Out_Door.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_out_door.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 }else {
 //BA.debugLineNum = 589;BA.debugLine="Label_status_light.Text = \"Out Door Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("Out Door Light Off"));
 //BA.debugLineNum = 590;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 591;BA.debugLine="send_data(\"RS_LightDoorOff_RE\")";
_send_data("RS_LightDoorOff_RE");
 //BA.debugLineNum = 592;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 594;BA.debugLine="ImageView_Out_Door.SetBackgroundImage(LoadBi";
mostCurrent._imageview_out_door.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 };
 //BA.debugLineNum = 599;BA.debugLine="End Sub";
return "";
}
public static String  _btn_room_off_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 683;BA.debugLine="Sub Btn_Room_off_Click";
 //BA.debugLineNum = 685;BA.debugLine="Label_status_light.Text = \"All Light Off\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("All Light Off"));
 //BA.debugLineNum = 687;BA.debugLine="ImageView_Room.SetBackgroundImage(LoadBitmap(Fi";
mostCurrent._imageview_room.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 689;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 690;BA.debugLine="send_data(\"RS_RoomOff_RE\")";
_send_data("RS_RoomOff_RE");
 //BA.debugLineNum = 691;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 693;BA.debugLine="Btn_Night.Enabled =True";
mostCurrent._btn_night.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 694;BA.debugLine="Btn_Night.Text = \"Night\"";
mostCurrent._btn_night.setText(BA.ObjectToCharSequence("Night"));
 //BA.debugLineNum = 695;BA.debugLine="Btn_Night.TextColor = 0xFFFEFEFE";
mostCurrent._btn_night.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 696;BA.debugLine="ImageView_Night.SetBackgroundImage(LoadBitmap(";
mostCurrent._imageview_night.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 698;BA.debugLine="Btn_Bed_1.Enabled =True";
mostCurrent._btn_bed_1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 699;BA.debugLine="Btn_Bed_1.Text = \"Bed Room 1\"";
mostCurrent._btn_bed_1.setText(BA.ObjectToCharSequence("Bed Room 1"));
 //BA.debugLineNum = 700;BA.debugLine="Btn_Bed_1.TextColor = 0xFFFEFEFE";
mostCurrent._btn_bed_1.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 701;BA.debugLine="ImageView_Bed1.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 704;BA.debugLine="Btn_Bed_2.Enabled =True";
mostCurrent._btn_bed_2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 705;BA.debugLine="Btn_Bed_2.Text = \"Bed Room 2\"";
mostCurrent._btn_bed_2.setText(BA.ObjectToCharSequence("Bed Room 2"));
 //BA.debugLineNum = 706;BA.debugLine="Btn_Bed_2.TextColor = 0xFFFEFEFE";
mostCurrent._btn_bed_2.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 707;BA.debugLine="ImageView_Bed2.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 710;BA.debugLine="Btn_off_main.Enabled = True";
mostCurrent._btn_off_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 711;BA.debugLine="Btn_off_main.TextColor = 0xFFFCFFFE";
mostCurrent._btn_off_main.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 712;BA.debugLine="Btn_on_main.Enabled =True";
mostCurrent._btn_on_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 713;BA.debugLine="Btn_on_main.TextSize = 18";
mostCurrent._btn_on_main.setTextSize((float) (18));
 //BA.debugLineNum = 714;BA.debugLine="Btn_on_main.Text = \"ON\"";
mostCurrent._btn_on_main.setText(BA.ObjectToCharSequence("ON"));
 //BA.debugLineNum = 715;BA.debugLine="Btn_on_main.TextColor = 0xFFFEFEFE";
mostCurrent._btn_on_main.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 716;BA.debugLine="ImageView_off_main.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_off_main.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 719;BA.debugLine="Btn_off_Decor.Enabled = True";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 720;BA.debugLine="Btn_off_Decor.TextColor = 0xFFFCFFFE";
mostCurrent._btn_off_decor.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 721;BA.debugLine="Btn_on_Decor.Enabled =True";
mostCurrent._btn_on_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 722;BA.debugLine="Btn_on_Decor.TextSize = 18";
mostCurrent._btn_on_decor.setTextSize((float) (18));
 //BA.debugLineNum = 723;BA.debugLine="Btn_on_Decor.Text = \"ON\"";
mostCurrent._btn_on_decor.setText(BA.ObjectToCharSequence("ON"));
 //BA.debugLineNum = 724;BA.debugLine="Btn_on_Decor.TextColor = 0xFFFEFEFE";
mostCurrent._btn_on_decor.setTextColor((int) (0xfffefefe));
 //BA.debugLineNum = 726;BA.debugLine="ImageView_off_decor.SetBackgroundImage(LoadBit";
mostCurrent._imageview_off_decor.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 728;BA.debugLine="End Sub";
return "";
}
public static String  _btn_room_on_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 630;BA.debugLine="Sub Btn_Room_on_Click";
 //BA.debugLineNum = 633;BA.debugLine="Label_status_light.Text = \"All Light On\"";
mostCurrent._label_status_light.setText(BA.ObjectToCharSequence("All Light On"));
 //BA.debugLineNum = 635;BA.debugLine="ImageView_Room.SetBackgroundImage(LoadBitmap(Fi";
mostCurrent._imageview_room.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 637;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 638;BA.debugLine="send_data(\"RS_RoomOn_RE\")";
_send_data("RS_RoomOn_RE");
 //BA.debugLineNum = 639;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 642;BA.debugLine="Btn_Night.Enabled = False";
mostCurrent._btn_night.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 643;BA.debugLine="Btn_Night.Text = \"INactivate\"";
mostCurrent._btn_night.setText(BA.ObjectToCharSequence("INactivate"));
 //BA.debugLineNum = 644;BA.debugLine="Btn_Night.TextColor = 0xFF12F411";
mostCurrent._btn_night.setTextColor((int) (0xff12f411));
 //BA.debugLineNum = 645;BA.debugLine="ImageView_Night.SetBackgroundImage(LoadBitmap(";
mostCurrent._imageview_night.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 648;BA.debugLine="Btn_Bed_1.Enabled = False";
mostCurrent._btn_bed_1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 649;BA.debugLine="Btn_Bed_1.Text = \"INactivate\"";
mostCurrent._btn_bed_1.setText(BA.ObjectToCharSequence("INactivate"));
 //BA.debugLineNum = 650;BA.debugLine="Btn_Bed_1.TextColor = 0xFF12F411";
mostCurrent._btn_bed_1.setTextColor((int) (0xff12f411));
 //BA.debugLineNum = 651;BA.debugLine="ImageView_Bed1.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 655;BA.debugLine="Btn_Bed_2.Enabled = False";
mostCurrent._btn_bed_2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 656;BA.debugLine="Btn_Bed_2.Text = \"INactivate\"";
mostCurrent._btn_bed_2.setText(BA.ObjectToCharSequence("INactivate"));
 //BA.debugLineNum = 657;BA.debugLine="Btn_Bed_2.TextColor = 0xFF12F411";
mostCurrent._btn_bed_2.setTextColor((int) (0xff12f411));
 //BA.debugLineNum = 658;BA.debugLine="ImageView_Bed2.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_bed2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 661;BA.debugLine="Btn_off_main.Enabled = False";
mostCurrent._btn_off_main.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 662;BA.debugLine="Btn_off_main.TextColor = 0xFF665255";
mostCurrent._btn_off_main.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 663;BA.debugLine="Btn_on_main.Enabled = False";
mostCurrent._btn_on_main.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 664;BA.debugLine="Btn_on_main.Text =\"INactivate\"";
mostCurrent._btn_on_main.setText(BA.ObjectToCharSequence("INactivate"));
 //BA.debugLineNum = 665;BA.debugLine="Btn_on_main.TextSize = 14";
mostCurrent._btn_on_main.setTextSize((float) (14));
 //BA.debugLineNum = 666;BA.debugLine="Btn_on_main.TextColor = 0xFF040404";
mostCurrent._btn_on_main.setTextColor((int) (0xff040404));
 //BA.debugLineNum = 668;BA.debugLine="ImageView_off_main.SetBackgroundImage(LoadBitm";
mostCurrent._imageview_off_main.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 671;BA.debugLine="Btn_off_Decor.Enabled = False";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 672;BA.debugLine="Btn_off_Decor.TextColor = 0xFF665255";
mostCurrent._btn_off_decor.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 673;BA.debugLine="Btn_on_Decor.Enabled = False";
mostCurrent._btn_on_decor.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 674;BA.debugLine="Btn_on_Decor.Text = \"INactivate\"";
mostCurrent._btn_on_decor.setText(BA.ObjectToCharSequence("INactivate"));
 //BA.debugLineNum = 675;BA.debugLine="Btn_on_Decor.TextSize = 14";
mostCurrent._btn_on_decor.setTextSize((float) (14));
 //BA.debugLineNum = 676;BA.debugLine="Btn_on_Decor.TextColor = 0xFF040404";
mostCurrent._btn_on_decor.setTextColor((int) (0xff040404));
 //BA.debugLineNum = 678;BA.debugLine="ImageView_off_decor.SetBackgroundImage(LoadBit";
mostCurrent._imageview_off_decor.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 681;BA.debugLine="End Sub";
return "";
}
public static String  _btn_security_click() throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 876;BA.debugLine="Sub Btn_Security_Click";
 //BA.debugLineNum = 881;BA.debugLine="COUNTER7 = COUNTER7 + 1";
_counter7 = (int) (_counter7+1);
 //BA.debugLineNum = 883;BA.debugLine="Dim m2 As String";
mostCurrent._m2 = "";
 //BA.debugLineNum = 884;BA.debugLine="m2 = COUNTER7 Mod 2";
mostCurrent._m2 = BA.NumberToString(_counter7%2);
 //BA.debugLineNum = 888;BA.debugLine="If m2 = 0 Then";
if ((mostCurrent._m2).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 891;BA.debugLine="Btn_Security.Text = \"Active\"";
mostCurrent._btn_security.setText(BA.ObjectToCharSequence("Active"));
 //BA.debugLineNum = 892;BA.debugLine="Btn_Security.TextColor = 0xFF000000";
mostCurrent._btn_security.setTextColor((int) (0xff000000));
 //BA.debugLineNum = 893;BA.debugLine="ImageView_lok.Visible = True";
mostCurrent._imageview_lok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 894;BA.debugLine="ImageView_lok.SetBackgroundImage(LoadBitma";
mostCurrent._imageview_lok.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"00.png").getObject()));
 //BA.debugLineNum = 896;BA.debugLine="Label_secu_text.Visible = True";
mostCurrent._label_secu_text.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 897;BA.debugLine="Label_status_AC.Visible = False";
mostCurrent._label_status_ac.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 898;BA.debugLine="Label_status_door.Visible = False";
mostCurrent._label_status_door.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 899;BA.debugLine="Label_status_light.Visible = False";
mostCurrent._label_status_light.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 900;BA.debugLine="Label_status_AC.Visible = False";
mostCurrent._label_status_ac.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 902;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 903;BA.debugLine="send_data(\"RS_SecOn_RE\")";
_send_data("RS_SecOn_RE");
 //BA.debugLineNum = 904;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 907;BA.debugLine="Btn_Door.Enabled = False";
mostCurrent._btn_door.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 909;BA.debugLine="Btn_Bed_1.Enabled = False";
mostCurrent._btn_bed_1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 910;BA.debugLine="Btn_Bed_1.TextColor = 0xFF665255";
mostCurrent._btn_bed_1.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 912;BA.debugLine="Btn_Bed_2.Enabled = False";
mostCurrent._btn_bed_2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 913;BA.debugLine="Btn_Bed_2.TextColor = 0xFF665255";
mostCurrent._btn_bed_2.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 915;BA.debugLine="Btn_Night.Enabled = False";
mostCurrent._btn_night.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 916;BA.debugLine="Btn_Night.TextColor = 0xFF665255";
mostCurrent._btn_night.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 918;BA.debugLine="Btn_off_AC.Enabled = False";
mostCurrent._btn_off_ac.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 919;BA.debugLine="Btn_off_AC.TextColor = 0xFF665255";
mostCurrent._btn_off_ac.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 921;BA.debugLine="Btn_on_AC.Enabled = False";
mostCurrent._btn_on_ac.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 922;BA.debugLine="Btn_on_AC.TextColor = 0xFF665255";
mostCurrent._btn_on_ac.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 924;BA.debugLine="Btn_off_Decor.Enabled = False";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 925;BA.debugLine="Btn_off_Decor.TextColor = 0xFF665255";
mostCurrent._btn_off_decor.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 927;BA.debugLine="Btn_on_Decor.Enabled = False";
mostCurrent._btn_on_decor.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 928;BA.debugLine="Btn_on_Decor.TextColor = 0xFF665255";
mostCurrent._btn_on_decor.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 930;BA.debugLine="Btn_off_main.Enabled = False";
mostCurrent._btn_off_main.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 931;BA.debugLine="Btn_off_main.TextColor = 0xFF665255";
mostCurrent._btn_off_main.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 933;BA.debugLine="Btn_on_main.Enabled = False";
mostCurrent._btn_on_main.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 934;BA.debugLine="Btn_on_main.TextColor = 0xFF665255";
mostCurrent._btn_on_main.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 936;BA.debugLine="Btn_Out_Light.Enabled = False";
mostCurrent._btn_out_light.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 937;BA.debugLine="Btn_Out_Light.TextColor = 0xFF665255";
mostCurrent._btn_out_light.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 939;BA.debugLine="Btn_Room_off.Enabled = False";
mostCurrent._btn_room_off.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 940;BA.debugLine="Btn_Room_off.TextColor = 0xFF665255";
mostCurrent._btn_room_off.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 942;BA.debugLine="Btn_Room_on.Enabled = False";
mostCurrent._btn_room_on.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 943;BA.debugLine="Btn_Room_on.TextColor = 0xFF665255";
mostCurrent._btn_room_on.setTextColor((int) (0xff665255));
 //BA.debugLineNum = 945;BA.debugLine="SeekBar_AC.Enabled = False";
mostCurrent._seekbar_ac.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 953;BA.debugLine="ImageView_lok.Visible = False";
mostCurrent._imageview_lok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 956;BA.debugLine="Label_secu_text.Visible = False";
mostCurrent._label_secu_text.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 957;BA.debugLine="Label_status_AC.Visible = True";
mostCurrent._label_status_ac.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 958;BA.debugLine="Label_status_door.Visible =True";
mostCurrent._label_status_door.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 959;BA.debugLine="Label_status_light.Visible = True";
mostCurrent._label_status_light.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 960;BA.debugLine="Label_status_AC.Visible =True";
mostCurrent._label_status_ac.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 962;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 963;BA.debugLine="send_data(\"RS_SecOff_RE\")";
_send_data("RS_SecOff_RE");
 //BA.debugLineNum = 964;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 967;BA.debugLine="Btn_Door.Enabled = True";
mostCurrent._btn_door.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 969;BA.debugLine="Btn_Bed_1.Enabled = True";
mostCurrent._btn_bed_1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 970;BA.debugLine="Btn_Bed_1.TextColor = 0xFFFCFFFE";
mostCurrent._btn_bed_1.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 972;BA.debugLine="Btn_Bed_2.Enabled =True";
mostCurrent._btn_bed_2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 973;BA.debugLine="Btn_Bed_2.TextColor = 0xFFFCFFFE";
mostCurrent._btn_bed_2.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 975;BA.debugLine="Btn_Night.Enabled = True";
mostCurrent._btn_night.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 976;BA.debugLine="Btn_Night.TextColor = 0xFFFCFFFE";
mostCurrent._btn_night.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 978;BA.debugLine="Btn_off_AC.Enabled = True";
mostCurrent._btn_off_ac.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 979;BA.debugLine="Btn_off_AC.TextColor = 0xFFFCFFFE";
mostCurrent._btn_off_ac.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 981;BA.debugLine="Btn_on_AC.Enabled = True";
mostCurrent._btn_on_ac.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 982;BA.debugLine="Btn_on_AC.TextColor = 0xFFFCFFFE";
mostCurrent._btn_on_ac.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 984;BA.debugLine="Btn_off_Decor.Enabled = True";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 985;BA.debugLine="Btn_on_AC.TextColor = 0xFFFCFFFE";
mostCurrent._btn_on_ac.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 987;BA.debugLine="Btn_on_Decor.Enabled = True";
mostCurrent._btn_on_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 988;BA.debugLine="Btn_on_Decor.TextColor = 0xFFFCFFFE";
mostCurrent._btn_on_decor.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 990;BA.debugLine="Btn_off_Decor.Enabled = True";
mostCurrent._btn_off_decor.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 991;BA.debugLine="Btn_off_Decor.TextColor = 0xFFFCFFFE";
mostCurrent._btn_off_decor.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 993;BA.debugLine="Btn_off_main.Enabled = True";
mostCurrent._btn_off_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 994;BA.debugLine="Btn_off_main.TextColor = 0xFFFCFFFE";
mostCurrent._btn_off_main.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 996;BA.debugLine="Btn_on_main.Enabled = True";
mostCurrent._btn_on_main.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 997;BA.debugLine="Btn_on_main.TextColor =0xFFFCFFFE";
mostCurrent._btn_on_main.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 999;BA.debugLine="Btn_Out_Light.Enabled = True";
mostCurrent._btn_out_light.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1000;BA.debugLine="Btn_Out_Light.TextColor = 0xFFFCFFFE";
mostCurrent._btn_out_light.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 1002;BA.debugLine="Btn_Room_off.Enabled = True";
mostCurrent._btn_room_off.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1003;BA.debugLine="Btn_Room_off.TextColor = 0xFFFCFFFE";
mostCurrent._btn_room_off.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 1005;BA.debugLine="Btn_Room_on.Enabled = True";
mostCurrent._btn_room_on.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1006;BA.debugLine="Btn_Room_on.TextColor = 0xFFFCFFFE";
mostCurrent._btn_room_on.setTextColor((int) (0xfffcfffe));
 //BA.debugLineNum = 1008;BA.debugLine="SeekBar_AC.Enabled =True";
mostCurrent._seekbar_ac.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1010;BA.debugLine="Btn_Security.Text = \"Security\"";
mostCurrent._btn_security.setText(BA.ObjectToCharSequence("Security"));
 //BA.debugLineNum = 1011;BA.debugLine="Btn_Security.TextColor = 0xFFFCFFFE";
mostCurrent._btn_security.setTextColor((int) (0xfffcfffe));
 };
 //BA.debugLineNum = 1014;BA.debugLine="End Sub";
return "";
}
public static String  _date_error() throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Sub DATE_Error";
 //BA.debugLineNum = 468;BA.debugLine="ToastMessageShow(\"Send the information problem oc";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Send the information problem occurred"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 470;BA.debugLine="End Sub";
return "";
}
public static String  _date_newdata(byte[] _buffer) throws Exception{
String _input = "";
byte[] _data = null;
int _result = 0;
 //BA.debugLineNum = 261;BA.debugLine="Sub DATE_NewData (Buffer() As Byte)";
 //BA.debugLineNum = 263;BA.debugLine="Dim input As String";
_input = "";
 //BA.debugLineNum = 264;BA.debugLine="input = BytesToString(Buffer, 0, Buffer.Length";
_input = anywheresoftware.b4a.keywords.Common.BytesToString(_buffer,(int) (0),_buffer.length,"UTF-8");
 //BA.debugLineNum = 265;BA.debugLine="temp =  ALI.WORD_Filter (input, \"T\",\"t\")";
mostCurrent._temp = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"T","t");
 //BA.debugLineNum = 266;BA.debugLine="hum  =  ALI.WORD_Filter (input, \"H\",\"h\")";
mostCurrent._hum = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"H","h");
 //BA.debugLineNum = 267;BA.debugLine="Gaz =   ALI.WORD_Filter (input, \"G\",\"g\")";
mostCurrent._gaz = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"G","g");
 //BA.debugLineNum = 268;BA.debugLine="Secu =  ALI.WORD_Filter (input, \"Q\",\"q\")";
mostCurrent._secu = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"Q","q");
 //BA.debugLineNum = 269;BA.debugLine="Cell =  ALI.WORD_Filter (input, \"V\",\"v\")";
mostCurrent._cell = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"V","v");
 //BA.debugLineNum = 270;BA.debugLine="soild =  ALI.WORD_Filter (input, \"S\",\"s\")";
mostCurrent._soild = mostCurrent._ali._word_filter(mostCurrent.activityBA,_input,"S","s");
 //BA.debugLineNum = 276;BA.debugLine="If m2 = 0 Then";
if ((mostCurrent._m2).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 279;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 280;BA.debugLine="send_data(\"RS_SecOn_RE\")";
_send_data("RS_SecOn_RE");
 //BA.debugLineNum = 281;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 283;BA.debugLine="ImageView_lok.Visible = True";
mostCurrent._imageview_lok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 286;BA.debugLine="If Secu = 1 Then";
if ((mostCurrent._secu).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 288;BA.debugLine="ImageView_lok.Visible = False";
mostCurrent._imageview_lok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 289;BA.debugLine="ImageView_Secur.Visible = True";
mostCurrent._imageview_secur.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 291;BA.debugLine="vibra.Vibrate(2000)";
mostCurrent._vibra.Vibrate(processBA,(long) (2000));
 //BA.debugLineNum = 293;BA.debugLine="b1.Initialize(2000,2000)";
mostCurrent._b1.Initialize((int) (2000),(int) (2000));
 //BA.debugLineNum = 294;BA.debugLine="b1.Beep";
mostCurrent._b1.Beep();
 //BA.debugLineNum = 296;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 298;BA.debugLine="result = Msgbox2(\"Please call the police \", \"Theft";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Please call the police "),BA.ObjectToCharSequence("Theft alert"),"Yes","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aaa.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 300;BA.debugLine="If result = DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 302;BA.debugLine="ImageView_Secur.Visible = False";
mostCurrent._imageview_secur.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 305;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 306;BA.debugLine="send_data(\"RS_SecOff_RE\")";
_send_data("RS_SecOff_RE");
 //BA.debugLineNum = 307;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 };
 };
 };
 //BA.debugLineNum = 316;BA.debugLine="If temp <> \"\" Then";
if ((mostCurrent._temp).equals("") == false) { 
 //BA.debugLineNum = 317;BA.debugLine="Lbl_temp.Text = temp & \" °C\"";
mostCurrent._lbl_temp.setText(BA.ObjectToCharSequence(mostCurrent._temp+" °C"));
 };
 //BA.debugLineNum = 321;BA.debugLine="If hum <> \"\" Then";
if ((mostCurrent._hum).equals("") == false) { 
 //BA.debugLineNum = 323;BA.debugLine="Lbl_Him.Text = hum  & \" %\"";
mostCurrent._lbl_him.setText(BA.ObjectToCharSequence(mostCurrent._hum+" %"));
 };
 //BA.debugLineNum = 335;BA.debugLine="Try";
try { //BA.debugLineNum = 337;BA.debugLine="If (Gaz>=0 And Gaz<400) Then";
if (((double)(Double.parseDouble(mostCurrent._gaz))>=0 && (double)(Double.parseDouble(mostCurrent._gaz))<400)) { 
 //BA.debugLineNum = 338;BA.debugLine="Label5_Gaz.Text =(\"Good air quality\" )";
mostCurrent._label5_gaz.setText(BA.ObjectToCharSequence(("Good air quality")));
 }else if(((double)(Double.parseDouble(mostCurrent._gaz))>=400 && (double)(Double.parseDouble(mostCurrent._gaz))<750)) { 
 //BA.debugLineNum = 341;BA.debugLine="Label5_Gaz.Text = (\"Unusual air quality\" )";
mostCurrent._label5_gaz.setText(BA.ObjectToCharSequence(("Unusual air quality")));
 }else if(((double)(Double.parseDouble(mostCurrent._gaz))>=750)) { 
 //BA.debugLineNum = 350;BA.debugLine="Label5_Gaz.Text = ( \" The risk of gas leaks\" )";
mostCurrent._label5_gaz.setText(BA.ObjectToCharSequence((" The risk of gas leaks")));
 //BA.debugLineNum = 352;BA.debugLine="b1.Initialize(5000,1823)";
mostCurrent._b1.Initialize((int) (5000),(int) (1823));
 //BA.debugLineNum = 353;BA.debugLine="b1.Beep";
mostCurrent._b1.Beep();
 //BA.debugLineNum = 354;BA.debugLine="Msgbox(\"Please cut the risk of death house gas";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please cut the risk of death house gas main valve"),BA.ObjectToCharSequence("Gas leak alarm"),mostCurrent.activityBA);
 };
 } 
       catch (Exception e48) {
			processBA.setLastException(e48); };
 //BA.debugLineNum = 363;BA.debugLine="Try";
try { //BA.debugLineNum = 365;BA.debugLine="If (Cell>=0 And Cell<50) Then";
if (((double)(Double.parseDouble(mostCurrent._cell))>=0 && (double)(Double.parseDouble(mostCurrent._cell))<50)) { 
 //BA.debugLineNum = 367;BA.debugLine="Label_Cell.Text =(\"Without Energy \" )";
mostCurrent._label_cell.setText(BA.ObjectToCharSequence(("Without Energy ")));
 //BA.debugLineNum = 368;BA.debugLine="ImageView_100.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_100.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 369;BA.debugLine="ImageView_200.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_200.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 370;BA.debugLine="ImageView_300.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_300.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 371;BA.debugLine="ImageView_400.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_400.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 }else if(((double)(Double.parseDouble(mostCurrent._cell))>=50 && (double)(Double.parseDouble(mostCurrent._cell))<150)) { 
 //BA.debugLineNum = 376;BA.debugLine="Label_Cell.Text = (\"Low Energy\" )";
mostCurrent._label_cell.setText(BA.ObjectToCharSequence(("Low Energy")));
 //BA.debugLineNum = 377;BA.debugLine="ImageView_100.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_100.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 378;BA.debugLine="ImageView_200.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_200.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 379;BA.debugLine="ImageView_300.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_300.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 380;BA.debugLine="ImageView_400.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_400.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 }else if(((double)(Double.parseDouble(mostCurrent._cell))>=150 && (double)(Double.parseDouble(mostCurrent._cell))<250)) { 
 //BA.debugLineNum = 386;BA.debugLine="Label_Cell.Text = (\"Average Energy\" )";
mostCurrent._label_cell.setText(BA.ObjectToCharSequence(("Average Energy")));
 //BA.debugLineNum = 387;BA.debugLine="ImageView_100.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_100.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 388;BA.debugLine="ImageView_200.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_200.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 389;BA.debugLine="ImageView_300.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_300.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 //BA.debugLineNum = 390;BA.debugLine="ImageView_400.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_400.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 }else if(((double)(Double.parseDouble(mostCurrent._cell))>=250 && (double)(Double.parseDouble(mostCurrent._cell))<350)) { 
 //BA.debugLineNum = 394;BA.debugLine="Label_Cell.Text = (\"Full Energy\" )";
mostCurrent._label_cell.setText(BA.ObjectToCharSequence(("Full Energy")));
 //BA.debugLineNum = 395;BA.debugLine="ImageView_100.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_100.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 396;BA.debugLine="ImageView_200.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_200.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 397;BA.debugLine="ImageView_300.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_300.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 398;BA.debugLine="ImageView_400.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_400.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png").getObject()));
 }else if(((double)(Double.parseDouble(mostCurrent._cell))>=350)) { 
 //BA.debugLineNum = 403;BA.debugLine="Label_Cell.Text = (\"Maximum Energy\" )";
mostCurrent._label_cell.setText(BA.ObjectToCharSequence(("Maximum Energy")));
 //BA.debugLineNum = 404;BA.debugLine="ImageView_100.SetBackgroundImage(LoadBitmap(Fi";
mostCurrent._imageview_100.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 405;BA.debugLine="ImageView_200.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_200.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 406;BA.debugLine="ImageView_300.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_300.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 407;BA.debugLine="ImageView_400.SetBackgroundImage(LoadBitmap(File";
mostCurrent._imageview_400.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png").getObject()));
 //BA.debugLineNum = 409;BA.debugLine="b1.Initialize(5000,1823)";
mostCurrent._b1.Initialize((int) (5000),(int) (1823));
 //BA.debugLineNum = 410;BA.debugLine="b1.Beep";
mostCurrent._b1.Beep();
 //BA.debugLineNum = 411;BA.debugLine="ToastMessageShow(\"Solar energy production is at";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Solar energy production is at its maximum position"),anywheresoftware.b4a.keywords.Common.True);
 };
 } 
       catch (Exception e85) {
			processBA.setLastException(e85); };
 //BA.debugLineNum = 423;BA.debugLine="Try";
try { //BA.debugLineNum = 427;BA.debugLine="If (soild>=850) Then";
if (((double)(Double.parseDouble(mostCurrent._soild))>=850)) { 
 //BA.debugLineNum = 429;BA.debugLine="Label5_Gaz.Visible = False";
mostCurrent._label5_gaz.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 430;BA.debugLine="Label_Soild.Visible = True";
mostCurrent._label_soild.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 431;BA.debugLine="Label_Soild.Text = (\"The soil is Dry\" )";
mostCurrent._label_soild.setText(BA.ObjectToCharSequence(("The soil is Dry")));
 //BA.debugLineNum = 433;BA.debugLine="ImageView_Garden_dry.Visible =True";
mostCurrent._imageview_garden_dry.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 434;BA.debugLine="ImageView_Garden_dry.SetBackgroundImage(LoadBi";
mostCurrent._imageview_garden_dry.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"garden dry.png").getObject()));
 }else if(((double)(Double.parseDouble(mostCurrent._soild))>=100 && (double)(Double.parseDouble(mostCurrent._soild))<360)) { 
 //BA.debugLineNum = 441;BA.debugLine="Label_Soild.Text = (\"Moist soil\")";
mostCurrent._label_soild.setText(BA.ObjectToCharSequence(("Moist soil")));
 //BA.debugLineNum = 442;BA.debugLine="Label_Soild.Visible = False";
mostCurrent._label_soild.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 443;BA.debugLine="Label5_Gaz.Visible =True";
mostCurrent._label5_gaz.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 445;BA.debugLine="ImageView_Garden_dry.Visible = False";
mostCurrent._imageview_garden_dry.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 446;BA.debugLine="ImageView_Garden.Visible = False";
mostCurrent._imageview_garden.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else if(((double)(Double.parseDouble(mostCurrent._soild))>=360 && (double)(Double.parseDouble(mostCurrent._soild))<850)) { 
 //BA.debugLineNum = 451;BA.debugLine="Label_Soild.Text = (\"Need watering\" )";
mostCurrent._label_soild.setText(BA.ObjectToCharSequence(("Need watering")));
 //BA.debugLineNum = 452;BA.debugLine="Label_Soild.Visible = True";
mostCurrent._label_soild.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 453;BA.debugLine="Label5_Gaz.Visible = False";
mostCurrent._label5_gaz.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 455;BA.debugLine="ImageView_Garden_dry.Visible = False";
mostCurrent._imageview_garden_dry.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 456;BA.debugLine="ImageView_Garden.Visible = True";
mostCurrent._imageview_garden.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 457;BA.debugLine="ImageView_Garden.SetBackgroundImage(LoadBitmap(F";
mostCurrent._imageview_garden.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gard.png").getObject()));
 };
 } 
       catch (Exception e108) {
			processBA.setLastException(e108); };
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
return "";
}
public static String  _date_terminated() throws Exception{
 //BA.debugLineNum = 472;BA.debugLine="Sub DATE_Terminated";
 //BA.debugLineNum = 474;BA.debugLine="ToastMessageShow(\"For transmitting information ha";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("For transmitting information has been lost"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 476;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private ImageView_Garden As ImageView";
mostCurrent._imageview_garden = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Lbl_temp As Label";
mostCurrent._lbl_temp = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Lbl_Him As Label";
mostCurrent._lbl_him = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Lbl_date As Label";
mostCurrent._lbl_date = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Lbl_Time As Label";
mostCurrent._lbl_time = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Btn_Door As Button";
mostCurrent._btn_door = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Btn_Kitchen As Button";
mostCurrent._btn_kitchen = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Btn_Out_Light As Button";
mostCurrent._btn_out_light = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private ImageView_Out_Door As ImageView";
mostCurrent._imageview_out_door = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Btn_Night As Button";
mostCurrent._btn_night = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private ImageView_Night As ImageView";
mostCurrent._imageview_night = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private Btn_Bed_1 As Button";
mostCurrent._btn_bed_1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Btn_Bed_2 As Button";
mostCurrent._btn_bed_2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private ImageView_Bed1 As ImageView";
mostCurrent._imageview_bed1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private ImageView_Bed2 As ImageView";
mostCurrent._imageview_bed2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Btn_Garden As Button";
mostCurrent._btn_garden = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private ImageView_off_main As ImageView";
mostCurrent._imageview_off_main = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private ImageView_off_decor As ImageView";
mostCurrent._imageview_off_decor = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private Btn_off_AC As Button";
mostCurrent._btn_off_ac = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private Btn_on_AC As Button";
mostCurrent._btn_on_ac = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private SeekBar_AC As SeekBar";
mostCurrent._seekbar_ac = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private ImageView1_AC As ImageView";
mostCurrent._imageview1_ac = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private Label7_AC As Label";
mostCurrent._label7_ac = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private ImageView_Room As ImageView";
mostCurrent._imageview_room = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private Btn_on_main As Button";
mostCurrent._btn_on_main = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private Btn_on_Decor As Button";
mostCurrent._btn_on_decor = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Btn_off_main As Button";
mostCurrent._btn_off_main = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Btn_off_Decor As Button";
mostCurrent._btn_off_decor = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Label5_Gaz As Label";
mostCurrent._label5_gaz = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Label_status_AC As Label";
mostCurrent._label_status_ac = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private Label_status_light As Label";
mostCurrent._label_status_light = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private Label_status_door As Label";
mostCurrent._label_status_door = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private Btn_Security As Button";
mostCurrent._btn_security = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private Label1_security As Label";
mostCurrent._label1_security = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private ImageView_Secur As ImageView";
mostCurrent._imageview_secur = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private ImageView_lok As ImageView";
mostCurrent._imageview_lok = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private Btn_Room_on As Button";
mostCurrent._btn_room_on = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private Btn_Room_off As Button";
mostCurrent._btn_room_off = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private Button_Ali As Button";
mostCurrent._button_ali = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private Label_secu_text As Label";
mostCurrent._label_secu_text = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private Btn_Connect As Button";
mostCurrent._btn_connect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private Btn_DisConnect As Button";
mostCurrent._btn_disconnect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private Btn_exit As Button";
mostCurrent._btn_exit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Private Label_Cell As Label";
mostCurrent._label_cell = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private ImageView_100 As ImageView";
mostCurrent._imageview_100 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private ImageView_200 As ImageView";
mostCurrent._imageview_200 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private ImageView_300 As ImageView";
mostCurrent._imageview_300 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private ImageView_400 As ImageView";
mostCurrent._imageview_400 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Private ImageView_Garden_dry As ImageView";
mostCurrent._imageview_garden_dry = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private Label_Ac712 As Label";
mostCurrent._label_ac712 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Private Label_Soild As Label";
mostCurrent._label_soild = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Dim s1 As Serial";
mostCurrent._s1 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 81;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 82;BA.debugLine="timer1.Initialize(\"Timer1\", 1000)";
mostCurrent._timer1.Initialize(processBA,"Timer1",(long) (1000));
 //BA.debugLineNum = 83;BA.debugLine="timer1.Enabled = True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 85;BA.debugLine="Dim date1 As PersianDate";
mostCurrent._date1 = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 87;BA.debugLine="Dim Sterem As AsyncStreams";
mostCurrent._sterem = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 89;BA.debugLine="Dim s1 As Serial";
mostCurrent._s1 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 91;BA.debugLine="Dim temp As String";
mostCurrent._temp = "";
 //BA.debugLineNum = 92;BA.debugLine="Dim hum As String";
mostCurrent._hum = "";
 //BA.debugLineNum = 93;BA.debugLine="Dim Gaz As String";
mostCurrent._gaz = "";
 //BA.debugLineNum = 94;BA.debugLine="Dim Secu As String";
mostCurrent._secu = "";
 //BA.debugLineNum = 95;BA.debugLine="Dim Cell As String";
mostCurrent._cell = "";
 //BA.debugLineNum = 96;BA.debugLine="Dim soild As String";
mostCurrent._soild = "";
 //BA.debugLineNum = 97;BA.debugLine="Dim Ac712 As String";
mostCurrent._ac712 = "";
 //BA.debugLineNum = 100;BA.debugLine="Dim  COUNTER1 As Int = 1";
_counter1 = (int) (1);
 //BA.debugLineNum = 102;BA.debugLine="Dim  COUNTER3 As Int = 1";
_counter3 = (int) (1);
 //BA.debugLineNum = 103;BA.debugLine="Dim  COUNTER4 As Int = 1";
_counter4 = (int) (1);
 //BA.debugLineNum = 104;BA.debugLine="Dim  COUNTER5 As Int = 1";
_counter5 = (int) (1);
 //BA.debugLineNum = 105;BA.debugLine="Dim  COUNTER6 As Int = 1";
_counter6 = (int) (1);
 //BA.debugLineNum = 106;BA.debugLine="Dim  COUNTER7 As Int = 1";
_counter7 = (int) (1);
 //BA.debugLineNum = 107;BA.debugLine="Dim  COUNTER8 As Int = 1";
_counter8 = (int) (1);
 //BA.debugLineNum = 110;BA.debugLine="Dim vibra As PhoneVibrate";
mostCurrent._vibra = new anywheresoftware.b4a.phone.Phone.PhoneVibrate();
 //BA.debugLineNum = 114;BA.debugLine="Dim speed1 As String = \"SS128SE\";";
mostCurrent._speed1 = "SS128SE";
 //BA.debugLineNum = 116;BA.debugLine="Dim b1 As Beeper";
mostCurrent._b1 = new anywheresoftware.b4a.audio.Beeper();
 //BA.debugLineNum = 118;BA.debugLine="Dim m2 As String";
mostCurrent._m2 = "";
 //BA.debugLineNum = 120;BA.debugLine="Dim C As String";
mostCurrent._c = "";
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public static String  _imageview_secur_click() throws Exception{
 //BA.debugLineNum = 1016;BA.debugLine="Sub ImageView_Secur_Click";
 //BA.debugLineNum = 1018;BA.debugLine="ImageView_Secur.Visible = False";
mostCurrent._imageview_secur.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1020;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
ali._process_globals();
starter._process_globals();
door._process_globals();
garden._process_globals();
gaed._process_globals();
gaedn._process_globals();
frm._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar_ac_valuechanged(int _value,boolean _userchanged) throws Exception{
String _speed = "";
byte[] _data = null;
 //BA.debugLineNum = 1056;BA.debugLine="Sub SeekBar_AC_ValueChanged (Value As Int, UserCha";
 //BA.debugLineNum = 1058;BA.debugLine="Dim speed As String = \"SS\"&Value&\"SE\"";
_speed = "SS"+BA.NumberToString(_value)+"SE";
 //BA.debugLineNum = 1059;BA.debugLine="speed1 = speed";
mostCurrent._speed1 = _speed;
 //BA.debugLineNum = 1060;BA.debugLine="Label7_AC.Text = Value";
mostCurrent._label7_ac.setText(BA.ObjectToCharSequence(_value));
 //BA.debugLineNum = 1062;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1063;BA.debugLine="data = speed.GetBytes(\"UTF-8\")";
_data = _speed.getBytes("UTF-8");
 //BA.debugLineNum = 1064;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1069;BA.debugLine="End Sub";
return "";
}
public static String  _send_data(String _go) throws Exception{
byte[] _data = null;
 //BA.debugLineNum = 1110;BA.debugLine="Sub send_data(go As String)";
 //BA.debugLineNum = 1112;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 1113;BA.debugLine="data = go.GetBytes(\"UTF-8\")";
_data = _go.getBytes("UTF-8");
 //BA.debugLineNum = 1114;BA.debugLine="Sterem.Write(data)";
mostCurrent._sterem.Write(_data);
 //BA.debugLineNum = 1115;BA.debugLine="Log(go)";
anywheresoftware.b4a.keywords.Common.Log(_go);
 //BA.debugLineNum = 1116;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
long _tim = 0L;
long _y = 0L;
long _m = 0L;
long _d = 0L;
 //BA.debugLineNum = 184;BA.debugLine="Sub Timer1_Tick";
 //BA.debugLineNum = 186;BA.debugLine="Dim tim As Long = DateTime.Now";
_tim = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 187;BA.debugLine="Lbl_Time.Text = DateTime.Time(tim)";
mostCurrent._lbl_time.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_tim)));
 //BA.debugLineNum = 189;BA.debugLine="Dim y As Long = DateTime.GetYear(tim)";
_y = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetYear(_tim));
 //BA.debugLineNum = 190;BA.debugLine="Dim m As Long = DateTime.GetMonth(tim)";
_m = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_tim));
 //BA.debugLineNum = 191;BA.debugLine="Dim d As Long = DateTime.GetDayOfMonth(tim)";
_d = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(_tim));
 //BA.debugLineNum = 193;BA.debugLine="Lbl_date.Text = date1.getDate(y,m,d,\"/\")";
mostCurrent._lbl_date.setText(BA.ObjectToCharSequence(mostCurrent._date1.getDate((int) (_y),(int) (_m),(int) (_d),"/")));
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
}
