package mk.ukim.finki.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    @PostConstruct
    public void init(){
        workshops = new ArrayList<>();
        registrations = new ArrayList<>();
        workshops.add(new Workshop("Java Basics", "Programming", 1500.0, 20));
        workshops.add(new Workshop("Spring Boot Intro", "Programming", 2000.0, 15));
        workshops.add(new Workshop("Web Design with Figma", "Design", 1200.0, 25));
        workshops.add(new Workshop("Docker for Beginners", "DevOps", 1800.0, 10));
        workshops.add(new Workshop("Data Analysis with Python", "Data Science", 2200.0, 18));
        workshops.add(new Workshop("Machine Learning 101", "Data Science", 2500.0, 12));
        workshops.add(new Workshop("Git and GitHub", "Tools", 800.0, 30));
        workshops.add(new Workshop("Cybersecurity Essentials", "Security", 2300.0, 8));
        workshops.add(new Workshop("UX Research Methods", "Design", 1400.0, 5));
        workshops.add(new Workshop("Advanced SQL", "Databases", 1700.0, 0));
    }
}
