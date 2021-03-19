import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {
    int numUsers;
    int numRes;

    int maxUsers;
    int maxResources;
    int maxJobs;
    int maxTime;

    public void start(){
        ArrayList<User> users = new ArrayList<>();

        setup(users);
        test(users);
        commence(users);
    }

    private void commence(ArrayList<User> users) {
        ArrayList<Resource> resources = createResourceQueue(users);

        for(Resource resource : resources){
            resource.nextUser();
        }

        boolean allDone = false;
        while(!allDone) {
            cls();
            allDone = true;
            for (Resource resource : resources) {                                     // Displays program progress
                int resNum = resource.getResNum();                                    // Resource number
                String userNum = resource.getCurrentUser() == null ||
                        resource.getCurrentUser().getUserNum() == -1 ?                // Changes the number to "None" when null so that it displays "None" when no users
                        "None" : Integer.toString(resource.getCurrentUser().getUserNum());

                display(userNum, resNum, resource);

                resource.useTime();

                if (resource.getTimeLeft() <= 0) {
                    if(!userNum.equals("None"))
                        resource.getCurrentUser().busy = false;
                    resource.nextUser();
                }

                if(!userNum.equals("None")){
                    allDone = false;
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Display
     */
    public void display(String user, int res, Resource resource){

        System.out.println("Resource " + res +
                "\t Occupied by: " + user +
                "\t" + "Duration: " + resource.getTimeLeft());
        System.out.print("In Waiting:");
        resource.displayUserQueue();
    }

    /**
     * Collects all requests from each user and puts them in the resource for queue.
     * Counts the number of times a resource is needed and represents them in an array
     * @param users
     * @return
     */
    private ArrayList<Resource> createResourceQueue(ArrayList<User> users){
        ArrayList<Resource> res = new ArrayList<>();

        for(int i = 0; i < numRes; i++){                                        // Creates a resource 1 to number of resources (numRes)
            res.add(new Resource(i + 1));
        }

        boolean allEmpty;
        do {
            allEmpty = true;
            for (User user : users) {                                                 // Put each user in Resource Queue
                if (!user.getResourceRequests().isEmpty()) {
                    allEmpty = false;
                    Request userRequest = user.getResourceRequests().remove();
                    res.get(userRequest.resourceNum - 1).addRequest(userRequest);
                }
            }
        } while (!allEmpty);
        return res;
    }

    /**
     * Create randomly generated users
     * Create randomly generated resources
     * Assign resources to a user
     *    Randomly generate how many jobs are needed done
     *    Randomly assign which resource will be used for that job
     *    Randomly generate time needed for that job
     * @param users
     */
    private void setup(List<User> users){
        maxUsers = 30;
        maxResources = 30;
        maxJobs = 5;
        maxTime = 30;

        numUsers = GeneralFunctions.generate(1, maxUsers);                             // Create randomly generated users
        numRes = GeneralFunctions.generate(1, maxResources);                          // Create randomly generated resources

        for(int i = 0; i < numUsers; i++){                                      
            users.add(new User(i + 1));
        }

        for(User user : users){
            int numJobs = GeneralFunctions.generate(1, maxJobs);                             // Randomly generate how many jobs are needed done
            for(int i = 0; i < numJobs; i++){
                int resourceNumber = GeneralFunctions.generate(1, numRes);     // Randomly assign which resource will be used for that job
                int time = GeneralFunctions.generate(1, maxTime);                   // Randomly generate time needed for that job
                user.addRequest(new Request(user, time, resourceNumber));  // Place time and resource required as a request
            }
        }
    }

    /**
     * Tests if users contain data
     * @param users
     */
    private void test(ArrayList<User> users) {
        System.out.println();
        System.out.println("-----TEST-----");
        for(User user: users){
            user.displayData();
        }
        System.out.println(users.size());
    }

    private void cls(){                                                         //Clear Screen
        for(int i = 0; i < 15; i++){
            System.out.println();
        }
    }
}
