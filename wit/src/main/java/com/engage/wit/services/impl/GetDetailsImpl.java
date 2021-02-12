package com.engage.wit.services.impl;

import com.engage.wit.entity.*;
import com.engage.wit.exceptions.UserNotFound;
import com.engage.wit.repository.LoginCountRecordRepository;
import com.engage.wit.repository.UserRecordRepository;
import com.engage.wit.services.GetDetails;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetDetailsImpl implements GetDetails {


    @Autowired
    private UserRecordRepository userRecordRepository;

    @Autowired
    private LoginCountRecordRepository loginCountRecordRepository;

    @Override
    public List<User> getAllUsers() {
        return userRecordRepository.findAll();
    }

    public ArrayList<PairObject> maternal(Set<String> visitedSet, Queue<PairObject> q, ArrayList<PairObject> outputObject ){
        while(!q.isEmpty()){
            int size = q.size();

            PairObject front=q.peek();
            q.poll();

            outputObject.add(front);

            if(front.getFlag() && front.getU1().getDad_username()!=null && !visitedSet.contains(front.getU1().getDad_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getDad_username());
                if(u.isPresent()) {
                    User itr = u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK() + 1);
                    if (front.getRoot() == true)
                        t.setFlag(false);
                    else
                        t.setFlag(true);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }
            }
            if(front.getFlag() && front.getU1().getMom_username()!=null && !visitedSet.contains(front.getU1().getMom_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getMom_username());
                if(u.isPresent()) {
                    User itr = u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK() + 1);
                    t.setFlag(true);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }
            }

            if(front.getFlag() && front.getU1().getSpouse_username()!=null && !visitedSet.contains(front.getU1().getSpouse_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getSpouse_username());
                if(u.isPresent()) {
                    User itr=u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK());
                    t.setFlag(false);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }

            }
            if(front.getU1().getChildren()!=null) {
                for (int i = 0; i < front.getU1().getChildren().size(); i++) {
                    if (front.getFlag() && !visitedSet.contains(front.getU1().getChildren().get(i))) {
                        Optional<User> u =userRecordRepository.findById(front.getU1().getChildren().get(i));
                        if(u.isPresent()) {
                            User itr = u.get();
                            PairObject t = new PairObject();
                            t.setU1(itr);
                            t.setRoot(false);
                            t.setK(front.getK() - 1);
                            t.setFlag(true);
                            q.add(t);

                            visitedSet.add(itr.getUsername());
                        }
                    }
                }
            }
        }
//            for(int i=0;i<outputObject.size();i++){
//                for(int j=0;j<outputObject.get(i).size();j++){
//                    if(i!=0)
//                        System.out.println(outputObject.get(i).get(j).getU1().getUsername() + " " + outputObject.get(i).get(j).getU2().getUsername() + " " + outputObject.get(i).get(j).getK());
//                    else
//                        System.out.println(outputObject.get(i).get(j).getU1().getUsername() + " " + outputObject.get(i).get(j).getK());
//
//                }
//                System.out.println();
//            }
        return outputObject;
    }


    public ArrayList<PairObject> paternal(Set<String> visitedSet, Queue<PairObject> q, ArrayList<PairObject> outputObject ){
        while(!q.isEmpty()){
            int size = q.size();

            PairObject front=q.peek();
            q.poll();

            outputObject.add(front);

            if(front.getFlag() && front.getU1().getDad_username()!=null && !visitedSet.contains(front.getU1().getDad_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getDad_username());
                if(u.isPresent()) {
                    User itr = u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK() + 1);
                    t.setFlag(true);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }
            }
            if(front.getFlag() && front.getU1().getMom_username()!=null && !visitedSet.contains(front.getU1().getMom_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getMom_username());
                if(u.isPresent()) {
                    User itr = u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK() + 1);
                    if (front.getRoot() == true)
                        t.setFlag(false);
                    else
                        t.setFlag(true);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }
            }

            if(front.getFlag() && front.getU1().getSpouse_username()!=null && !visitedSet.contains(front.getU1().getSpouse_username())) {
                Optional<User> u =userRecordRepository.findById(front.getU1().getSpouse_username());
                if(u.isPresent()) {
                    User itr=u.get();
                    PairObject t = new PairObject();
                    t.setU1(itr);
                    t.setRoot(false);
                    t.setK(front.getK());
                    t.setFlag(false);
                    q.add(t);

                    visitedSet.add(itr.getUsername());
                }

            }
            if(front.getU1().getChildren()!=null) {
                for (int i = 0; i < front.getU1().getChildren().size(); i++) {
                    if (front.getFlag() && !visitedSet.contains(front.getU1().getChildren().get(i))) {
                        Optional<User> u =userRecordRepository.findById(front.getU1().getChildren().get(i));
                        if(u.isPresent()) {
                            User itr = u.get();
                            PairObject t = new PairObject();
                            t.setU1(itr);
                            t.setRoot(false);
                            t.setK(front.getK() - 1);
                            t.setFlag(true);
                            q.add(t);

                            visitedSet.add(itr.getUsername());
                        }
                    }
                }
            }
        }
//            for(int i=0;i<outputObject.size();i++){
//                for(int j=0;j<outputObject.get(i).size();j++){
//                    if(i!=0)
//                        System.out.println(outputObject.get(i).get(j).getU1().getUsername() + " " + outputObject.get(i).get(j).getU2().getUsername() + " " + outputObject.get(i).get(j).getK());
//                    else
//                        System.out.println(outputObject.get(i).get(j).getU1().getUsername() + " " + outputObject.get(i).get(j).getK());
//
//                }
//                System.out.println();
//            }
        return outputObject;
    }

    @Override
    public ArrayList<PairObject> getFamilyTree(String username, Integer i) throws UserNotFound {
        Optional<User> user= userRecordRepository.findById(username);
        if(user.isPresent()){
            Queue<PairObject> q= new LinkedList<>();

            PairObject tempObject = new PairObject();
            tempObject.setU1(user.get());
            tempObject.setRoot(true);
            tempObject.setK(0);
            tempObject.setFlag(true);

            q.add(tempObject);

            Set<String> visitedSet=new HashSet<>();
            visitedSet.add(user.get().getUsername());


            ArrayList<PairObject> outputObject = new ArrayList<>();

            if(i==0){
                //Paternal Side
                outputObject = paternal(visitedSet,q,outputObject);
            }
            else{
                //Maternal Side
                outputObject = maternal(visitedSet,q,outputObject);
            }
            return outputObject;
        }
        else {
            throw new UserNotFound("User not Found");
        }
    }

    @Override
    public List<User> getBloodGroup(String username) throws UserNotFound {
        Optional<User> originalUser = userRecordRepository.findById(username);

        List<User> outputUsers = new ArrayList<>();

        if(originalUser.isPresent()) {
            ArrayList<User> family= getFamily(username);
            for (int i = 1; i < family.size(); i++) {
                User t = family.get(i);
                if (originalUser.get().getBloodGroup().compareTo(t.getBloodGroup()) ==0
                        && (originalUser.get().getCountry()==null || originalUser.get().getCountry().compareTo(t.getCountry()) ==0)
                        && (originalUser.get().getState()==null || originalUser.get().getState().compareTo(t.getState()) ==0)) {
                    outputUsers.add(t);
                }
            }
        }
        return outputUsers;
    }

    @Override
    public ArrayList<User> getFamily(String username) throws UserNotFound {
        ArrayList<User> output = new ArrayList<>();
        Optional<User> user = userRecordRepository.findById(username);
        if (user.isPresent()) {
            Queue<FamilyCheckPair> q = new LinkedList<>();
            q.add(new FamilyCheckPair(user.get(), true));

            Set<String> visitedSet = new HashSet<>();
            visitedSet.add(user.get().getUsername());

            while (!q.isEmpty()) {
                FamilyCheckPair front = q.peek();
                q.poll();
                output.add(front.getFirst());

                if (front.getSecond() && front.getFirst().getDad_username() != null && !visitedSet.contains(front.getFirst().getDad_username())) {
                    Optional<User> u =userRecordRepository.findById(front.getFirst().getDad_username());
                    if(u.isPresent()) {
                        User itr = u.get();
                        q.add(new FamilyCheckPair(itr, true));
                        visitedSet.add(itr.getUsername());
                    }
                }
                if (front.getSecond() && front.getFirst().getMom_username() != null && !visitedSet.contains(front.getFirst().getMom_username())) {
                    Optional<User> u =userRecordRepository.findById(front.getFirst().getMom_username());
                    if(u.isPresent()) {
                        User itr = u.get();
                        q.add(new FamilyCheckPair(itr, true));
                        visitedSet.add(itr.getUsername());
                    }
                }

                if (front.getSecond() && front.getFirst().getSpouse_username() != null && !visitedSet.contains(front.getFirst().getSpouse_username())) {
                    Optional<User> u =userRecordRepository.findById(front.getFirst().getSpouse_username());
                    if(u.isPresent()) {
                        User itr = u.get();
                        q.add(new FamilyCheckPair(itr, false));
                        visitedSet.add(itr.getUsername());
                    }
                }
                if(front.getFirst().getChildren()!=null) {
                    for (int i = 0; i < front.getFirst().getChildren().size(); i++) {
                        if (front.getSecond() && !visitedSet.contains(front.getFirst().getChildren().get(i))) {
                            Optional<User> u =userRecordRepository.findById(front.getFirst().getChildren().get(i));
                            if(u.isPresent()) {
                                User itr = u.get();
                                q.add(new FamilyCheckPair(itr, true));
                                visitedSet.add(itr.getUsername());
                            }
                        }
                    }
                }
            }

//            for(int i=0;i<output.size();i++){
//                System.out.println(output.get(i).getUsername());
//            }
            return output;
        }
        else {
            throw new UserNotFound("User not Found");
        }
    }

    @Override
    public ArrayList<ArrayList<User>> getEvents(String username) throws UserNotFound {

        Date d= new java.util.Date();
        Optional<User> u = userRecordRepository.findById(username);
        ArrayList<User> birthdayUsers = new ArrayList<>();
        ArrayList<User> anniversaryUsers = new ArrayList<>();
        if(u.isPresent()){
            ArrayList<User> users = getFamily(username);
            for(int i=0;i<users.size();i++){
                if(users.get(i).getDob()!=null && users.get(i).getDob().getDate()==d.getDate() && users.get(i).getDob().getMonth()==d.getMonth()){
                    birthdayUsers.add(users.get(i));
                    System.out.println("found birthday- " + users.get(i).getUsername());
                }
                if(users.get(i).getDoa()!=null && users.get(i).getDoa().getDate()==d.getDate() && users.get(i).getDoa().getMonth()==d.getMonth()){
                    anniversaryUsers.add(users.get(i));
                    System.out.println("found anniverasary- " + users.get(i).getUsername());
                }
            }
        }
        ArrayList<ArrayList<User>> returnedObject = new ArrayList<>();
        returnedObject.add(birthdayUsers);
        returnedObject.add(anniversaryUsers);
        return returnedObject;

    }


    @Override
    public Map<String,ArrayList<User>> getHobbies(String username) throws UserNotFound  {
        Optional<User> users = userRecordRepository.findById(username);
        List<User> family = getFamily(username);
        Map<String,ArrayList<User>> result = new HashMap<>();

        if(users.get().getHobbies()!=null) {
            for (int i = 0; i < users.get().getHobbies().size(); i++) {
                String hobby = users.get().getHobbies().get(i);
                System.out.println(hobby);
                ArrayList<User> similarInterest = new ArrayList<>();
                for (int j = 1; j < family.size(); j++) {
                    if (family.get(j).getHobbies() != null && family.get(j).getHobbies().contains(hobby)) {
                        similarInterest.add(family.get(j));
                    }
                }
                result.put(hobby, similarInterest);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Reviews> getSuggestion(String username) throws UserNotFound {
        Optional<User> user = userRecordRepository.findById(username);
        if(user.isPresent()){
            return user.get().getReviews();
        }
        return null;
    }

    @Override
    public ArrayList<User> getCareerAdvise(String username, String topic) throws UserNotFound {
        Optional<User> user = userRecordRepository.findById(username);
        ArrayList<User> careerAdvise = new ArrayList<>();
        if(user.isPresent()){
            ArrayList<User> family = getFamily(username);
            for(int i=0;i<family.size();i++){
                if(family.get(i).getProfession()!=null && family.get(i).getProfession().compareTo(topic) == 0)
                    careerAdvise.add(family.get(i));
            }
        }
        return careerAdvise;
    }

    @Override
    public ArrayList<Post> getPosts(String username) {
        Optional<User> user=userRecordRepository.findById(username);
        if(user.isPresent())
            return user.get().getPosts();
        return null;
    }

    @Override
    public Map<String, Integer> getDiseases(String username) throws UserNotFound {
        Optional<User> user=userRecordRepository.findById(username);
        Map<String, Integer>  output=new HashMap<>();
        if(user.isPresent()){
            ArrayList<User> family = getFamily(username);
            for(int i=0;i<family.size();i++){
                if(family.get(i).getDisease()!=null){
                    for(int j=0;j<family.get(i).getDisease().size();j++){
                        if(output.containsKey(family.get(i).getDisease().get(j)))
                            output.put(family.get(i).getDisease().get(j), (output.get(family.get(i).getDisease().get(j)) + 1)/ family.size());
                        else
                            output.put(family.get(i).getDisease().get(j), 1);
                    }
                }
            }
        }
        return output;
    }

    @Override
    public User getLogin(String username, String password) {
        Optional<User> user = userRecordRepository.findById(username);
        if(user.isPresent() && user.get().getPassword().compareTo(password) == 0){
            Optional<LoginCount> loginCount = loginCountRecordRepository.findById(username);
            if(loginCount.isPresent()){
                loginCount.get().setCount(loginCount.get().getCount() + 1);
                loginCountRecordRepository.save(loginCount.get());
            }
            else{
                LoginCount c= new LoginCount();
                c.setCount(1);
                c.setUsername(username);
                loginCountRecordRepository.save(c);
            }
            return user.get();
        }
        return null;
    }

    @Override
    public Float getSocialIndex(String username) {
        List<LoginCount> u= loginCountRecordRepository.findAll();
        Integer num=0;
        Integer counter=0;
        for(int i=0;i<u.size();i++){
            if(u.get(i).getUsername().compareTo(username)==0){
                num=u.get(i).getCount();
            }
            counter+=u.get(i).getCount();
        }
        return ((float)num/counter * 100);
    }


}


