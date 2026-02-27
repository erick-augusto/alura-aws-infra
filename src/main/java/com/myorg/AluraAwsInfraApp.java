package com.myorg;

import software.amazon.awscdk.App;

public class AluraAwsInfraApp {
    public static void main(final String[] args) {
        App app = new App();

        AluraVPCStack vpcStack = new AluraVPCStack(app, "Vpc");
        AluraClusterStack clusterStack = new AluraClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        AluraRdsStack rdsStack = new AluraRdsStack(app, "Rds", vpcStack.getVpc());
        rdsStack.addDependency(vpcStack);

        AluraServiceStack aluraServiceStack = new AluraServiceStack(app, "Service", clusterStack.getCluster());
        aluraServiceStack.addDependency(clusterStack);
        aluraServiceStack.addDependency(rdsStack);
        app.synth();
    }
}