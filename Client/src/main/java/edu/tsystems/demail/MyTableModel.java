package edu.tsystems.demail;

import edu.tsystems.demail.DTO.MailDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Author: Ivan Pastukh
 * Date: 22.06.13
 * Time: 2:16
 */


public class MyTableModel extends AbstractTableModel{
    ArrayList<MailDTO> mails;
    public MyTableModel(ArrayList<MailDTO> mails) {
        super();
        this.mails = mails;
    }
    @Override
    public int getRowCount() {
        return mails.size();
    }
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return mails.get(r).getFrom();
            case 1:
                return mails.get(r).getTo();
            case 2:
                return mails.get(r).getSubject();
            case 3:
                return mails.get(r).getDate();
            case 4:
                return mails.get(r).getRead();
            default:
                return "";
        }
    }
    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "From";
                break;
            case 1:
                result = "To";
                break;
            case 2:
                result = "Subject";
                break;
            case 3:
                result = "Date";
                break;
            case 4:
                result = "isRead";
                break;
        }
        return result;
    }

}