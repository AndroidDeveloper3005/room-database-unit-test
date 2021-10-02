# room-database-unit-test

to test retrofit and dagger hill we need to add our API KEY inside gradle.properties file like this ...API_KEY = "23662246-bb36ac340b68aa325d00f0345" 
after that add (buildConfigField("String","API_KEY",API_KEY)) this inside build.gradle file and 
add gradle.properties inside .gitignore file 




###Dagger Hilt
for dagger hilt we need to create application class first.for that we need to add in our manifest file.
now we need to define module for injection inside dependenceinjection package
#create resource class inside other package
#create repository package 
craete a class name defaultShoppingRepository
now we craete a interface name ShoppingRepository and impliment that inside our defaultshoppingrepository class and inside our testing repository class

##Now create a package in Test package as like 

inside this fakeclass we test our repository and api but we did not call actual network call
fakerepository class created for test viewmodel
