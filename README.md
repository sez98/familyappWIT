# Family App 
## Microsoft Hackathon

In Today's world, This Family App is an initiative that helps individuals stay connected to their family memebers in a digital space.

## Introduction
Individuals are addicted to digital space with easy access to smartphones and network connectivity, we are prone to lose touch with our family roots. This app helps us by connecting us to our distance cousins and keeping us grounded.

## Features
## User Entity Description
``` 
1. username: Unique Identification Identity for login
2. name: User's Full Name
3. gender: User's Gender
4. email: Any email id of the user
5. password: Login Password of the User
6. Date of Birth: pattern="dd-MM-yyyy"
6. Date of Anniversay: pattern="dd-MM-yyyy"
7. Children: A list of usernames of the children of the user if on the same platform
8. Mom_Username: User's Mother's username if on the same platform
9. Dad_Username: User's Father's username if on the same platform
10. Married: If the user is married or not (Yes/ No)
11. Spouse_Username: User's Spouse's username if on the same platform
12. BloodGroup: Blood Group of the user
13. Profession: Profession of the user
14. State: Current location where the useris living
15. Country: Current country where the user is residing
16. Disease: A list of diseases that the user is suffering from
17. Hobbies: A List of hobbies of the user
18. Reviews: User has the option of adding his/her reivew for anything, be it a location, food, hospital. These reviews can be seen by his family members.
19. Posts: User has the option of adding many posts, that can be viewed as broadcasted messages sent to his family members. 
```

- ### User Sign Up
    - The add user form lets us add a new user to the database. It will asks all possible fields of an individuals entity and help us create a login to this app. 

- ### Login Page
    - This Page allows the user to enter his username and password and login into the dashboard

- ### Displaying Family Tree
    - My app provides the feature to display my family tree in a visual format so as to make it easy for me to explore my cousins and interact with my uncles and aunts. 
    - It makes it easy to view both sides of ancestory by providing us with a paternal as well as maternal side of relatives.
        - Parental Family Tree
        - Maternal Family Tree

- ### Emergency Blood Required
    - If the user requires emergency blood then he/she can press this button and it will show the list of all users that have the same blood group and are present in the same state and country. 

- ### Risk Calculation of Genetic Diseases
    - This kind of analysis is important in genetic counseling. 
    - For example, if a male and female are both unaffected by a genetic disease trait, but are both are carriers, then for each of their children there is 1 chance in 4 that the child will be affected by the disease.
    - Similar to this example, I am calculating the probabilty of diseases getting affected by keeping a note of the affected relatives in the upper family tree. 

- ### Social Index
    - This helps us calculate the percentage of a user utilizing and connecting with his family members as compared to the other users on the platform. 
    - This will help users analyze how well they are connected to their family as compared to others families. 

- ### Reviews
    - Individuals tend to trust their family members much more than random reviews on the internet. This app helps users check the reviews added by their family members and take an informed decision. 

- ### Career Advise
    - Individuals reach for role models inside their families, from whom they can get guidance. This feature helps users input the field they are interested in and then retrive the number of family members who are in the similar field. 
    - I am using NLP techniques to get this similarity.

- ### Posts
    - Individuals can add posts where they are currently and broadcast this message to their family memebers who then get a notification on their dashboard that this relative is close by, you can connect. 
    - This app helps users add various kinds of posts which can then be send as notification to their family members. 

- ### Events
    - This feature keeps a track of the birthdays and anniversaries of the memebers of a users family and displays on the dashboard who have events today.
    - It displays a personalized message which can then be broadcasted to the user whose birthday or anniversary is present. 

- ### Hobbies
    - This feature allows us to connect with our family memebers by providing a list of similar interests. 
    - When a user adds his hobbies, then on the dashboard he can view his family memebrs who have the similar hobby. This similarity is done with NLP techniques. 

# Technologies 
- Backend- Springboot 
- Frontend- Angular 11
- Java Version- 11
- Maven

# Executing the Application
## Backend
- Run the backend 
``` 
mvn clean install
mvn spring-boot:run
```
- Run the Frontend
```
npm install
npm start
```
- Application 
```
Frontend- http://localhost:4200/
Backend- http://localhost:8082/familyapp/{APIendpoint}
```

# Video Uploads
- [Functionaliy Description Video](https://youtu.be/E8aGbYWaMDs)
- [Pitch Video](https://youtu.be/Q7_Dd4qrogc)

