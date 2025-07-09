package team.rainfall.fluctlight;

public class TaskThread extends Thread{
    public Task<?> task = null;
    public boolean finished = false;
    @Override
    public void run(){
        while (!finished){
            if(Fluctlight.getInstance().tasks.isEmpty()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }else {
                task = Fluctlight.getInstance().getTask();
                if(task == null){
                    continue;
                }
                Object result = task.run();
                Fluctlight.getInstance().finishTask(task,result);
            }
        }
    }
}
