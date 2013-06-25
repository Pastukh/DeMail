package edu.tsystems.demail.services;
import edu.tsystems.demail.DTO.MailBoxDTO;
import edu.tsystems.demail.DTO.MailDTO;
import edu.tsystems.demail.model.FolderEntity;
import edu.tsystems.demail.model.MailBoxEntity;
import edu.tsystems.demail.model.MailEntity;
import edu.tsystems.demail.DAO.FolderDAO;
import edu.tsystems.demail.DAO.MailBoxDAO;
import edu.tsystems.demail.DAO.MailDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * Author: Ivan Pastukh
 * Date: 20.06.13
 * Time: 16:52
 */
public class MailService {
    private MailDAO mailDAO = new MailDAO();
    private FolderDAO folderDAO = new FolderDAO();
    private MailBoxDAO mailBoxDAO = new MailBoxDAO();
    /**
     * Return list of mails in selected folder. If folder is null return from Inbox folder
     * @param folderId current folder
     * @param userId user id
     * @return list of mails
     */
    public List<MailDTO> getMailByFolder(int folderId, int userId) {
//        if(folderId == null) {
//            folderId = folderDAO.getFolderIdByNameAndUserId("Inbox", userId);
//        }
        System.out.println("getMailByFolderId(folderId) " + folderId);

        List<MailEntity> mailEntities = mailDAO.getMailByFolderId(folderId);
        List<MailDTO> mails = new ArrayList<MailDTO>();
        for(MailEntity mail : mailEntities) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setBody(mail.getBody());
            mailDTO.setDate(mail.getDate());
            mailDTO.setFrom(mail.getMailFrom());
            mailDTO.setTo(mail.getMailTo());
            mailDTO.setSubject(mail.getSubject());
            mailDTO.setRead(mail.getRead());
            mailDTO.setId(mail.getId());
            mails.add(mailDTO);
        }

        return mails;
    }

    /**
     * Create welcome message
     * @param mailBoxDTO mail box DTO
     */
    public void createFirstMail(MailBoxDTO mailBoxDTO) {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        mailEntity.setSubject("Welcome to your mail!");
        mailEntity.setBody("Welcome to your mail");
        mailEntity.setMailFrom("admin@demail.de");
        mailEntity.setMailTo(mailBoxDTO.getMailbox());
        mailEntity.setFolderId(folderDAO.getFolderIdByNameAndUserId("Inbox",mailBoxDTO.getUserId()));
        mailEntity.setRead(false);
        mailEntity.setMailBoxOwnerId(mailBoxDTO.getUserId());

        mailDAO.create(mailEntity);
    }

//    public void markMailAsRead(MailDTO MailDTO) {
//        System.out.println(MailDTO);
//        MailEntity MailEntity = (MailEntity) mailDAO.get(MailEntity.class, MailDTO.getId());
//        MailEntity.setRead(true);
//        mailDAO.update(MailEntity);
//    }

    public void sendMail(MailDTO mailDTO){
        MailBoxEntity toMailBox = mailBoxDAO.getMailBoxByName(mailDTO.getTo());

        MailBoxEntity fromMailBox = (MailBoxEntity) mailBoxDAO.get(mailDTO.getFromId());
        if(fromMailBox == null)
            System.out.println("ERROR! fromMailBox don't found!");

        assert fromMailBox != null;
        int folderOutboxId = folderDAO.getFolderIdByNameAndUserId("Outbox", fromMailBox.getId());

        MailEntity mailEntityTo;

        if(toMailBox == null) {
            int folderInboxId = folderDAO.getFolderIdByNameAndUserId("Inbox", fromMailBox.getId());
            mailEntityTo = new MailEntity();
            mailEntityTo.setBody("<h1>Mail box not found</h1> <br/><br/>" + mailDTO.getBody());
            mailEntityTo.setMailFrom("admin@demail.de");
            mailEntityTo.setMailTo(fromMailBox.getMailBox());
            mailEntityTo.setMailBoxOwnerId(fromMailBox.getId());
            mailEntityTo.setSubject(mailDTO.getSubject());
            mailEntityTo.setRead(false);
            mailEntityTo.setFolderId(folderInboxId);
        } else {
            int folderInboxId = folderDAO.getFolderIdByNameAndUserId("Inbox", toMailBox.getId());
            mailEntityTo = new MailEntity();
            mailEntityTo.setBody(mailDTO.getBody());
            mailEntityTo.setMailFrom(fromMailBox.getMailBox());
            mailEntityTo.setMailTo(toMailBox.getMailBox());
            mailEntityTo.setMailBoxOwnerId(toMailBox.getId());
            mailEntityTo.setSubject(mailDTO.getSubject());
            mailEntityTo.setRead(false);
            mailEntityTo.setFolderId(folderInboxId);
        }

        MailEntity mailEntity = new MailEntity();
        mailEntity.setBody(mailDTO.getBody());
        mailEntity.setMailFrom(fromMailBox.getMailBox());
        mailEntity.setMailTo(mailDTO.getTo());
        mailEntity.setMailBoxOwnerId(fromMailBox.getId());
        mailEntity.setFolderId(folderOutboxId);
        mailEntity.setRead(true);
        mailEntity.setSubject(mailDTO.getSubject());

        mailDAO.create(mailEntity);
        mailDAO.create(mailEntityTo);
    }

    public void removeMail(MailDTO mailDTO) {
        MailEntity mailEntity = (MailEntity) mailDAO.get(mailDTO.getId());
        mailDAO.delete(mailEntity);
    }

    public void moveMailToFolder(MailDTO mailDTO) {
        System.out.println("--------getFolderIdByNameAndUserId >" + mailDTO.getSubject() + " " + mailDTO.getToId());
        String folderName = mailDTO.getSubject();
        int userId =  mailDTO.getToId();
        int toFolderId = folderDAO.getFolderIdByNameAndUserId(folderName, userId);
        System.out.println("------------------toFolderId " + toFolderId);
//        if(toFolderId == null) throw new DAOException(getClass().getCanonicalName(), "Folder not found by user id "
//                + userId + " and name " + folderName);

//        FolderEntity folder = (FolderEntity) folderDAO.get(FolderEntity.class, toFolderId);
//        if(folder == null) throw new DAOException(getClass().getCanonicalName(), "Folder not found by id " + toFolderId);

//        if(!folder.isSystem()) {
            MailEntity MailEntity = (MailEntity) mailDAO.get(mailDTO.getId());
            MailEntity.setFolderId(toFolderId);

            mailDAO.update(MailEntity);
//        }
//        else throw new DAOException(getClass().getCanonicalName(), "Cannot move to system folder!");
    }

//    public void removemailFromFolder(int folderId) {
//        List<MailEntity> mails = mailDAO.getmailByFolderId(folderId);
//
//        for(MailEntity mail : mails) {
//            mailDAO.delete(mail);
//        }
//    }
}
