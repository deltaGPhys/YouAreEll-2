package models;

/* 
 * POJO for an Id object
 */
public class Id {

    private String name;
    private String gitHubId;
    
    public Id (String name, String gitHubId) {
        this.name = name;
        this.gitHubId = gitHubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitHubId() {
        return gitHubId;
    }

    public void setGitHubId(String gitHubId) {
        this.gitHubId = gitHubId;
    }
}