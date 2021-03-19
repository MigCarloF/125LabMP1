import java.util.LinkedList;
import java.util.Queue;

public class Resource {
    private int resNum;
    private Queue<Request> requests;
    private User currentUser;
    private int timeLeft;

    public Resource(int resNum){
        this.resNum = resNum;
        requests = new LinkedList<>();
        timeLeft = 0;
    }

    public void nextUser(){
        if(timeLeft == 0){
            currentUser = null;
        }

        if(hasRequests()) {
            if(!requests.peek().getUser().isBusy()) {
                Request request = requests.remove();
                currentUser = request.getUser();
                timeLeft = request.getTime();
                request.getUser().busy = true;
            }
        } else {
            currentUser = null;
            timeLeft = 0;
        }
    }

    public void useTime(){
        if(timeLeft > 0){
            timeLeft--;
        }
    }

    public boolean hasRequests(){
        return !requests.isEmpty();
    }

    public void addRequest(Request request){
       requests.add(request);
    }

    public int getResNum(){
        return resNum;
    }

    public void displayUserQueue(){
        for(Request request : requests){
            System.out.print("-" + request.getUser().getUserNum() + " ");
        }
        System.out.println();
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public int getTimeLeft(){
        return timeLeft;
    }
}
