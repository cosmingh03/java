package com.lab5;

import java.io.IOException;

import com.lab5.Errors.ImageNotFoundException;
import com.lab5.Image.Image;
import com.lab5.repository.Repository;

public class App {

    public static void main(String args[]) {
        App app = new App();
        app.testRepo();
    }

    private void testRepo() {
        var repo = new Repository();

        repo.add(new Image("Duke", "C:\\Users\\simon\\Desktop\\descărcare.png"));
        repo.add(new Image("Java Logo", "c:/images/java_logo.png"));

        System.out.println(repo);

        try {
            repo.displayImage(repo.findImageByName("Duke"));

            System.out.println("inceracam sa afisam o imagine care nu exista\n");
            repo.displayImage(repo.findImageByName("Java Logo"));

        } catch (ImageNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("finish");
        }
    }

}
