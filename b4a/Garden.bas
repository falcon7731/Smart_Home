B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	
	 Dim Sterem As AsyncStreams
	  Dim  COUNTER1 As Int = 1
     Dim  COUNTER2 As Int = 1
     Dim  COUNTER3 As Int = 1
     Dim  COUNTER4 As Int = 1
     Dim  COUNTER5 As Int = 1
     Dim  COUNTER6 As Int = 1
     Dim  COUNTER7 As Int = 1

End Sub

Sub Globals
	
	
	Private ImageView_Garden As ImageView
	Private Button_Garden As Button
	
	Private ImageView_Garden As ImageView
	Private Lbl_temp As Label
	Private Lbl_Him As Label
	Private Lbl_date As Label
	Private Lbl_Time As Label
	Private Btn_Door As Button
	Private Btn_Kitchen As Button
	Private Btn_Out_Light As Button
	Private ImageView_Out_Door As ImageView
	Private Btn_Night As Button
	Private ImageView_Night As ImageView
	Private Btn_Bed_1 As Button
	Private Btn_Bed_2 As Button
	Private ImageView_Bed1 As ImageView
	Private ImageView_Bed2 As ImageView
	Private Btn_Garden As Button
	Private ImageView_off_main As ImageView
	Private ImageView_off_decor As ImageView
	Private Btn_off_AC As Button
	Private Btn_on_AC As Button
	Private SeekBar_AC As SeekBar
	Private Label7 As Label
	Private Label1 As Label
	Private ImageView1_AC As ImageView
	Private Label7_AC As Label
	Private ImageView_Room As ImageView
	Private Btn_on_main As Button
	Private Btn_on_Decor As Button
	Private Btn_off_main As Button
	Private Btn_off_Decor As Button
	Private Label5_Gaz As Label
	Private Label_status_AC As Label
	Private Label_status_light As Label
	Private Label_status_door As Label
	
	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("Garden")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub Button_Back_Click
	
	StartActivity(Main)
	
End Sub

Sub Button_Garden_Click
	
	COUNTER6 = COUNTER6 + 1
	 
     Dim G As String 
	  G = COUNTER6 Mod 2

	Try
		If G = 1 Then
		
		Label_status_light.Text = "  bedroom 1 Light On"
		
	   ImageView_Garden.SetBackgroundImage(LoadBitmap(File.DirAssets,"on.png"))
	   
	   Dim data() As Byte
       send_data("RS_Bed1on_RE")	
       Sterem.Write(data)
	  
	Else
		
		Label_status_light.Text ="  bedroom 1 Light Off"
	       
      ImageView_Garden.SetBackgroundImage(LoadBitmap(File.DirAssets,"off.png"))
	 
	   Dim data() As Byte
       send_data("RS_Bed1off_RE")
       Sterem.Write(data)
	  
	End If
	
	Catch
		Log(LastException)
	End Try
	
	
	
End Sub
Sub send_data(go As String)	
'vibrat1.Vibrate(100)	
Dim data() As Byte
data = go.GetBytes("UTF-8")
Sterem.Write(data)
Log(go)
End Sub









