package ru.nsu.ramazanova

import lombok.Getter
import lombok.ToString
import ru.nsu.ramazanova.model.ControlPoint
import ru.nsu.ramazanova.model.Group
import ru.nsu.ramazanova.model.Student
import ru.nsu.ramazanova.model.Task

import javax.xml.crypto.Data

import static groovy.lang.Closure.DELEGATE_ONLY

@Getter
@ToString
class ModelConfiguration {
     static public List<Task> tasksList = new ArrayList<>();
     static public List<Student> groupList = new ArrayList<>();
     static public List<ControlPoint> controlPointsList = new ArrayList<>();

    static public TaskSpec taskSpec = new TaskSpec()
    static class TaskSpec {
        static void task(int id, String name, double points){
            tasksList.add(new Task(id, name, points))
        }
    }
    static void tasks (@DelegatesTo(value = TaskSpec, strategy = DELEGATE_ONLY) Closure closure) {
        closure.delegate = taskSpec
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static public GroupSpec groupSpec = new GroupSpec()
    static class GroupSpec {
        static void student(String nickname, String fullName, String repositoryURL){
            groupList.add(new Student(nickname, fullName, repositoryURL))
        }
    }
    static void group (@DelegatesTo(value = GroupSpec, strategy = DELEGATE_ONLY) Closure closure) {
        closure.delegate = groupSpec
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static public ControlPointSpec controlPointSpec = new ControlPointSpec();
    static class ControlPointSpec {
        static void controlPoint(String name, String date){
            controlPointsList.add(new ControlPoint(name, date))
        }
    }
    static void controlPoints (@DelegatesTo(value = ControlPointSpec, strategy = DELEGATE_ONLY) Closure closure) {
        closure.delegate = controlPointSpec
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}
