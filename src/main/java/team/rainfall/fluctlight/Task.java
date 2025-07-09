package team.rainfall.fluctlight;

public abstract class Task<T> {
    T result = null;
    volatile boolean isFinished = false;
    public void setResult(Object result){
        isFinished = true;
        this.result = (T) result;
    }
    public T blockOn(){
        while (!isFinished){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
        return result;
    }
    public abstract T run();
}
