package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "locality_id")
    @JsonIgnoreProperties("teamsOnLocality")
    private Locality locality;
    @NonNull
    private String logo;
    @NonNull
    private String chatKey;
    @NonNull
    private String sport;
    @OneToMany(mappedBy = "team")
    private List<TeamPosition> positions = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    @JsonManagedReference("team-messages")
    private List<Message> messages = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    private List<TeamRol> teamRoles = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties({"team","opponent"})
    private List<Event> eventList = new ArrayList<>();
    @OneToMany(mappedBy = "opponent")
    @JsonIgnoreProperties("opponent")
    private List<Match> matchesAsopponent = new ArrayList<>();
    @ManyToMany(mappedBy = "teamList")
    @JsonIgnoreProperties("teamList")
    private List<MobileUser> members = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    private List<AssignedPosition> assignedPositions = new ArrayList<>();

    public Team(){}

    public Team(String name, Locality locality, String logo, String chatKey, String sport) {
        this.name = name;
        this.locality = locality;
        this.logo = logo;
        this.chatKey = chatKey;
        this.sport = sport;
    }

    @Transient
    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>(eventList);
        allEvents.addAll(matchesAsopponent);
        return allEvents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void addMember(MobileUser user){
        this.members.add(user);
        user.getTeamList().add(this);
    }

    public List<TeamPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<TeamPosition> positions) {
        this.positions = positions;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<TeamRol> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(List<TeamRol> teamRoles) {
        this.teamRoles = teamRoles;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<MobileUser> getMembers() {
        return members;
    }

    public void setMembers(List<MobileUser> members) {
        this.members = members;
    }

    public List<AssignedPosition> getAssignedPositions() {
        return assignedPositions;
    }

    public void setAssignedPositions(List<AssignedPosition> assignedPositions) {
        this.assignedPositions = assignedPositions;
    }

    public Team addRol(TeamRol newRol) {
        this.teamRoles.add(newRol);
        return this;
    }


}
