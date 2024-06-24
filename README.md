## Heart Rate - mobile application for measuring pulse
The application is written in Kotlin + Jetpack Compose. Pulse measurement records are stored in a database implemented using Room. The good structure of the project makes it easy to scale. Translating to any language or changing the font takes less than 5 minutes, as all the texts are located in ../res/values/strings.xml.

## Application Loading Screen
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/e235655c-1eda-488a-9c51-9d18cb489d1b" width="200"/>
</p>

## Onboarding Screens
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/346c2930-4449-4ef1-8210-123489518cc3" width="200" />
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/d7758111-0815-4317-b029-5fb6942dab63" width="200" />
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/a25985ab-bfb9-42d9-b60b-673d9de79105" width="200" />
</p>
The input screens are implemented using RadioButton. To add a new record, just add a new OnboardingEntity object to the list located in OnboardingViewModel.

## Main Page and Camera Permission Screen
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/12f79b88-58b4-4949-810d-771fa096e21a" width="200"/>
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/9d50fbde-575c-4844-aa62-8569892f3466" width="200"/>
</p>
The main page has two buttons: one is located in the TopBar and allows you to go to the history page (pulse records over time), the second button takes you to the pulse measurement page if camera permission is granted. If permission is not granted, you will be taken to the "camera permission" screen.

## Pulse Measurement Screen
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/39cdca7e-9a83-4ed7-a484-32db2162d101" width="200"/>
</p>
The measurement screen contains a small window displaying the rear camera image, allowing you to correctly place your finger on the camera. When positioned correctly, pulsations can be observed. The screen also contains instructions aimed at helping the user take accurate measurements. In the center of the heart icon, you can observe the current pulse values during the measurement.
The camera and flashlight turn on immediately after transitioning to the measurement screen. This is done to reduce the number of buttons and make the measurement more intuitive.

## Results Screen
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/4e8cd317-0e82-465d-a894-6051c05690f4" width="200" />
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/0b0f55a4-d1a0-4814-8a93-840dfeb40a0d" width="200" />
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/445ed574-92b8-41cc-944a-a7d26ff0e7f6" width="200" />
</p>
The results screen contains a TopBar, a results card, and a button that takes you back to the main page. The card contains the time and date of the measurement and a strip with three colors that shows the pulse. If the pulse is below 60 BPM, it is slow. If the pulse is within 60-100 BPM, it is normal. If it is above 100 BPM, it is fast.

## History Screen
<p align="center">
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/aad671ce-5808-4687-b71c-8f4631085541" width="200"/>
  <img src="https://github.com/TENOVOD/Pulsotrone/assets/57965389/b5d70660-5a53-488d-9efa-7621909512bb" width="200"/>
</p>
To display observations from the database, LazyColumn was used. Each record contains brief information, namely the number of beats per minute, time, and date. To get the "result card," just click on any record. At the end of all results, there is a "Clear History" button. After clicking, all records are deleted from the database.
