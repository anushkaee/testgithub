package com.unite.github;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.DataService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.Date;

public class TagService {

    private DataService service;
    private RepositoryId repo;

    public static void main(String [] args) {

        Repository repository = null;

        //Basic authentication
        GitHubClient client = new GitHubClient();
        //client.setCredentials("anushkaee", "p@55w0rd");
        client.setOAuth2Token("d8a3c505761f926ad9ffdd20c0dbe4828da13e2a");

        RepositoryService repositoryServiceService = new RepositoryService(client);
        DataService dataService = new DataService(client);

        try {
            for (Repository repo : repositoryServiceService.getRepositories("anushkaee")) {

                if("testgithub".equalsIgnoreCase(repo.getName())){
                    repository = repo;
                }
            }

            CommitUser commitUser = new CommitUser();
            commitUser.setName("anushkaee");
            commitUser.setDate(new Date());
            commitUser.setEmail("anushka.mcg@gmail.com");

            Tag tag = new Tag();
            tag.setObject((TypedResource) new TypedResource().setType("commit")
                    .setUrl("https://api.github.com/repos/anushkaee/testgithub/git/commits/8008513ca89e5cbaee84d9bf7fc9059565e22b8c")
                    .setSha("8008513ca89e5cbaee84d9bf7fc9059565e22b8c"));
            tag.setTag("v1.0");
            tag.setSha("8008513ca89e5cbaee84d9bf7fc9059565e22b8c");
            tag.setTagger(commitUser);
            tag.setMessage("new version created.");

            tag= dataService.createTag(repository, tag);
            System.out.println("Created Tag:"+tag.toString());


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
