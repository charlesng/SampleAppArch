# Communication-Fragments

# Sample from Android Developer :
- Master Detail Template (From Android Studio Gallery)
- android.v4.ViewPager
<p>
  <img src="https://user-images.githubusercontent.com/5112837/30728470-02f6b93e-9f8b-11e7-83f2-28d3dd4bf61f.png" width="250">
</p>


# ViewPager
- https://stackoverflow.com/questions/44272914/sharing-data-between-fragments-using-new-architecture-component-viewmodel/44655954#44655954
- Fragment A and Fragment B send simple text to each other
<p>
  <img src="https://user-images.githubusercontent.com/5112837/30728643-0769e634-9f8c-11e7-8206-fe57dd63b5d4.png" width="250">
  <img src="https://user-images.githubusercontent.com/5112837/30728467-02f17cda-9f8b-11e7-9240-b662d5280c49.png" width="250">
</p>


# Master Detail Template
- Use the template import first and modified the template to fit Android App Architecture
- https://stackoverflow.com/questions/44272914/sharing-data-between-fragments-using-new-architecture-component-viewmodel/44655954#44655954
- Main Points :
 - Small Device (MasterFragment -> DetailFragment by ShareViewModel)
 - Large Device (ListActivity -> DetailActivity by putExtra method)
 - To keep it to the same codebase, we need to defined both ShareViewModel in ListActivity and DetailActivity. However, the value setting in DetailActivity will use putExtra method to transfer data

<p>
<img src="https://user-images.githubusercontent.com/5112837/30728677-42590202-9f8c-11e7-88a9-1dc31b6e8894.png" width="250">
<img src="https://user-images.githubusercontent.com/5112837/30728469-02f60e80-9f8b-11e7-994d-611957fe46ac.png" width="250">
</p>
