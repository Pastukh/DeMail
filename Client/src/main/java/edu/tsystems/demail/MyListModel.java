package edu.tsystems.demail;

import edu.tsystems.demail.DTO.FolderDTO;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Author: Ivan Pastukh
 * Date: 23.06.13
 * Time: 0:29
 */
public class MyListModel extends DefaultListModel {
    ArrayList<FolderDTO> folders;

    public MyListModel(ArrayList<FolderDTO> folders) {
        super();
        this.folders = folders;
    }

    public int getSize() {
        return folders.size();
    }

    @Override
    public Object getElementAt(int index) {
        return folders.get(index).getName();
    }
}
