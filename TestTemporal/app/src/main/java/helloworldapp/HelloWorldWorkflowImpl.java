package helloworldapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow{
    ActivityOptions options = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(60)).build();
    private final HelloWorldActivities activities = Workflow.newActivityStub(HelloWorldActivities.class, options);

    @Override
    public String getGreeting(String name){
        return activities.composeGreeting(name);
    }



}
