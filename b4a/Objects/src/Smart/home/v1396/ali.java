package Smart.home.v1396;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class ali {
private static ali mostCurrent = new ali();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public Smart.home.v1396.main _main = null;
public Smart.home.v1396.starter _starter = null;
public Smart.home.v1396.door _door = null;
public Smart.home.v1396.garden _garden = null;
public Smart.home.v1396.gaed _gaed = null;
public Smart.home.v1396.gaedn _gaedn = null;
public Smart.home.v1396.frm _frm = null;
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _word_filter(anywheresoftware.b4a.BA _ba,String _imput_word,String _first_word,String _last_word) throws Exception{
String _result = "";
int _n1 = 0;
int _n2 = 0;
 //BA.debugLineNum = 6;BA.debugLine="Public Sub WORD_Filter (imput_word As String, firs";
 //BA.debugLineNum = 7;BA.debugLine="Try";
try { //BA.debugLineNum = 8;BA.debugLine="Dim result As String";
_result = "";
 //BA.debugLineNum = 9;BA.debugLine="Dim n1 ,n2 As Int";
_n1 = 0;
_n2 = 0;
 //BA.debugLineNum = 10;BA.debugLine="If imput_word.IndexOf(first_word)>-1 Then";
if (_imput_word.indexOf(_first_word)>-1) { 
 //BA.debugLineNum = 11;BA.debugLine="n1 = imput_word.IndexOf(first_word)+first_word.Len";
_n1 = (int) (_imput_word.indexOf(_first_word)+_first_word.length());
 //BA.debugLineNum = 12;BA.debugLine="If imput_word.indexOf(last_word) > 0 Then";
if (_imput_word.indexOf(_last_word)>0) { 
 //BA.debugLineNum = 13;BA.debugLine="n2 = imput_word.indexOf(last_word)";
_n2 = _imput_word.indexOf(_last_word);
 //BA.debugLineNum = 14;BA.debugLine="If imput_word.Length < 20 Then";
if (_imput_word.length()<20) { 
 //BA.debugLineNum = 15;BA.debugLine="result  = imput_word.SubString2(n1,n2)";
_result = _imput_word.substring(_n1,_n2);
 //BA.debugLineNum = 16;BA.debugLine="Return result";
if (true) return _result;
 };
 };
 };
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 22;BA.debugLine="ToastMessageShow(\"خطایی رخ داده است\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("خطایی رخ داده است"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
}
