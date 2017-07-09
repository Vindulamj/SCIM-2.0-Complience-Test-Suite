package info.wso2.scim2.compliance.tests;


import info.wso2.scim2.compliance.entities.TestResult;

public interface Test {

    public TestResult performTest() throws Exception;
}
