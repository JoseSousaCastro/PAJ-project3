package aor.paj.project3.bean;

import aor.paj.project3.dao.TaskDao;
import aor.paj.project3.dao.UserDao;
import aor.paj.project3.dto.TaskDto;
import aor.paj.project3.entity.TaskEntity;
import aor.paj.project3.entity.UserEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;


@Stateless
public class TaskBean implements Serializable {
@EJB
TaskDao taskDao;

@EJB
UserDao userDao;

public TaskBean() {
}

/*
	public boolean addTask(String token, TaskDto t) {
		UserEntity userEntity = userDao.findUserByToken(token);
		if(userEntity != null){
			TaskEntity taskEntity = convertTaskFromDtoToEntity(t);
			taskEntity.setOwner(userEntity);
			taskDao.persist(taskEntity);
			return true;
		}
		return false;
	}

*/

	public TaskDto getTask(int id) {
		TaskEntity taskEntity = taskDao.findTaskById(id);
		return convertTaskFromEntityToDto(taskEntity);
	}

/*	public ArrayList<TaskDto> getTasks(String token) {
		UserEntity userEntity = userDao.findUserByToken(token);
		if(userEntity != null){
			ArrayList<TaskEntity> tasks = taskDao.findTasksByUser(userEntity);
			if (tasks!= null)
				return convertTaskFromEntityListToDtoList(tasks);
		}
		return null;
	}
	*/

	public boolean removeTask(int id) {
		TaskEntity a = taskDao.findTaskById(id);
		if(a!=null){
			taskDao.remove(a);
			return true;
		}
		return false;
	}

	public boolean updateTask(int id, TaskDto taskDto) {
		TaskEntity t = taskDao.findTaskById(id);
		if(t!= null){
			t.setTitle(taskDto.getTitle());
			t.setDescription(taskDto.getDescription());
			return true;
		}
		return false;
	}

	
    private TaskDto convertTaskFromEntityToDto(TaskEntity a){
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(a.getTitle());
        taskDto.setDescription(a.getDescription());
        taskDto.setId(a.getId());
        //taskDto.setOwner(a.getOwner().getEmail());
        return taskDto;
    }

    private TaskEntity convertTaskFromDtoToEntity(TaskDto t){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(t.getTitle());
        taskEntity.setDescription(t.getDescription());

        return taskEntity;
    }

    private ArrayList<TaskDto> convertTaskFromEntityListToDtoList(ArrayList<TaskEntity> taskEntityEntities){
        ArrayList<TaskDto> taskDtos = new ArrayList<TaskDto>();
        for(TaskEntity t : taskEntityEntities){
            taskDtos.add(convertTaskFromEntityToDto(t));
        }
        return taskDtos;
    }



}
