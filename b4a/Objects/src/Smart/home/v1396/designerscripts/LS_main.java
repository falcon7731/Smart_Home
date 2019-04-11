package Smart.home.v1396.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Main/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="Lbl_Time.Left =1%x"[Main/General script]
views.get("lbl_time").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="Lbl_Time.Width =28%x"[Main/General script]
views.get("lbl_time").vw.setWidth((int)((28d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="Lbl_Time.Height = 5%y"[Main/General script]
views.get("lbl_time").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 8;BA.debugLine="Label5_Gaz.Width = 39%x"[Main/General script]
views.get("label5_gaz").vw.setWidth((int)((39d / 100 * width)));
//BA.debugLineNum = 9;BA.debugLine="Label5_Gaz.Height = 5%y"[Main/General script]
views.get("label5_gaz").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 10;BA.debugLine="Label5_Gaz.Left = Lbl_Time.Right"[Main/General script]
views.get("label5_gaz").vw.setLeft((int)((views.get("lbl_time").vw.getLeft() + views.get("lbl_time").vw.getWidth())));
//BA.debugLineNum = 12;BA.debugLine="Label_Soild.Left = Lbl_Time.Right"[Main/General script]
views.get("label_soild").vw.setLeft((int)((views.get("lbl_time").vw.getLeft() + views.get("lbl_time").vw.getWidth())));
//BA.debugLineNum = 13;BA.debugLine="Label_Soild.top = Label5_Gaz.top"[Main/General script]
views.get("label_soild").vw.setTop((int)((views.get("label5_gaz").vw.getTop())));
//BA.debugLineNum = 14;BA.debugLine="Label_Soild.Width = 39%x"[Main/General script]
views.get("label_soild").vw.setWidth((int)((39d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="Label_Soild.Height = 5%y"[Main/General script]
views.get("label_soild").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 17;BA.debugLine="Lbl_date.Width = 28%x"[Main/General script]
views.get("lbl_date").vw.setWidth((int)((28d / 100 * width)));
//BA.debugLineNum = 18;BA.debugLine="Lbl_date.Left = Label5_Gaz.Right"[Main/General script]
views.get("lbl_date").vw.setLeft((int)((views.get("label5_gaz").vw.getLeft() + views.get("label5_gaz").vw.getWidth())));
//BA.debugLineNum = 20;BA.debugLine="Panel8.Top = Lbl_date.Bottom"[Main/General script]
views.get("panel8").vw.setTop((int)((views.get("lbl_date").vw.getTop() + views.get("lbl_date").vw.getHeight())));
//BA.debugLineNum = 21;BA.debugLine="Panel8.Width = 98%x"[Main/General script]
views.get("panel8").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 22;BA.debugLine="Panel8.Height = 1%y"[Main/General script]
views.get("panel8").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 23;BA.debugLine="Panel8.HorizontalCenter = 50%x"[Main/General script]
views.get("panel8").vw.setLeft((int)((50d / 100 * width) - (views.get("panel8").vw.getWidth() / 2)));
//BA.debugLineNum = 25;BA.debugLine="Label_status_AC.Top = Panel8.Bottom"[Main/General script]
views.get("label_status_ac").vw.setTop((int)((views.get("panel8").vw.getTop() + views.get("panel8").vw.getHeight())));
//BA.debugLineNum = 26;BA.debugLine="Label_status_AC.Width = 26%x"[Main/General script]
views.get("label_status_ac").vw.setWidth((int)((26d / 100 * width)));
//BA.debugLineNum = 27;BA.debugLine="Label_status_AC.Left= 1%x"[Main/General script]
views.get("label_status_ac").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 28;BA.debugLine="Label_status_AC.Height=5%y"[Main/General script]
views.get("label_status_ac").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 31;BA.debugLine="Label_status_light.Left = Label_status_AC.Right"[Main/General script]
views.get("label_status_light").vw.setLeft((int)((views.get("label_status_ac").vw.getLeft() + views.get("label_status_ac").vw.getWidth())));
//BA.debugLineNum = 32;BA.debugLine="Label_status_light.Top = Panel8.Bottom"[Main/General script]
views.get("label_status_light").vw.setTop((int)((views.get("panel8").vw.getTop() + views.get("panel8").vw.getHeight())));
//BA.debugLineNum = 33;BA.debugLine="Label_status_light.Width = 46%x"[Main/General script]
views.get("label_status_light").vw.setWidth((int)((46d / 100 * width)));
//BA.debugLineNum = 34;BA.debugLine="Label_status_light.Height=5%y"[Main/General script]
views.get("label_status_light").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 36;BA.debugLine="Label_status_door.Top = Panel8.Bottom"[Main/General script]
views.get("label_status_door").vw.setTop((int)((views.get("panel8").vw.getTop() + views.get("panel8").vw.getHeight())));
//BA.debugLineNum = 37;BA.debugLine="Label_status_door.Left = Label_status_light.Right"[Main/General script]
views.get("label_status_door").vw.setLeft((int)((views.get("label_status_light").vw.getLeft() + views.get("label_status_light").vw.getWidth())));
//BA.debugLineNum = 38;BA.debugLine="Label_status_door.Width =  26%x"[Main/General script]
views.get("label_status_door").vw.setWidth((int)((26d / 100 * width)));
//BA.debugLineNum = 39;BA.debugLine="Label_status_door.Height=5%y"[Main/General script]
views.get("label_status_door").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 41;BA.debugLine="Panel7.Top = Label_status_door.Bottom"[Main/General script]
views.get("panel7").vw.setTop((int)((views.get("label_status_door").vw.getTop() + views.get("label_status_door").vw.getHeight())));
//BA.debugLineNum = 42;BA.debugLine="Panel7.Width = 98%x"[Main/General script]
views.get("panel7").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 43;BA.debugLine="Panel7.Height = 1%y"[Main/General script]
views.get("panel7").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 44;BA.debugLine="Panel7.HorizontalCenter = 50%x"[Main/General script]
views.get("panel7").vw.setLeft((int)((50d / 100 * width) - (views.get("panel7").vw.getWidth() / 2)));
//BA.debugLineNum = 46;BA.debugLine="Btn_Room_off.Top = Panel7.Bottom"[Main/General script]
views.get("btn_room_off").vw.setTop((int)((views.get("panel7").vw.getTop() + views.get("panel7").vw.getHeight())));
//BA.debugLineNum = 47;BA.debugLine="Btn_Room_off.Left = 1%x"[Main/General script]
views.get("btn_room_off").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 48;BA.debugLine="Btn_Room_off.Width = 42%x"[Main/General script]
views.get("btn_room_off").vw.setWidth((int)((42d / 100 * width)));
//BA.debugLineNum = 49;BA.debugLine="Btn_Room_off.Height = 7%y"[Main/General script]
views.get("btn_room_off").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 51;BA.debugLine="ImageView_Room.Top  = Panel7.Bottom"[Main/General script]
views.get("imageview_room").vw.setTop((int)((views.get("panel7").vw.getTop() + views.get("panel7").vw.getHeight())));
//BA.debugLineNum = 52;BA.debugLine="ImageView_Room.Width = 12%x"[Main/General script]
views.get("imageview_room").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 53;BA.debugLine="ImageView_Room.Height = 7%y"[Main/General script]
views.get("imageview_room").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 54;BA.debugLine="ImageView_Room.Left = Btn_Room_off.Right +1%x"[Main/General script]
views.get("imageview_room").vw.setLeft((int)((views.get("btn_room_off").vw.getLeft() + views.get("btn_room_off").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 56;BA.debugLine="Btn_Room_on.Top = Panel7.Bottom"[Main/General script]
views.get("btn_room_on").vw.setTop((int)((views.get("panel7").vw.getTop() + views.get("panel7").vw.getHeight())));
//BA.debugLineNum = 57;BA.debugLine="Btn_Room_on.Width = 42%x"[Main/General script]
views.get("btn_room_on").vw.setWidth((int)((42d / 100 * width)));
//BA.debugLineNum = 58;BA.debugLine="Btn_Room_on.Height = 7%y"[Main/General script]
views.get("btn_room_on").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 59;BA.debugLine="Btn_Room_on.Left = ImageView_Room.Right+1%x"[Main/General script]
views.get("btn_room_on").vw.setLeft((int)((views.get("imageview_room").vw.getLeft() + views.get("imageview_room").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 61;BA.debugLine="Panel1.Top = ImageView_Room.Bottom"[Main/General script]
views.get("panel1").vw.setTop((int)((views.get("imageview_room").vw.getTop() + views.get("imageview_room").vw.getHeight())));
//BA.debugLineNum = 62;BA.debugLine="Panel1.Width = 98%x"[Main/General script]
views.get("panel1").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 63;BA.debugLine="Panel1.Height = 1%y"[Main/General script]
views.get("panel1").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 64;BA.debugLine="Panel1.HorizontalCenter = 50%x"[Main/General script]
views.get("panel1").vw.setLeft((int)((50d / 100 * width) - (views.get("panel1").vw.getWidth() / 2)));
//BA.debugLineNum = 66;BA.debugLine="Btn_off_main.Top = Panel1.Bottom"[Main/General script]
views.get("btn_off_main").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())));
//BA.debugLineNum = 67;BA.debugLine="Btn_off_main.Left = 1%x"[Main/General script]
views.get("btn_off_main").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 68;BA.debugLine="Btn_off_main.Width = 25%x"[Main/General script]
views.get("btn_off_main").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 69;BA.debugLine="Btn_off_main.Height = 7%y"[Main/General script]
views.get("btn_off_main").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 71;BA.debugLine="Label3.Left = Btn_off_main.Right"[Main/General script]
views.get("label3").vw.setLeft((int)((views.get("btn_off_main").vw.getLeft() + views.get("btn_off_main").vw.getWidth())));
//BA.debugLineNum = 72;BA.debugLine="Label3.Top = Panel1.Bottom"[Main/General script]
views.get("label3").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())));
//BA.debugLineNum = 73;BA.debugLine="Label3.Width = 36%x"[Main/General script]
views.get("label3").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 74;BA.debugLine="Label3.Height = 7%y"[Main/General script]
views.get("label3").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 76;BA.debugLine="ImageView_off_main.Top - Panel1.Bottom +1%y"[Main/General script]
views.get("imageview_off_main").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 77;BA.debugLine="ImageView_off_main.Left = Label3.Right"[Main/General script]
views.get("imageview_off_main").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())));
//BA.debugLineNum = 78;BA.debugLine="ImageView_off_main.Width = 12%x"[Main/General script]
views.get("imageview_off_main").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 79;BA.debugLine="ImageView_off_main.Height = 5%y"[Main/General script]
views.get("imageview_off_main").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 81;BA.debugLine="Btn_on_main.Left = ImageView_off_main.Right"[Main/General script]
views.get("btn_on_main").vw.setLeft((int)((views.get("imageview_off_main").vw.getLeft() + views.get("imageview_off_main").vw.getWidth())));
//BA.debugLineNum = 82;BA.debugLine="Btn_on_main.Width = 25%x"[Main/General script]
views.get("btn_on_main").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 83;BA.debugLine="Btn_on_main.Height = 7%y"[Main/General script]
views.get("btn_on_main").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 84;BA.debugLine="Btn_on_main.Top = Panel1.Bottom"[Main/General script]
views.get("btn_on_main").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())));
//BA.debugLineNum = 88;BA.debugLine="Btn_off_Decor.Top = Btn_off_main.Bottom"[Main/General script]
views.get("btn_off_decor").vw.setTop((int)((views.get("btn_off_main").vw.getTop() + views.get("btn_off_main").vw.getHeight())));
//BA.debugLineNum = 89;BA.debugLine="Btn_off_Decor.Left = 1%x"[Main/General script]
views.get("btn_off_decor").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 90;BA.debugLine="Btn_off_Decor.Width = 25%x"[Main/General script]
views.get("btn_off_decor").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 91;BA.debugLine="Btn_off_Decor.Height =7%y"[Main/General script]
views.get("btn_off_decor").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 93;BA.debugLine="Label4.Left = Btn_off_main.Right"[Main/General script]
views.get("label4").vw.setLeft((int)((views.get("btn_off_main").vw.getLeft() + views.get("btn_off_main").vw.getWidth())));
//BA.debugLineNum = 94;BA.debugLine="Label4.Top = Label3.Bottom"[Main/General script]
views.get("label4").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())));
//BA.debugLineNum = 95;BA.debugLine="Label4.Width = 36%x"[Main/General script]
views.get("label4").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 96;BA.debugLine="Label4.Height = 5%y"[Main/General script]
views.get("label4").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 98;BA.debugLine="ImageView_off_decor.Top = ImageView_off_main.Bottom+1%y"[Main/General script]
views.get("imageview_off_decor").vw.setTop((int)((views.get("imageview_off_main").vw.getTop() + views.get("imageview_off_main").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 99;BA.debugLine="ImageView_off_decor.Left = Label3.Right"[Main/General script]
views.get("imageview_off_decor").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())));
//BA.debugLineNum = 100;BA.debugLine="ImageView_off_decor.Width = 12%x"[Main/General script]
views.get("imageview_off_decor").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 101;BA.debugLine="ImageView_off_decor.Height = 5%y"[Main/General script]
views.get("imageview_off_decor").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 103;BA.debugLine="Btn_on_Decor.Left = ImageView_off_decor.Right"[Main/General script]
views.get("btn_on_decor").vw.setLeft((int)((views.get("imageview_off_decor").vw.getLeft() + views.get("imageview_off_decor").vw.getWidth())));
//BA.debugLineNum = 104;BA.debugLine="Btn_on_Decor.Width = 25%x"[Main/General script]
views.get("btn_on_decor").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 105;BA.debugLine="Btn_on_Decor.Height = 7%y"[Main/General script]
views.get("btn_on_decor").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 106;BA.debugLine="Btn_on_Decor.Top = Btn_on_main.Bottom"[Main/General script]
views.get("btn_on_decor").vw.setTop((int)((views.get("btn_on_main").vw.getTop() + views.get("btn_on_main").vw.getHeight())));
//BA.debugLineNum = 109;BA.debugLine="Panel2.Top = Btn_on_Decor.Bottom"[Main/General script]
views.get("panel2").vw.setTop((int)((views.get("btn_on_decor").vw.getTop() + views.get("btn_on_decor").vw.getHeight())));
//BA.debugLineNum = 110;BA.debugLine="Panel2.Width = 98%x"[Main/General script]
views.get("panel2").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 111;BA.debugLine="Panel2.Height = 1%y"[Main/General script]
views.get("panel2").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 112;BA.debugLine="Panel2.HorizontalCenter = 50%x"[Main/General script]
views.get("panel2").vw.setLeft((int)((50d / 100 * width) - (views.get("panel2").vw.getWidth() / 2)));
//BA.debugLineNum = 115;BA.debugLine="Btn_Bed_1.Top = Panel2.Bottom"[Main/General script]
views.get("btn_bed_1").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())));
//BA.debugLineNum = 116;BA.debugLine="Btn_Bed_1.Width = 36%x"[Main/General script]
views.get("btn_bed_1").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 117;BA.debugLine="Btn_Bed_1.Left = 1%x"[Main/General script]
views.get("btn_bed_1").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 118;BA.debugLine="Btn_Bed_1.Height = 7%y"[Main/General script]
views.get("btn_bed_1").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 121;BA.debugLine="ImageView_Bed1.Top = Panel2.Bottom +1%y"[Main/General script]
views.get("imageview_bed1").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 122;BA.debugLine="ImageView_Bed1.Width = 12%x"[Main/General script]
views.get("imageview_bed1").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 123;BA.debugLine="ImageView_Bed1.Height = 5%y"[Main/General script]
views.get("imageview_bed1").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 124;BA.debugLine="ImageView_Bed1.Left = Btn_Bed_1.Right"[Main/General script]
views.get("imageview_bed1").vw.setLeft((int)((views.get("btn_bed_1").vw.getLeft() + views.get("btn_bed_1").vw.getWidth())));
//BA.debugLineNum = 127;BA.debugLine="ImageView_Bed2.Top = Panel2.Bottom +1%y"[Main/General script]
views.get("imageview_bed2").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 128;BA.debugLine="ImageView_Bed2.Width = 12%x"[Main/General script]
views.get("imageview_bed2").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 129;BA.debugLine="ImageView_Bed2.Height = 5%y"[Main/General script]
views.get("imageview_bed2").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 130;BA.debugLine="ImageView_Bed2.Left = ImageView_Bed1.Right +2%x"[Main/General script]
views.get("imageview_bed2").vw.setLeft((int)((views.get("imageview_bed1").vw.getLeft() + views.get("imageview_bed1").vw.getWidth())+(2d / 100 * width)));
//BA.debugLineNum = 133;BA.debugLine="Btn_Bed_2.Top = Panel2.Bottom"[Main/General script]
views.get("btn_bed_2").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())));
//BA.debugLineNum = 134;BA.debugLine="Btn_Bed_2.Width = 36%x"[Main/General script]
views.get("btn_bed_2").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 135;BA.debugLine="Btn_Bed_2.Left = 1%x"[Main/General script]
views.get("btn_bed_2").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 136;BA.debugLine="Btn_Bed_2.Height = 7%y"[Main/General script]
views.get("btn_bed_2").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 137;BA.debugLine="Btn_Bed_2.Left = ImageView_Bed2.Right"[Main/General script]
views.get("btn_bed_2").vw.setLeft((int)((views.get("imageview_bed2").vw.getLeft() + views.get("imageview_bed2").vw.getWidth())));
//BA.debugLineNum = 139;BA.debugLine="Panel3.Top = Btn_Bed_1.Bottom"[Main/General script]
views.get("panel3").vw.setTop((int)((views.get("btn_bed_1").vw.getTop() + views.get("btn_bed_1").vw.getHeight())));
//BA.debugLineNum = 140;BA.debugLine="Panel3.Width = 98%x"[Main/General script]
views.get("panel3").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 141;BA.debugLine="Panel3.Height = 1%y"[Main/General script]
views.get("panel3").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 142;BA.debugLine="Panel3.HorizontalCenter = 50%x"[Main/General script]
views.get("panel3").vw.setLeft((int)((50d / 100 * width) - (views.get("panel3").vw.getWidth() / 2)));
//BA.debugLineNum = 144;BA.debugLine="Btn_Out_Light.Top = Panel3.Bottom"[Main/General script]
views.get("btn_out_light").vw.setTop((int)((views.get("panel3").vw.getTop() + views.get("panel3").vw.getHeight())));
//BA.debugLineNum = 145;BA.debugLine="Btn_Out_Light.Width = 36%x"[Main/General script]
views.get("btn_out_light").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 146;BA.debugLine="Btn_Out_Light.Left = 1%x"[Main/General script]
views.get("btn_out_light").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 147;BA.debugLine="Btn_Out_Light.Height = 7%y"[Main/General script]
views.get("btn_out_light").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 150;BA.debugLine="ImageView_Out_Door.Top = Panel3.Bottom +1%x"[Main/General script]
views.get("imageview_out_door").vw.setTop((int)((views.get("panel3").vw.getTop() + views.get("panel3").vw.getHeight())+(1d / 100 * width)));
//BA.debugLineNum = 151;BA.debugLine="ImageView_Out_Door.Width = 12%x"[Main/General script]
views.get("imageview_out_door").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 152;BA.debugLine="ImageView_Out_Door.Height = 5%y"[Main/General script]
views.get("imageview_out_door").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 153;BA.debugLine="ImageView_Out_Door.Left = Btn_Out_Light.Right"[Main/General script]
views.get("imageview_out_door").vw.setLeft((int)((views.get("btn_out_light").vw.getLeft() + views.get("btn_out_light").vw.getWidth())));
//BA.debugLineNum = 155;BA.debugLine="ImageView_Night.Top = Panel3.Bottom +1%x"[Main/General script]
views.get("imageview_night").vw.setTop((int)((views.get("panel3").vw.getTop() + views.get("panel3").vw.getHeight())+(1d / 100 * width)));
//BA.debugLineNum = 156;BA.debugLine="ImageView_Night.Width = 12%x"[Main/General script]
views.get("imageview_night").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 157;BA.debugLine="ImageView_Night.Height = 5%y"[Main/General script]
views.get("imageview_night").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 158;BA.debugLine="ImageView_Night.Left = ImageView_Out_Door.Right +2%x"[Main/General script]
views.get("imageview_night").vw.setLeft((int)((views.get("imageview_out_door").vw.getLeft() + views.get("imageview_out_door").vw.getWidth())+(2d / 100 * width)));
//BA.debugLineNum = 161;BA.debugLine="Btn_Night.Top = Panel3.Bottom"[Main/General script]
views.get("btn_night").vw.setTop((int)((views.get("panel3").vw.getTop() + views.get("panel3").vw.getHeight())));
//BA.debugLineNum = 162;BA.debugLine="Btn_Night.Width = 36%x"[Main/General script]
views.get("btn_night").vw.setWidth((int)((36d / 100 * width)));
//BA.debugLineNum = 163;BA.debugLine="Btn_Night.Left = 1%x"[Main/General script]
views.get("btn_night").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 164;BA.debugLine="Btn_Night.Height = 7%y"[Main/General script]
views.get("btn_night").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 165;BA.debugLine="Btn_Night.Left = ImageView_Night.Right"[Main/General script]
views.get("btn_night").vw.setLeft((int)((views.get("imageview_night").vw.getLeft() + views.get("imageview_night").vw.getWidth())));
//BA.debugLineNum = 168;BA.debugLine="Panel5.Top = Btn_Out_Light.Bottom"[Main/General script]
views.get("panel5").vw.setTop((int)((views.get("btn_out_light").vw.getTop() + views.get("btn_out_light").vw.getHeight())));
//BA.debugLineNum = 169;BA.debugLine="Panel5.Width = 98%x"[Main/General script]
views.get("panel5").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 170;BA.debugLine="Panel5.Height = 1%y"[Main/General script]
views.get("panel5").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 171;BA.debugLine="Panel5.HorizontalCenter = 50%x"[Main/General script]
views.get("panel5").vw.setLeft((int)((50d / 100 * width) - (views.get("panel5").vw.getWidth() / 2)));
//BA.debugLineNum = 174;BA.debugLine="Btn_off_AC.Top = Panel5.Bottom +1%y"[Main/General script]
views.get("btn_off_ac").vw.setTop((int)((views.get("panel5").vw.getTop() + views.get("panel5").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 175;BA.debugLine="Btn_off_AC.Width = 25%x"[Main/General script]
views.get("btn_off_ac").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 176;BA.debugLine="Btn_off_AC.Left = 1%x"[Main/General script]
views.get("btn_off_ac").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 177;BA.debugLine="Btn_off_AC.Height = 7%y"[Main/General script]
views.get("btn_off_ac").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 179;BA.debugLine="Lbl_Him.Top = Panel5.Bottom"[Main/General script]
views.get("lbl_him").vw.setTop((int)((views.get("panel5").vw.getTop() + views.get("panel5").vw.getHeight())));
//BA.debugLineNum = 180;BA.debugLine="Lbl_Him.Width = 16%x"[Main/General script]
views.get("lbl_him").vw.setWidth((int)((16d / 100 * width)));
//BA.debugLineNum = 181;BA.debugLine="Lbl_Him.Height =4%y"[Main/General script]
views.get("lbl_him").vw.setHeight((int)((4d / 100 * height)));
//BA.debugLineNum = 182;BA.debugLine="Lbl_Him.Left = Btn_off_AC.Right +1%x"[Main/General script]
views.get("lbl_him").vw.setLeft((int)((views.get("btn_off_ac").vw.getLeft() + views.get("btn_off_ac").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 185;BA.debugLine="ImageView1_AC.Top = Panel5.Bottom"[Main/General script]
views.get("imageview1_ac").vw.setTop((int)((views.get("panel5").vw.getTop() + views.get("panel5").vw.getHeight())));
//BA.debugLineNum = 186;BA.debugLine="ImageView1_AC.Width = 31%x"[Main/General script]
views.get("imageview1_ac").vw.setWidth((int)((31d / 100 * width)));
//BA.debugLineNum = 187;BA.debugLine="ImageView1_AC.Height = 9%y"[Main/General script]
views.get("imageview1_ac").vw.setHeight((int)((9d / 100 * height)));
//BA.debugLineNum = 188;BA.debugLine="ImageView1_AC.Left = Lbl_Him.Right"[Main/General script]
views.get("imageview1_ac").vw.setLeft((int)((views.get("lbl_him").vw.getLeft() + views.get("lbl_him").vw.getWidth())));
//BA.debugLineNum = 191;BA.debugLine="Btn_on_AC.Top = Panel5.Bottom +1%y"[Main/General script]
views.get("btn_on_ac").vw.setTop((int)((views.get("panel5").vw.getTop() + views.get("panel5").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 192;BA.debugLine="Btn_on_AC.Width = 25%x"[Main/General script]
views.get("btn_on_ac").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 193;BA.debugLine="Btn_on_AC.Left = 1%x"[Main/General script]
views.get("btn_on_ac").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 194;BA.debugLine="Btn_on_AC.Height = 7%y"[Main/General script]
views.get("btn_on_ac").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 195;BA.debugLine="Btn_on_AC.Left = ImageView1_AC.Right"[Main/General script]
views.get("btn_on_ac").vw.setLeft((int)((views.get("imageview1_ac").vw.getLeft() + views.get("imageview1_ac").vw.getWidth())));
//BA.debugLineNum = 199;BA.debugLine="Label2.Top = Btn_off_AC.Bottom +1%y"[Main/General script]
views.get("label2").vw.setTop((int)((views.get("btn_off_ac").vw.getTop() + views.get("btn_off_ac").vw.getHeight())+(1d / 100 * height)));
//BA.debugLineNum = 200;BA.debugLine="Label2.Width = 14%x"[Main/General script]
views.get("label2").vw.setWidth((int)((14d / 100 * width)));
//BA.debugLineNum = 201;BA.debugLine="Label2.Left = 1%x"[Main/General script]
views.get("label2").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 202;BA.debugLine="Label2.Height = 5%y"[Main/General script]
views.get("label2").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 205;BA.debugLine="Lbl_temp.Top = Lbl_Him.Bottom + .5%x"[Main/General script]
views.get("lbl_temp").vw.setTop((int)((views.get("lbl_him").vw.getTop() + views.get("lbl_him").vw.getHeight())+(.5d / 100 * width)));
//BA.debugLineNum = 206;BA.debugLine="Lbl_temp.Width = 16%x"[Main/General script]
views.get("lbl_temp").vw.setWidth((int)((16d / 100 * width)));
//BA.debugLineNum = 207;BA.debugLine="Lbl_temp.Height = 4%y"[Main/General script]
views.get("lbl_temp").vw.setHeight((int)((4d / 100 * height)));
//BA.debugLineNum = 208;BA.debugLine="Lbl_temp.Left = Btn_off_AC.Right + 1%x"[Main/General script]
views.get("lbl_temp").vw.setLeft((int)((views.get("btn_off_ac").vw.getLeft() + views.get("btn_off_ac").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 211;BA.debugLine="SeekBar_AC.Left = Label2.Right"[Main/General script]
views.get("seekbar_ac").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())));
//BA.debugLineNum = 212;BA.debugLine="SeekBar_AC.Top = Lbl_temp.Bottom +2%x"[Main/General script]
views.get("seekbar_ac").vw.setTop((int)((views.get("lbl_temp").vw.getTop() + views.get("lbl_temp").vw.getHeight())+(2d / 100 * width)));
//BA.debugLineNum = 213;BA.debugLine="SeekBar_AC.Height =9%x"[Main/General script]
views.get("seekbar_ac").vw.setHeight((int)((9d / 100 * width)));
//BA.debugLineNum = 214;BA.debugLine="SeekBar_AC.Width =70%x"[Main/General script]
views.get("seekbar_ac").vw.setWidth((int)((70d / 100 * width)));
//BA.debugLineNum = 217;BA.debugLine="Panel4.Top = SeekBar_AC.Bottom +.5%y"[Main/General script]
views.get("panel4").vw.setTop((int)((views.get("seekbar_ac").vw.getTop() + views.get("seekbar_ac").vw.getHeight())+(.5d / 100 * height)));
//BA.debugLineNum = 218;BA.debugLine="Panel4.Width = 98%x"[Main/General script]
views.get("panel4").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 219;BA.debugLine="Panel4.Height = 1%y"[Main/General script]
views.get("panel4").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 220;BA.debugLine="Panel4.HorizontalCenter = 50%x"[Main/General script]
views.get("panel4").vw.setLeft((int)((50d / 100 * width) - (views.get("panel4").vw.getWidth() / 2)));
//BA.debugLineNum = 223;BA.debugLine="Btn_Door.Top = Panel4.Bottom"[Main/General script]
views.get("btn_door").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight())));
//BA.debugLineNum = 224;BA.debugLine="Btn_Door.Width = 24%x"[Main/General script]
views.get("btn_door").vw.setWidth((int)((24d / 100 * width)));
//BA.debugLineNum = 225;BA.debugLine="Btn_Door.Left = 1%x"[Main/General script]
views.get("btn_door").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 226;BA.debugLine="Btn_Door.Height = 8%y"[Main/General script]
views.get("btn_door").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 230;BA.debugLine="Btn_Garden.Top = Panel4.Bottom"[Main/General script]
views.get("btn_garden").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight())));
//BA.debugLineNum = 231;BA.debugLine="Btn_Garden.Width = 24%x"[Main/General script]
views.get("btn_garden").vw.setWidth((int)((24d / 100 * width)));
//BA.debugLineNum = 232;BA.debugLine="Btn_Garden.Left = 1%x"[Main/General script]
views.get("btn_garden").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 233;BA.debugLine="Btn_Garden.Height = 8%y"[Main/General script]
views.get("btn_garden").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 234;BA.debugLine="Btn_Garden.Left = Btn_Door.Right+1%x"[Main/General script]
views.get("btn_garden").vw.setLeft((int)((views.get("btn_door").vw.getLeft() + views.get("btn_door").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 237;BA.debugLine="Btn_Kitchen.Top = Panel4.Bottom"[Main/General script]
views.get("btn_kitchen").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight())));
//BA.debugLineNum = 238;BA.debugLine="Btn_Kitchen.Width = 24%x"[Main/General script]
views.get("btn_kitchen").vw.setWidth((int)((24d / 100 * width)));
//BA.debugLineNum = 239;BA.debugLine="Btn_Kitchen.Left = 1%x"[Main/General script]
views.get("btn_kitchen").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 240;BA.debugLine="Btn_Kitchen.Height = 8%y"[Main/General script]
views.get("btn_kitchen").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 241;BA.debugLine="Btn_Kitchen.Left = Btn_Garden.Right+1%x"[Main/General script]
views.get("btn_kitchen").vw.setLeft((int)((views.get("btn_garden").vw.getLeft() + views.get("btn_garden").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 244;BA.debugLine="Btn_Security.Top = Panel4.Bottom"[Main/General script]
views.get("btn_security").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight())));
//BA.debugLineNum = 245;BA.debugLine="Btn_Security.Width = 23%x"[Main/General script]
views.get("btn_security").vw.setWidth((int)((23d / 100 * width)));
//BA.debugLineNum = 246;BA.debugLine="Btn_Security.Left = 1%x"[Main/General script]
views.get("btn_security").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 247;BA.debugLine="Btn_Security.Height = 8%y"[Main/General script]
views.get("btn_security").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 248;BA.debugLine="Btn_Security.Left = Btn_Kitchen.Right+1%x"[Main/General script]
views.get("btn_security").vw.setLeft((int)((views.get("btn_kitchen").vw.getLeft() + views.get("btn_kitchen").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 251;BA.debugLine="Panel6.Top =  Btn_Kitchen.Bottom"[Main/General script]
views.get("panel6").vw.setTop((int)((views.get("btn_kitchen").vw.getTop() + views.get("btn_kitchen").vw.getHeight())));
//BA.debugLineNum = 252;BA.debugLine="Panel6.Width = 98%x"[Main/General script]
views.get("panel6").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 253;BA.debugLine="Panel6.Height = 1%y"[Main/General script]
views.get("panel6").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 254;BA.debugLine="Panel6.HorizontalCenter = 50%x"[Main/General script]
views.get("panel6").vw.setLeft((int)((50d / 100 * width) - (views.get("panel6").vw.getWidth() / 2)));
//BA.debugLineNum = 258;BA.debugLine="ImageView_Secur.Top = Panel2.Bottom"[Main/General script]
views.get("imageview_secur").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())));
//BA.debugLineNum = 259;BA.debugLine="ImageView_Secur.Height=15%y"[Main/General script]
views.get("imageview_secur").vw.setHeight((int)((15d / 100 * height)));
//BA.debugLineNum = 260;BA.debugLine="ImageView_Secur.Left = Btn_Bed_1.Right"[Main/General script]
views.get("imageview_secur").vw.setLeft((int)((views.get("btn_bed_1").vw.getLeft() + views.get("btn_bed_1").vw.getWidth())));
//BA.debugLineNum = 263;BA.debugLine="ImageView_lok.Top = Panel8.Bottom"[Main/General script]
views.get("imageview_lok").vw.setTop((int)((views.get("panel8").vw.getTop() + views.get("panel8").vw.getHeight())));
//BA.debugLineNum = 264;BA.debugLine="ImageView_lok.Height = 5%y"[Main/General script]
views.get("imageview_lok").vw.setHeight((int)((5d / 100 * height)));
//BA.debugLineNum = 265;BA.debugLine="ImageView_lok.Left = 2%x"[Main/General script]
views.get("imageview_lok").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 268;BA.debugLine="Label_secu_text.Top = Panel8.Bottom"[Main/General script]
views.get("label_secu_text").vw.setTop((int)((views.get("panel8").vw.getTop() + views.get("panel8").vw.getHeight())));
//BA.debugLineNum = 269;BA.debugLine="Label_secu_text.HorizontalCenter = 50%x"[Main/General script]
views.get("label_secu_text").vw.setLeft((int)((50d / 100 * width) - (views.get("label_secu_text").vw.getWidth() / 2)));
//BA.debugLineNum = 270;BA.debugLine="Label_secu_text.Height = 8%x"[Main/General script]
views.get("label_secu_text").vw.setHeight((int)((8d / 100 * width)));
//BA.debugLineNum = 273;BA.debugLine="Label_Cell.Top = Panel6.Bottom"[Main/General script]
views.get("label_cell").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 274;BA.debugLine="Label_Cell.Left =49%x"[Main/General script]
views.get("label_cell").vw.setLeft((int)((49d / 100 * width)));
//BA.debugLineNum = 275;BA.debugLine="Label_Cell.Width = 32%x"[Main/General script]
views.get("label_cell").vw.setWidth((int)((32d / 100 * width)));
//BA.debugLineNum = 276;BA.debugLine="Label_Cell.Height=7%y"[Main/General script]
views.get("label_cell").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 279;BA.debugLine="Panel9.Top = Label_Cell.Bottom"[Main/General script]
views.get("panel9").vw.setTop((int)((views.get("label_cell").vw.getTop() + views.get("label_cell").vw.getHeight())));
//BA.debugLineNum = 280;BA.debugLine="Panel9.Width = 98%x"[Main/General script]
views.get("panel9").vw.setWidth((int)((98d / 100 * width)));
//BA.debugLineNum = 281;BA.debugLine="Panel9.Height = 1%y"[Main/General script]
views.get("panel9").vw.setHeight((int)((1d / 100 * height)));
//BA.debugLineNum = 282;BA.debugLine="Panel9.HorizontalCenter = 50%x"[Main/General script]
views.get("panel9").vw.setLeft((int)((50d / 100 * width) - (views.get("panel9").vw.getWidth() / 2)));
//BA.debugLineNum = 303;BA.debugLine="Btn_Connect.Top = Panel9.Bottom"[Main/General script]
views.get("btn_connect").vw.setTop((int)((views.get("panel9").vw.getTop() + views.get("panel9").vw.getHeight())));
//BA.debugLineNum = 304;BA.debugLine="Btn_Connect.Width = 32%x"[Main/General script]
views.get("btn_connect").vw.setWidth((int)((32d / 100 * width)));
//BA.debugLineNum = 305;BA.debugLine="Btn_Connect.Left = 1%x"[Main/General script]
views.get("btn_connect").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 306;BA.debugLine="Btn_Connect.Height = 7%y"[Main/General script]
views.get("btn_connect").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 309;BA.debugLine="Btn_DisConnect.Top =Panel9.Bottom"[Main/General script]
views.get("btn_disconnect").vw.setTop((int)((views.get("panel9").vw.getTop() + views.get("panel9").vw.getHeight())));
//BA.debugLineNum = 310;BA.debugLine="Btn_DisConnect.Width = 32%x"[Main/General script]
views.get("btn_disconnect").vw.setWidth((int)((32d / 100 * width)));
//BA.debugLineNum = 311;BA.debugLine="Btn_DisConnect.Height = 7%y"[Main/General script]
views.get("btn_disconnect").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 312;BA.debugLine="Btn_DisConnect.Left = Btn_Connect.Right+1%x"[Main/General script]
views.get("btn_disconnect").vw.setLeft((int)((views.get("btn_connect").vw.getLeft() + views.get("btn_connect").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 315;BA.debugLine="Btn_exit.Top = Panel9.Bottom"[Main/General script]
views.get("btn_exit").vw.setTop((int)((views.get("panel9").vw.getTop() + views.get("panel9").vw.getHeight())));
//BA.debugLineNum = 316;BA.debugLine="Btn_exit.Width = 32%x"[Main/General script]
views.get("btn_exit").vw.setWidth((int)((32d / 100 * width)));
//BA.debugLineNum = 317;BA.debugLine="Btn_exit.Height = 7%y"[Main/General script]
views.get("btn_exit").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 318;BA.debugLine="Btn_exit.Left = Btn_DisConnect.Right+1%x"[Main/General script]
views.get("btn_exit").vw.setLeft((int)((views.get("btn_disconnect").vw.getLeft() + views.get("btn_disconnect").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 321;BA.debugLine="ImageView_100.Top = Panel6.Bottom"[Main/General script]
views.get("imageview_100").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 322;BA.debugLine="ImageView_100.Width = 12%x"[Main/General script]
views.get("imageview_100").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 323;BA.debugLine="ImageView_100.Height = 7%y"[Main/General script]
views.get("imageview_100").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 324;BA.debugLine="ImageView_100.Left = 1%x"[Main/General script]
views.get("imageview_100").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 327;BA.debugLine="ImageView_200.Top = Panel6.Bottom"[Main/General script]
views.get("imageview_200").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 328;BA.debugLine="ImageView_200.Width = 12%x"[Main/General script]
views.get("imageview_200").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 329;BA.debugLine="ImageView_200.Height = 7%y"[Main/General script]
views.get("imageview_200").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 330;BA.debugLine="ImageView_200.Left = ImageView_100.Right"[Main/General script]
views.get("imageview_200").vw.setLeft((int)((views.get("imageview_100").vw.getLeft() + views.get("imageview_100").vw.getWidth())));
//BA.debugLineNum = 333;BA.debugLine="ImageView_300.Top = Panel6.Bottom"[Main/General script]
views.get("imageview_300").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 334;BA.debugLine="ImageView_300.Width = 12%x"[Main/General script]
views.get("imageview_300").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 335;BA.debugLine="ImageView_300.Height = 7%y"[Main/General script]
views.get("imageview_300").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 336;BA.debugLine="ImageView_300.Left = ImageView_200.Right"[Main/General script]
views.get("imageview_300").vw.setLeft((int)((views.get("imageview_200").vw.getLeft() + views.get("imageview_200").vw.getWidth())));
//BA.debugLineNum = 338;BA.debugLine="ImageView_400.Top = Panel6.Bottom"[Main/General script]
views.get("imageview_400").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 339;BA.debugLine="ImageView_400.Width = 12%x"[Main/General script]
views.get("imageview_400").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 340;BA.debugLine="ImageView_400.Height = 7%y"[Main/General script]
views.get("imageview_400").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 341;BA.debugLine="ImageView_400.Left = ImageView_300.Right"[Main/General script]
views.get("imageview_400").vw.setLeft((int)((views.get("imageview_300").vw.getLeft() + views.get("imageview_300").vw.getWidth())));
//BA.debugLineNum = 343;BA.debugLine="ImageView_Pto.Top = Panel6.Bottom"[Main/General script]
views.get("imageview_pto").vw.setTop((int)((views.get("panel6").vw.getTop() + views.get("panel6").vw.getHeight())));
//BA.debugLineNum = 344;BA.debugLine="ImageView_Pto.Width = 18%x"[Main/General script]
views.get("imageview_pto").vw.setWidth((int)((18d / 100 * width)));
//BA.debugLineNum = 345;BA.debugLine="ImageView_Pto.Height = 7%y"[Main/General script]
views.get("imageview_pto").vw.setHeight((int)((7d / 100 * height)));
//BA.debugLineNum = 346;BA.debugLine="ImageView_Pto.Left = Label_Cell.Right"[Main/General script]
views.get("imageview_pto").vw.setLeft((int)((views.get("label_cell").vw.getLeft() + views.get("label_cell").vw.getWidth())));
//BA.debugLineNum = 350;BA.debugLine="ImageView_Garden.Top = Panel2.Bottom"[Main/General script]
views.get("imageview_garden").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())));
//BA.debugLineNum = 351;BA.debugLine="ImageView_Garden.Width = 26%x"[Main/General script]
views.get("imageview_garden").vw.setWidth((int)((26d / 100 * width)));
//BA.debugLineNum = 352;BA.debugLine="ImageView_Garden.Height=15%y"[Main/General script]
views.get("imageview_garden").vw.setHeight((int)((15d / 100 * height)));
//BA.debugLineNum = 353;BA.debugLine="ImageView_Garden.Left = Btn_Bed_1.Right"[Main/General script]
views.get("imageview_garden").vw.setLeft((int)((views.get("btn_bed_1").vw.getLeft() + views.get("btn_bed_1").vw.getWidth())));
//BA.debugLineNum = 357;BA.debugLine="ImageView_Garden_dry.Top = Panel2.Bottom"[Main/General script]
views.get("imageview_garden_dry").vw.setTop((int)((views.get("panel2").vw.getTop() + views.get("panel2").vw.getHeight())));
//BA.debugLineNum = 358;BA.debugLine="ImageView_Garden_dry.Width = 26%x"[Main/General script]
views.get("imageview_garden_dry").vw.setWidth((int)((26d / 100 * width)));
//BA.debugLineNum = 359;BA.debugLine="ImageView_Garden_dry.Height=15%y"[Main/General script]
views.get("imageview_garden_dry").vw.setHeight((int)((15d / 100 * height)));
//BA.debugLineNum = 360;BA.debugLine="ImageView_Garden_dry.Left = Btn_Bed_1.Right"[Main/General script]
views.get("imageview_garden_dry").vw.setLeft((int)((views.get("btn_bed_1").vw.getLeft() + views.get("btn_bed_1").vw.getWidth())));

}
}