# EasyRide

EasyRide is a car booking site where a user can book a cab from bangalore to any where in India. The cab can be scheduled for a perticular date and time which makes the user finding the cab at right time in right place. User can select the driver with respect to their ratings and the languages they speak.

### Steps to book a cab

A form will be displayed in dashboard. A user can click on search icon and a modal window opens which has the map. User can select the destination and press **Go** this will give the destination address and the distance in Km, then press **Ok**.
Select the date and time of pickup and press **Book**.
A modal pops-up and asks for login with google, sign in with gmail.
After sucessful signup user will see the list of drivers available. User can press book button on drivers card of your choice and proceed to payment.
A form will be seen for payment. Fill the card details and pay the amount.
On sucessful payment User will be routed to history page where a user can all the rides booked and their status, wether its sucessful or pending.
On right top of navbar username and avatar can be seen. From there he can route to different pages and even logout.

###Tech satck used
1) Docker 2) jdk 1.8 3) maven-3.6 4) node-12 5) angular-cli-8.3 6) mysql 7) mongodb

* In frontend anguar 8 and angular material is used.
* In Backend Java and springboot and spring security is used.
* Databases are mysql and mongodb.
* Google oauth is used for login.


####If you are cloning and running it locally then hit this command **docker-compose -f docker-compose-local.yml up -d --build**
and after successful build, open the browser and hit `localhost:8080`


###Backlogs###
* Application is not responsive
* Deployment is not set to perticular domain.
* Payment is a dummy form
  

