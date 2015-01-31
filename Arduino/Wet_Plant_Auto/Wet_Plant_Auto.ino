/*
This software let you control in automatic way the water-pump without using an
Internet connection, just settings the frequency and the duration of the operation
Version: 1.0
Author: Giacomo Bellazzi
*/
int pump = 9;
int duration;
int frequency;
int pinD = A0;
int pinF = A1;
int pushButton = 8;
int timePassed1 = 0;
int timePassed2 = 0;
boolean par = true;

void setup() {
  pinMode(pump,OUTPUT);
  Serial.begin(9600); //For debug only
  pinMode(pushButton,INPUT);
  setOption();
}

void loop() {
  timePassed1 = millis();
  if(isPressed()){
    setOption();
  }
  if(isTimeToWet()){
    wetPlant();
    timePassed2 = millis();
   }
  delay(1000);
}

/*This function set the frequency and duration from the two potentiometer*/
void setOption(){
  frequency=map(analogRead(pinF),0,1023,12,106)*1000*60;
  duration = map(analogRead(pinD),0,1023,30,120)*1000;
  Serial.println(frequency);
  Serial.println(duration);
}

/*This function detect if the push button has been pressed*/
boolean isPressed(){
  if(digitalRead(pushButton)==1){
    return true;
  }else{
    return false;
  }
}

/*This function wet the plant by closing and opening a relay*/
void wetPlant(){
  digitalWrite(pump,HIGH);
  delay(duration);
  digitalWrite(pump,LOW);
}

/*This function detect if it is time to wet the plant*/
boolean isTimeToWet(){
  int dif = timePassed1-timePassed2;
  if(dif>=frequency){
    return true;
  }else{
    return false;
  }
}

