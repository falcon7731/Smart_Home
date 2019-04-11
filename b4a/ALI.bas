B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=6.3
@EndOfDesignText@

Sub Process_Globals
	

End Sub
Public Sub WORD_Filter (imput_word As String, first_word  As String,last_word As String)
Try
Dim result As String 
Dim n1 ,n2 As Int	
If imput_word.IndexOf(first_word)>-1 Then
n1 = imput_word.IndexOf(first_word)+first_word.Length	
If imput_word.indexOf(last_word) > 0 Then
n2 = imput_word.indexOf(last_word)	
If imput_word.Length < 20 Then	
result  = imput_word.SubString2(n1,n2)
Return result

End If
End If
End If
Catch
ToastMessageShow("خطایی رخ داده است",True)
End Try
End Sub
