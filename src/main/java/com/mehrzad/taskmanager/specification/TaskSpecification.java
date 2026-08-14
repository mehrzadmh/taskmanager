package com.mehrzad.taskmanager.specification;

import com.mehrzad.taskmanager.entity.Task;
import com.mehrzad.taskmanager.enums.TaskPriority;
import com.mehrzad.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> hasTitle(String title){

        return (root, query, cb) ->

                title == null ?

                        null :

                        cb.like(root.get("title"), "%" + title + "%");

    }

    public static Specification<Task> hasStatus(TaskStatus status){

        return (root, query, cb) ->

                status == null ?

                        null :

                        cb.equal(root.get("status"), status);

    }


    public static Specification<Task> hasPriority(TaskPriority priority){

        return (root, query, cb) ->

                priority == null ?

                        null :

                        cb.equal(root.get("priority"), priority);

    }

}