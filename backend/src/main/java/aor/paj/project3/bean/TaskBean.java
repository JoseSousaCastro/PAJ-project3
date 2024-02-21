package aor.paj.project3.bean;

import aor.paj.project3.dto.TaskDto;
import aor.paj.project3.entity.TaskEntity;
import aor.paj.project3.pojo.TaskPojo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Iterator;

@ApplicationScoped
public class TaskBean {

    public boolean newTask(TaskDto task) {

        return validateTask(task);
    }

    /*
    public boolean editTask(TaskDto task, ArrayList<TaskDto> tasks) {
        boolean edited = false;

        for (TaskDto a : tasks) {
            if (a.getId().equals(task.getId())) {
                if (task.getStateId() != 0) {
                    a.setTitle(task.getTitle());
                    a.setDescription(task.getDescription());
                    a.setPriority(task.getPriority());
                    a.setStateId(task.getStateId());
                    a.setStartDate(a.getStartDate());
                    a.setLimitDate(a.getLimitDate());
                    edited = validateTask(a);
                }
            }
        }

        return edited;
    }

     */


    /*
    public boolean removeTask(String id, ArrayList<TaskDto> tasks) {
        boolean removed = false;
        Iterator<TaskDto> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            TaskDto task = iterator.next();
            if (task.getId().equals(id)) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

     */

    public boolean validateTask(TaskDto task) {
        boolean valid = true;
        if ((task.getStartDate() == null
                || task.getLimitDate() == null
                || task.getLimitDate().isBefore(task.getStartDate())
                || task.getTitle().isBlank()
                || task.getDescription().isBlank()
                || task.getPriority() == 0
                || (task.getPriority() != TaskPojo.LOWPRIORITY && task.getPriority() != TaskPojo.MEDIUMPRIORITY && task.getPriority() != TaskPojo.HIGHPRIORITY)
                || (task.getStateId() != TaskPojo.TODO && task.getStateId() != TaskPojo.DOING && task.getStateId() != TaskPojo.DONE)
        )) {
            valid = false;
        }
        return valid;
    }

/*
    public UserPojo getUser(String username, String password){

        return null;
    }

 */

    private TaskDto convertTaskFromEntityToDto(TaskEntity a){
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(a.getTitle());
        taskDto.setDescription(a.getDescription());
        taskDto.setId(a.getId());
        //taskDto.setOwner(a.getOwner().getEmail());
        return taskDto;
    }

    private TaskEntity convertTaskFromDtoToEntity(TaskDto a){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(a.getTitle());
        taskEntity.setDescription(a.getDescription());

        return taskEntity;
    }

    private ArrayList<TaskDto> convertTaskFromEntityListToDtoList(ArrayList<TaskEntity> taskEntityEntities){
        ArrayList<TaskDto> taskDtos = new ArrayList<TaskDto>();
        for(TaskEntity a : taskEntityEntities){
            taskDtos.add(convertTaskFromEntityToDto(a));
        }
        return taskDtos;
    }



}
