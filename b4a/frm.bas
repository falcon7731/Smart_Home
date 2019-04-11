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
	Dim temp As String

End Sub

Sub Globals
	Dim temp As String
Private Label_Soil As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frm")
	
	Label_Soil.Text = (temp )

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
