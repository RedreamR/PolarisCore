package team.rainfall.fluctlight;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Iterator;

public class Fluctlight implements Disposable {
    private static Fluctlight INSTANCE = null;
    public ArrayList<Task<?>> tasks = new ArrayList<>();
    public ArrayList<Task<?>> runningTasks = new ArrayList<>();
    public ArrayList<TaskThread> taskThreads = new ArrayList<>();
    public static Fluctlight getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Fluctlight();
            INSTANCE.setThreads();
        }
        return INSTANCE;
    }
    public synchronized void finishTask(Task<?> task,Object result){
        task.setResult(result);
        runningTasks.remove(task);
    }
    public synchronized Task<?> getTask(){
        if(!tasks.isEmpty()){
            Task<?> task = tasks.get(0);
            tasks.remove(0);
            runningTasks.add(task);
            return task;
        }
        return null;
    }
    public synchronized void addTask(Task<?> task){
        tasks.add(task);
    }
    public void setThreads(){
        taskThreads.clear();
        taskThreads.add(new TaskThread());
        taskThreads.add(new TaskThread());
        taskThreads.add(new TaskThread());
        taskThreads.add(new TaskThread());
        for (TaskThread taskThread : taskThreads) {
            taskThread.start();
        }
    }

    @Override
    public void dispose() {
        Iterator<TaskThread> it = taskThreads.iterator();
        while (it.hasNext()){
            it.next().finished = true;
            it.remove();
        }
    }
}
