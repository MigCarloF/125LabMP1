import java.util.LinkedList;
import java.util.Queue;

public class User {
    private int userNum;
    Queue<Request> requests;    // List of resources the user wants to use
    boolean busy;

    public User(int userNum){
        this.userNum = userNum;
        requests = new LinkedList<>();
    }

    public boolean isBusy(){
        return busy;
    }

    /**
     * Checks for duplicate resource in request and does not add it to request list if so
     * @param request
     */
    public void addRequest(Request request){
        boolean multipleRequest = false;
        for(Request requestCheck : requests){
            if(request.resourceNum == requestCheck.resourceNum){
                multipleRequest = true;
                break;
            }
        }
        if(!multipleRequest){
            requests.add(request);
        }
    }

    public Queue<Request> getResourceRequests(){
        return requests;
    }

    public void displayData(){
        System.out.println("User " + userNum + ":");
        for(Request req : requests){
            System.out.print("Resource " + req.resourceNum);
            System.out.println(" for " + req.time);
        }
        System.out.println();
    }

    public int getUserNum(){
        return userNum;
    }
}
