public class Request {
    private User user;
    int time;
    int resourceNum;

    public Request(User user, int time, int resourceNum){
        this.user = user;
        this.time = time;
        this.resourceNum = resourceNum;
    }

    public User getUser(){
        return user;
    }

    public  int getTime() { return time; }
}
