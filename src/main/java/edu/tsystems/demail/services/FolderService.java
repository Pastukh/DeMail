package edu.tsystems.demail.services;

import edu.tsystems.demail.DAO.FolderDAO;
import edu.tsystems.demail.DAO.UserDAO;
import edu.tsystems.demail.DTO.FolderDTO;
import edu.tsystems.demail.DTO.MailBoxDTO;
import edu.tsystems.demail.model.FolderEntity;
import edu.tsystems.demail.model.MailBoxEntity;
import edu.tsystems.demail.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 11:41
 */
public class FolderService {
    private FolderDAO folderDAO;
    private UserDAO userDAO;
    //private MailService mailService;

    public List<FolderDTO> getFoldersByUserId(int userId) {
        List<FolderEntity> folderEntities = folderDAO.getFoldersByUserId(userId);

        List<FolderDTO> folders = new ArrayList<FolderDTO>();

        for(FolderEntity folder: folderEntities) {
            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setId(folder.getId());
            folderDTO.setName(folder.getName());

            folders.add(folderDTO);
        }

        return folders;
    }


    public void createDefaultFolderForMailBoxId(MailBoxDTO mailBox) {
        // prepare new folders
        MailBoxEntity mailBoxEntity = new MailBoxEntity();
        mailBoxEntity.setMailBox(mailBox.getMailbox());
        mailBoxEntity.setId(mailBox.getId());
        mailBoxEntity.setUserId(mailBox.getUserId());
        mailBoxEntity.setDateCreate(mailBox.getDateCreated());
        folderDAO.createDefaultFoldersForMailBox(mailBoxEntity);
    }


    public void createFolder(FolderDTO folderDTO) {
        FolderEntity folderEntity = new FolderEntity();

//        MailBoxEntity mailBoxEntity = ((UserEntity)userDAO.get(UserEntity.class, folderDTO.getUserId())).getMailBox();
//        folderEntity.setMailBox(mailBoxEntity);
//        folderEntity.setName(folderDTO.getName());

        folderDAO.create(folderEntity);
    }


}
