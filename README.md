# SampleAppArch
Sample Application using the app archieture
-Sample Navigation Drawer

Module
- Fragment Communication
- Connectivity
- [Feed Entry](feedentry)
  <p>
  <img src="https://user-images.githubusercontent.com/5112837/30317424-aca50e60-97dc-11e7-89a3-ea5d4ff097f0.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30317423-aca26c64-97dc-11e7-8fbb-047ef7df27d3.png" width="150">
  <img src="https://user-images.githubusercontent.com/5112837/30267309-ae590838-9714-11e7-92a9-387a64b26552.png" width="150">
  </p>
- Location


**package name used as Yigit proposed layer (datasource , responsitory, ui, viewModel) in 2017 Google IO**


https://youtu.be/FrteWKKVyzI?t=29m41s


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


