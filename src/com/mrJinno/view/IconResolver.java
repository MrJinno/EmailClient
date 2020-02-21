package com.mrJinno.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconResolver {
    public Node getIconForFolder(String folderName){
        folderName=folderName.toLowerCase();
        String iconSource;
        try {
            if (folderName.contains("@")) iconSource="icons/email.png";
            else if (folderName.contains("inbox")) iconSource="icons/inbox.png";
            else if (folderName.contains("sent")) iconSource="icons/sent2.png";
            else if (folderName.contains("spam"))  iconSource="icons/spam.png";
            else iconSource="icons/folder.png";

        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
        ImageView imageView= new ImageView(new Image(getClass().getResourceAsStream(iconSource)));
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        return imageView;
    }

}
