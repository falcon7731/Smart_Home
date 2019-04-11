#include <SoftwareSerial.h>
SoftwareSerial bluetooth(3, 2); // RX, TX 
//---------------------------------------------------28/12/1395--------------
#include <dht.h>
dht DHT;
#define DHT21_PIN 8
//-----------------------------------
const int SensorPin = 7;
int SensorState = 0;    
//----------------------------------

int led_Decor = 0;
int led_out_Door = 1;
int led_Night = 11;
int led_Bed1 = 9;
int led_Bed2 = 10;
int led_Main = 6;

int D3 = 12;
int D4 = 13;
//---------------------- MQ-8 ---------------
const int spin = A0;
int SensorValue = 0;
//------------------------------------------
void setup() {

  pinMode(led_out_Door, OUTPUT);
  pinMode(led_Night, OUTPUT);
  pinMode(led_Bed1, OUTPUT);
  pinMode(led_Bed2, OUTPUT);
  pinMode(led_Main, OUTPUT);
  pinMode(led_Decor, OUTPUT);
  //--------------------------------Fan----------------
  digitalWrite(D3, LOW);
  digitalWrite(D4, LOW);
  pinMode(D3, OUTPUT);
  pinMode(D4, OUTPUT);
  //-------------------------------Security-----------------
  pinMode(SensorPin, INPUT);
  //-----------------------------------------------------
  bluetooth.begin(38400);
  //Serial.begin(9600);
  bluetooth.setTimeout(50) ;
  delay(3000);
}
void loop() {

  //--------------------------- WORD_Filter ------------------

  if (bluetooth.available()) {
    String input =  bluetooth.readString();
    String speed1 =  WORD_Filter ( input , "SS", "SE" );
    String go_input =  WORD_Filter ( input , "RS_", "_RE" );
    int speed2 = speed1.toInt() ;

    //-----------------Speed_Pin pwm 6----------------

    if ( go_input == "AcOn") {

      wle_go("AcOn", speed2);

    }

    if ( go_input == "AcOff") {

      wle_go("AcOff", speed2);

    }
    //------------------------ Kitchen and Main Light Pin 6 ----------------------

    if ( go_input == "Mainon") {

      //wle_go("Kiton", speed2);
      digitalWrite(led_Main, HIGH);
    }

    if ( go_input == "Mainoff") {

      //wle_go("Kitoff", speed2);
      digitalWrite(led_Main, LOW);
    }

    //------------------------ Bedroom1 Pin 9 ----------------------

    if ( go_input == "Bed1on") {

      //wle_go("Kiton", speed2);
      digitalWrite(led_Bed1, HIGH);

    }

    if ( go_input == "Bed1off") {

      //wle_go("Kitoff", speed2);
      digitalWrite(led_Bed1, LOW);
    }
    //------------------------ Bedroom2 Pin 10 ----------------------

    if ( go_input == "Bed2on") {

      //wle_go("Kiton", speed2);
      digitalWrite(led_Bed2, HIGH);

    }

    if ( go_input == "Bed2off") {

      //wle_go("Kitoff", speed2);
      digitalWrite(led_Bed2, LOW);

    }
    //------------------------ Night Pin 11 ----------------------

    if ( go_input == "Nighton") {

      //wle_go("Nighton", speed2);
      digitalWrite(led_Night, HIGH);

    }

    if ( go_input == "Nightoff") {

      //wle_go("Nightoff", speed2);
      digitalWrite(led_Night, LOW);

    }
    //------------------------ Decor Pin 0 ----------------------

    if ( go_input == "Decoron") {

      //wle_go("Nighton", speed2);
      digitalWrite(led_Decor, HIGH);

    }

    if ( go_input == "Decoroff") {

      //wle_go("Nightoff", speed2);
      digitalWrite(led_Decor, LOW);

    }
    //--------------------- Out Door Pin 1 --------------------
    if ( go_input == "LightDoorOn") {

      //wle_go("Kiton", speed2);
      digitalWrite(led_out_Door, HIGH);

    }

    if ( go_input == "LightDoorOff") {

      //wle_go("Kitoff", speed2);
      digitalWrite(led_out_Door, LOW);
    }
    //---------------------DOOR----------------------

    if ( go_input == "DoorOpen") {

      digitalWrite(led_out_Door, HIGH);
      delay(2000);

      digitalWrite(D3, HIGH);
      digitalWrite(D4, LOW);
      delay(800);
      digitalWrite(D3, LOW);
      digitalWrite(D4, LOW);

    }

    if ( go_input == "DoorClose") {

      delay(2000);
      
      digitalWrite(D3, LOW);
      digitalWrite(D4, HIGH);
      delay(800);
      digitalWrite(D3, LOW);
      digitalWrite(D4, LOW);

      delay(2000);
      digitalWrite(led_out_Door, LOW);

    }
    //--------------------- Room Off  And On --------------------
    if ( go_input == "RoomOn") {

      digitalWrite(led_Bed2, HIGH);
      digitalWrite(led_Bed1, HIGH);
      digitalWrite(led_Main, HIGH);

      digitalWrite(led_Night, LOW);
      digitalWrite(led_Decor, LOW);

    }

    if ( go_input == "RoomOff") {

      digitalWrite(led_Decor, LOW);
      digitalWrite(led_Bed2, LOW);
      digitalWrite(led_Bed1, LOW);
      digitalWrite(led_Main, LOW);
      digitalWrite(led_Decor, LOW);
    }
    //---------------------------------- Security pin 12------------
    if ( go_input == "SecOn") {

      //digitalWrite(ledPin, HIGH);
    }

    if ( go_input == "SecOff") {

      // digitalWrite(ledPin, LOW);
    }

    //-------------------------------------------
    //Serial.print(input);
    //Serial.print("  ");
    Serial.println(go_input);
  }
  //------------------- send Temperature and Humidity and MQ_O8 -------------

  const unsigned long fiveMinutes =  6 * 1000UL;
  static unsigned long lastSampleTime = 0 - fiveMinutes;  // initialize such that a reading is due the first time through loop()

  unsigned long now = millis();
  if (now - lastSampleTime >= fiveMinutes)
  {
    lastSampleTime += fiveMinutes;

    int chk = DHT.read21(DHT21_PIN);
    SensorValue = analogRead(spin);
    SensorState = digitalRead(SensorPin);


    bluetooth.print(" H");
    bluetooth.print( DHT.humidity );
    bluetooth.print("h");

    delay(300);

    bluetooth.print(" T");
    bluetooth.print( DHT.temperature)  ;
    bluetooth.print("t");

    delay(300);

    bluetooth.print(" G");
    bluetooth.print(SensorValue);
    bluetooth.print("g");

    bluetooth.print(" Q");
    bluetooth.print(SensorState)  ;
    bluetooth.print("q");

    //Obtaining and sending the solar panel's voltage
    int Cell_Value = analogRead(A2);
    bluetooth.print(" V");
    bluetooth.print(Cell_Value);
    bluetooth.print("v");

    int Soil_Water_Level = analogRead(A3);
    bluetooth.print(" S");
    bluetooth.print(Soil_Water_Level);
    bluetooth.print("s");

    delay(300);
    //---------------------------- MQ-2 -----------
    if (SensorValue >= 750)
    {
      // digitalWrite(led_Gaz, HIGH);
    }
    else
    {
      //digitalWrite(led_Gaz, LOW);
    }

    //-------------------------------------------------
    // Serial.print(DHT.temperature);
    //Serial.print("    ");
    //Serial.println(DHT.humidity);
    //Serial.println(SensorValue);
    //Serial.println( SensorState);
  }
}
//----------------------- MOTOR Fan LIBRARY--------------------------

void wle_go( String motor , int speed1 ) {

  //----------------- Fan Pin ---------
  int D0 = 4;
  int Speed_Pin = 5;
  //--------------------Enabel pin---------------
  analogWrite(Speed_Pin, speed1);
  //analogWrite(led_Kitchen, speed1);
  //*************************
  pinMode(D0, OUTPUT);
  //-------------------------- Air Coolling ------------
  if (motor == "AcOn" ) {

    digitalWrite(D0, HIGH);

  }

  if (motor == "AcOff" ) {

    digitalWrite(D0, LOW);

  }
}
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
//----------------------- word filter--------------
String WORD_Filter ( String imput_word, String first_word, String last_word ) {
  String result;
  int n1 , n2 ;
  n1 = imput_word.indexOf(first_word) + first_word.length() ;
  n2 = imput_word.indexOf(last_word);
  result = imput_word.substring(n1 , n2);
  return result;

}
