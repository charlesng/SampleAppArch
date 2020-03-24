# SampleAppArch
Sample Application using the app archieture

#Getting Started
- Please install the JDK 1.8 and use the path as JAVA_HOME

#Debugging

## Run All Unit test
``` bash
$ ./gradlew testDebugUnitTest
```

## Run All Android Test
``` bash
$ ./gradlew connectedAndroidTest
```


Current Plan

| Implementation  | Description | Type |
| ------------- | ------------- | ----|
| Android Architecture Component (AAC) | Using the AAC as bone of the app structures  | App |
| MVP  | Extract the BaseFragment Features and link it to the AAC (e.g. ProgressDialog, AlertDialog)  | View, Presenter |
| Dagger2 | As the Dependency Injectection to the  | DI|
| Google Github sample for AAC Networkbound, ApiResponse | Reference the Networkbound class and related class to generic the API call and responses | Model Gateway |
| Android Data Binding Library | To easily bind the data to the UI and seperate the design and controller logic | Presenter |
| Crashlytics | To report the runtime error | Testing |



-Sample Navigation Drawer

Activity
- [Fragment Communication](app/src/main/java/com/cn29/aac/ui/masterdetail)
  <p>
  <img src="https://user-images.githubusercontent.com/5112837/30728470-02f6b93e-9f8b-11e7-83f2-28d3dd4bf61f.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30728643-0769e634-9f8c-11e7-8206-fe57dd63b5d4.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30728677-42590202-9f8c-11e7-88a9-1dc31b6e8894.png" width="150">
  </p>
  
- [Feed Entry](app/src/main/java/com/cn29/aac/ui/feedentry)
  <p>
  <img src="https://user-images.githubusercontent.com/5112837/30317424-aca50e60-97dc-11e7-89a3-ea5d4ff097f0.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30317423-aca26c64-97dc-11e7-8fbb-047ef7df27d3.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30267309-ae590838-9714-11e7-92a9-387a64b26552.png" width="150">
  </p>
- [Location](app/src/main/java/com/cn29/aac/ui/location)
  <p>
  <img src="https://user-images.githubusercontent.com/5112837/30799289-f985eba0-a20e-11e7-8c9c-ef36044cc663.png" width="150">
  </p>


**package name used as Yigit proposed layer (datasource , responsitory, ui, viewModel) in 2017 Google IO**


https://youtu.be/FrteWKKVyzI?t=29m41s

  <img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png" >

ui (UI Controllers , Fragments & Activities)
-
- Activities & Fragments
- Observers the View Model
- Keeps the UI up-to-date 
- Forwards user Actions back to the ViewModel

viewmodel 
-
- Prepares  keeps the data for the UI
- Includes LiveData, Observable etc
- Survives Configuration changes 
- The gateway for the UI Controller 

repository 
-
- The complete data model for the App
- Provide simple data modification & retrieval APIS
- Coordinates fetching , syncing , persisting etc from different data sources 

datasource
-
- API to the data sources 
- E.g. Retrofit for a REST API
- Room for local persistence
- External content providers for OS

utils
-
- Common Utils (Like Android databinding
- Inject Utils (Dependency Injection)

### How do I use this project?
This is a boilerplate project aimed to help bootstrap new Android MVP Applications. Feel free to fork this application. :)

### License
```
   Copyright (C) 2017 Sample App Architecture

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to SampleArrArch
Just make pull request. You are in!
