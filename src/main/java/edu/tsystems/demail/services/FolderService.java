package edu.tsystems.demail.services;

import edu.tsystems.demail.DAO.FolderDAO;
import edu.tsystems.demail.DAO.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 11:41
 */
public class FolderService {
    private FolderDAO folderDAO;
    private UserDAO userDAO;
    //private MailService mailService;

    public List<FolderDTO> getFoldersByUserId(Long userId) {
        List<FolderEntity> folderEntities = folderDAO.getFoldersByUserId(userId);

        List<FolderDTO> folders = new ArrayList<FolderDTO>();

        for(FolderEntity folder: folderEntities) {
            FolderDTO fDTO = new FolderDTO();
            fDTO.setId(folder.getId());
            fDTO.setName(folder.getName());
            fDTO.setSystem(folder.isSystem());

            folders.add(fDTO);
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

        MailBoxEntity mailBoxEntity = ((UserEntity)userDAO.get(UserEntity.class, folderDTO.getUserId())).getMailBox();

        folderEntity.setMailBox(mailBoxEntity);
        folderEntity.setName(folderDTO.getName());
        folderEntity.setSystem(folderDTO.isSystem());

        folderDAO.create(folderEntity);
    }

    public void removeFolder(Long folderId) {
        FolderEntity folderEntity = (FolderEntity) folderDAO.get(FolderEntity.class, folderId);
        if(!folderEntity.isSystem()) {
            mailService.removeEmailFromFolder(folderId);
            folderDAO.delete(folderEntity);
        }
    }
}
