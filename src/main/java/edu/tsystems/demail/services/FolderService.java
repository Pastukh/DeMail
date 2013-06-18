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
    private FolderDAO folderDAO = new FolderDAO();
    private UserDAO userDAO;
    //private MailService mailService;

    public List<FolderDTO> getFoldersByUserId(int userId) {
        List<FolderEntity> folderEntities = folderDAO.getFoldersByUserId(userId);

        List<FolderDTO> folders = new ArrayList<FolderDTO>();

        for(FolderEntity folder: folderEntities) {
            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setId(folder.getId());
            folderDTO.setName(folder.getName());
            folderDTO.setSystem(folder.isSystem());

            folders.add(folderDTO);
        }

        return folders;
    }


    public void createDefaultFolderForMailBoxId(MailBoxDTO mailBoxDTO) {
        // prepare new folders
        MailBoxEntity mailBoxEntity = new MailBoxEntity();
        mailBoxEntity.setMailBox(mailBoxDTO.getMailbox());
        mailBoxEntity.setId(mailBoxDTO.getId());
        mailBoxEntity.setUserId(mailBoxDTO.getUserId());
        mailBoxEntity.setDateCreate(mailBoxDTO.getDateCreated());
        System.out.println(mailBoxEntity);
        folderDAO.createDefaultFoldersForMailBox(mailBoxEntity);
    }


    public void createFolder(FolderDTO folderDTO) {
//        FolderEntity folderEntity = new FolderEntity();

//        MailBoxEntity mailBoxEntity = ((UserEntity)userDAO.get(UserEntity.class, folderDTO.getUserId())).getMailBox();
//        folderEntity.setMailBox(mailBoxEntity);
//        folderEntity.setName(folderDTO.getName());
//        folderEntity.setSystem(folderDTO.isSystem());

//        folderDAO.create(folderEntity);
    }
    public void removeFolder(int folderId) {
//        FolderEntity folderEntity = (FolderEntity) folderDAO.get(FolderEntity.class, folderId);
//        if(!folderEntity.isSystem()) {
//            mailService.removeEmailFromFolder(folderId);
//            folderDAO.delete(folderEntity);
//        }
    }


}
